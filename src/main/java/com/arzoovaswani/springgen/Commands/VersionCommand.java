package com.arzoovaswani.springgen.Commands;

import picocli.CommandLine.Command;

@Command(
        name = "version",
        description = "Displays SpringGen version"
)
public class VersionCommand implements Runnable {

    @Override
    public void run() {

        System.out.println("SpringGen v1.0.0");

    }

}