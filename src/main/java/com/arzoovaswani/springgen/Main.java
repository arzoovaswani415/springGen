package com.arzoovaswani.springgen;

import picocli.CommandLine;
import com.arzoovaswani.springgen.CLI.SpringGenCLI;

public class Main {

    public static void main(String[] args) {

        int exitCode = new CommandLine(new SpringGenCLI())
                .execute(args);

        System.exit(exitCode);
    }
}