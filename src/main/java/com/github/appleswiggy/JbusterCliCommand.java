package com.github.appleswiggy;

import com.github.appleswiggy.util.Banner;
import com.github.appleswiggy.util.Global;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "jbuster-cli", description = "Web directory/file busting CLI tool in Java.", mixinStandardHelpOptions = true, version = "Jbuster-cli v1.0")
public class JbusterCliCommand implements Runnable {

    @Option(names = { "-u", "--url" }, description = "URL of the target.", required = true)
    String url;

    @Option(names = { "-w", "--wordlist" }, description = "Wordlist to be used.", required = true)
    String wordlist;

    @Option(names = { "-U", "--user-agent" }, description = "User-Agent HTTP header", defaultValue = "Jbuster-cli/1.0")
    String userAgent = Global.userAgent;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(JbusterCliCommand.class, args);
    }

    public void run() {
        Banner.printBanner(url, wordlist, userAgent);
    }
}
