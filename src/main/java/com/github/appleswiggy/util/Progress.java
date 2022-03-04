package com.github.appleswiggy.util;

public class Progress {
    private int completed = 0;
    private int total;

    public Progress(int total) {
        this.total = total;
    }

    synchronized public void printProgress() {
        completed++;
        System.out.printf("\rProgress: %d/%d (%.2f%%)", completed, total, (float) (completed * 100) / total);
    }

    // implement using \b\b\b\b\b\b here

    public void printStatus(String path, int statusCode) {
        System.out.printf("\r/%s (Status: %d)%s\n", path,
                statusCode, Global.spaces);
    }

    public void printEmptyline() {
        System.out.printf("\r%s\n", Global.spaces);
    }
}
