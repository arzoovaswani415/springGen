package com.arzoovaswani.springgen.config;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ProjectConfigWriter {

    private static final String FILE_NAME =
            ".springgen.properties";

    public void write(Path projectRoot,
                      ProjectConfig config)
            throws IOException {

        Properties properties = new Properties();

        properties.setProperty(
                "package",
                config.getPackageName()
        );

        properties.setProperty(
                "group",
                config.getGroupId()
        );

        properties.setProperty(
                "artifact",
                config.getArtifactId()
        );

        properties.setProperty(
                "buildTool",
                config.getBuildTool().name().toLowerCase()
        );
        properties.setProperty(
                "config-format",
                config.getConfigFormat().getId()
        );


        properties.setProperty(
                "dependencies",
                String.join(",", config.getDependencies())
        );

        Path configFile =
                projectRoot.resolve(FILE_NAME);

        try (OutputStream output =
                     Files.newOutputStream(configFile)) {

            properties.store(
                    output,
                    "SpringGen Project Configuration"
            );

        }

    }

}