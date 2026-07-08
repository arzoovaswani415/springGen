package com.arzoovaswani.springgen.CLI;

import com.arzoovaswani.springgen.Commands.VersionCommand;
import picocli.CommandLine.Command;

@Command(
        name = "springgen",
        mixinStandardHelpOptions = true,
        version = "SpringGen v1.0.0",
        description = "AI Powered Spring Boot Project Generator",
        subcommands = {
                VersionCommand.class
        }
)
public class SpringGenCLI implements Runnable {

    @Override
    public void run() {

        System.out.println(
                """
                Welcome to SpringGen!

                Use

                  springgen --help

                to see available commands.
                """
        );

    }

}