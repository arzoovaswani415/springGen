package com.arzoovaswani.springgen;

import com.arzoovaswani.springgen.template.PlaceholderReplacer;
import com.arzoovaswani.springgen.template.TemplateEngine;
import com.arzoovaswani.springgen.template.TemplateLoader;
import picocli.CommandLine;
import com.arzoovaswani.springgen.CLI.SpringGenCLI;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        int exitCode = new CommandLine(new SpringGenCLI())
                .execute(args);

        TemplateEngine engine =
                new TemplateEngine();

        Map<String,String> placeholders =
                new HashMap<>();

        placeholders.put(
                "PACKAGE",
                "com.example.demo.entity"
        );

        placeholders.put(
                "CLASS_NAME",
                "User"
        );

        engine.generate(
                "entity.tpl",
                placeholders,
                Path.of("User.java")
        );
        System.exit(exitCode);
    }
}