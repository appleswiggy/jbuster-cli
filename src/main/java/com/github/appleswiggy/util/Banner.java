package com.github.appleswiggy.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Banner {
    public static void printBanner() {
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println(Global.partitioner);
        System.out.println(Global.tool + " " + Global.version);
        System.out.println(Global.credits);
        System.out.println(Global.partitioner);

        System.out.println("[+] URL:\t\t" + Global.url);
        System.out.println("[+] Wordlist:\t\t" + Global.wordlist);
        System.out.println("[+] User Agent:\t\t" + Global.userAgent);
        System.out.println("[+] Threads:\t\t" + Global.threads);
        System.out.println("[+] Timeout:\t\t" + Global.timeout + " ms");

        System.out.println(Global.partitioner);
        System.out.println(dateObj.format(formatObj) + " Starting Jbuster");
        System.out.println(Global.partitioner);

    }

    public static void errorBanner() {

    }
}
