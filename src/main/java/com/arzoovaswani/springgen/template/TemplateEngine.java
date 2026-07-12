package com.arzoovaswani.springgen.template;

import com.arzoovaswani.springgen.generator.FileGenerator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class TemplateEngine {

    private final TemplateLoader templateLoader;

    private final PlaceholderReplacer placeholderReplacer;

    private final FileGenerator fileGenerator;

    public TemplateEngine() {

        this.templateLoader = new TemplateLoader();
        this.placeholderReplacer = new PlaceholderReplacer();
        this.fileGenerator = new FileGenerator();

    }

    /**
     * Generates a file from a template.
     *
     * Flow:
     *
     * Load Template
     *      ↓
     * Replace Placeholders
     *      ↓
     * Write File
     */
    public void generate(String templateName,
                         Map<String, String> placeholders,
                         Path outputFile)
            throws IOException {

        String template =
                templateLoader.load(templateName);

        String generatedSource =
                placeholderReplacer.replace(
                        template,
                        placeholders
                );

        fileGenerator.generate(
                outputFile,
                generatedSource
        );

    }

}