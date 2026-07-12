package com.arzoovaswani.springgen.CLI;

import com.arzoovaswani.springgen.commands.*;
import picocli.CommandLine.Command;


@Command(
        name = "springgen",
        mixinStandardHelpOptions = true,
        version = "SpringGen v1.0.0",
        description = "AI Powered Spring Boot Project Generator",
        subcommands = {
                VersionCommand.class,
                CreateCommand.class,
                EntityCommand.class,
                RepositoryCommand.class,
                DtoCommand.class,
                ServiceCommand.class,
                ControllerCommand.class,
                CrudCommand.class
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