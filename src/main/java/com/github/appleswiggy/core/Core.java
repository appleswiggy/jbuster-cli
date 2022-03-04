package com.github.appleswiggy.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.github.appleswiggy.util.Progress;

class WorkerThread extends Thread {
    private Progress progress;
    private int start, end;

    static ArrayList<String> words;
    static String url;
    static String userAgent;
    static int timeout;

    WorkerThread(Progress progress, int start, int end) {
        this.progress = progress;
        this.start = start;
        this.end = end;
        this.start();
    }

    public void run() {
        if (start != 0 || end != 0) {
            for (int i = start; i < end; ++i) {
                progress.printProgress();

                String newURL = url + "/" + words.get(i);
                int statusCode = Connect.getStatusCode(newURL, userAgent, timeout);

                if (statusCode == 200) {
                    progress.printStatus(words.get(i), statusCode);
                }
                if (i == end - 1) {
                    progress.printEmptyline();
                }

            }
        }
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
            Progress progress = new Progress(words.size());
            WorkerThread[] workerThreads = new WorkerThread[threads];

            for (int i = 0; i < threads; ++i) {
                if (i == 0) {
                    workerThreads[i] = new WorkerThread(progress, 0, splitter);
                } else if (i == threads - 1) {
                    workerThreads[i] = new WorkerThread(progress, splitter * i, words.size());
                } else {
                    workerThreads[i] = new WorkerThread(progress, splitter * i, splitter * (i + 1));
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
