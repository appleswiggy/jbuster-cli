package com.github.appleswiggy.core;

import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

public class Connect {
    static int getStatusCode(String url, String userAgent, int timeout) {
        try {
            HttpURLConnection obj = (HttpURLConnection) new URL(url).openConnection();

            obj.setRequestMethod("GET");
            obj.setRequestProperty("User-Agent", userAgent);
            obj.setConnectTimeout(timeout);

            try {
                return obj.getResponseCode();
            } catch (SocketTimeoutException ste) {
                System.out.println("Timeout.");
            }

        } catch (MalformedURLException mue) {
            System.out.println(mue);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return -1;
    }
}
