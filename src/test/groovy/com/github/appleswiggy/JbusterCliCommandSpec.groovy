package com.github.appleswiggy

import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment

import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import java.io.ByteArrayOutputStream
import java.io.PrintStream

class JbusterCliCommandSpec extends Specification {

    @Shared @AutoCleanup ApplicationContext ctx = ApplicationContext.run(Environment.CLI, Environment.TEST)

    void "test jbuster-cli with command line option"() {
        given:
        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        System.setOut(new PrintStream(baos))

        String[] args = ['-u', 'https://google.com', '-w', '/usr/share/wordlists/common.txt', '-U', 'abcda'] as String[]
        PicocliRunner.run(JbusterCliCommand, ctx, args)

        expect:
        baos.toString().contains('abcda')
    }
}

