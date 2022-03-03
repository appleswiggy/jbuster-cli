package com.github.appleswiggy.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class SyncMethods {
    int completed = 0;

    String getCounter() {
        return Integer.toString(completed);
    }

    synchronized void incrementCounter() {
        completed++;
    }
}

class WorkerThread extends Thread {
    SyncMethods sm;
    int start, end;
    static ArrayList<String> words;
    static String url;
    static String userAgent;
    static int timeout;

    WorkerThread(SyncMethods sm, int start, int end) {
        this.sm = sm;
        this.start = start;
        this.end = end;
        this.start();
    }

    public void run() {
        System.out.println(Connect.getStatusCode(url, userAgent, timeout));
    }
}

public class Core {
    public static void process(String url, String wordlist, String userAgent, int threads, int timeout) {
        try {
            File myObj = new File(wordlist);
            Scanner myReader = new Scanner(myObj);
            ArrayList<String> words = new ArrayList<String>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.length() != 0) {
                    words.add(data);
                }
            }
            myReader.close();
            WorkerThread.words = words;

            // fix
            WorkerThread.url = url;
            WorkerThread.userAgent = userAgent;
            WorkerThread.timeout = timeout;

            int splitter = words.size() / threads;
            SyncMethods sm = new SyncMethods();
            WorkerThread[] workerThreads = new WorkerThread[threads];

            for (int i = 0; i < threads; ++i) {
                if (i == 0) {
                    workerThreads[i] = new WorkerThread(sm, 0, splitter);
                } else if (i == threads - 1) {
                    workerThreads[i] = new WorkerThread(sm, splitter * i, words.size());
                } else {
                    workerThreads[i] = new WorkerThread(sm, splitter * i, splitter * (i + 1));
                }
            }
            for (int i = 0; i < threads; ++i) {
                try {
                    workerThreads[i].join();
                } catch (InterruptedException ie) {
                    System.out.println("Interrupted exception. ");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while opening the wordlist.");
            e.printStackTrace();
        }

    }
}
