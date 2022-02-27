package com.github.appleswiggy;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "jbuster-cli", description = "Web directory/file busting CLI tool in Java.", mixinStandardHelpOptions = true)
public class JbusterCliCommand implements Runnable {

    @Option(names = { "-u", "--url" }, description = "URL of the target.", required = true)
    String url;

    @Option(names = { "-w", "--wordlist" }, description = "Wordlist to be used.", required = true)
    String wordllist;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(JbusterCliCommand.class, args);
    }

    public void run() {
        System.out.println("URL given: " + url);
        System.out.println("Wordlist: " + wordllist);
    }
}
