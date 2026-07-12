package com.arzoovaswani.springgen.generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileGenerator {

    /**
     * Writes generated content to a file.
     *
     * Missing directories are created automatically.
     */
    public void generate(Path outputFile,
                         String content)
            throws IOException {

        Path parent = outputFile.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        Files.writeString(
                outputFile,
                content,
                StandardCharsets.UTF_8
        );

    }

}