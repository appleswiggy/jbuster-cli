package com.github.appleswiggy;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "jbuster-cli", description = "Directory busting CLI tool in Java.", mixinStandardHelpOptions = true)
public class JbusterCliCommand implements Runnable {

    @Option(names = { "-v", "--verbose" }, description = "Print verbose.")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(JbusterCliCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
