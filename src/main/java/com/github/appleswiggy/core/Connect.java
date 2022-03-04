package com.github.appleswiggy.core;

import com.github.appleswiggy.util.Global;

import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

public class Connect {
    static int getStatusCode(String newURL) {
        try {
            HttpURLConnection obj = (HttpURLConnection) new URL(newURL).openConnection();

            obj.setRequestMethod("GET");
            obj.setRequestProperty("User-Agent", Global.userAgent);
            obj.setConnectTimeout(Global.timeout);

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
