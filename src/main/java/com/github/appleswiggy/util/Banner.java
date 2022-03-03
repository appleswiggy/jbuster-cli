package com.github.appleswiggy.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Banner {
    public static void printBanner(String url, String wordlist, String userAgent, int threads) {
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println(Global.partitioner);
        System.out.println(Global.tool + " " + Global.version);
        System.out.println(Global.credits);
        System.out.println(Global.partitioner);

        System.out.println("[+] URL:\t\t" + url);
        System.out.println("[+] Wordlist:\t\t" + wordlist);
        System.out.println("[+] User Agent:\t\t" + userAgent);
        System.out.println("[+] Threads:\t\t" + threads);

        System.out.println(Global.partitioner);
        System.out.println(dateObj.format(formatObj) + " Starting Jbuster");
        System.out.println(Global.partitioner);

    }
}
