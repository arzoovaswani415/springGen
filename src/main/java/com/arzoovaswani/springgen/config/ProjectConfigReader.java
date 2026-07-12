package com.arzoovaswani.springgen.config;

import com.arzoovaswani.springgen.model.BuildTool;
import com.arzoovaswani.springgen.model.ConfigFormat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Properties;

public class ProjectConfigReader {

    private static final String FILE_NAME =
            ".springgen.properties";

    public ProjectConfig read(Path projectRoot)
            throws IOException {

        Path configFile =
                projectRoot.resolve(FILE_NAME);

        if (!Files.exists(configFile)) {
            throw new IOException(
                    "Not a SpringGen project."
            );
        }

        Properties properties = new Properties();

        try (InputStream input =
                     Files.newInputStream(configFile)) {

            properties.load(input);

        }

        ProjectConfig config = new ProjectConfig();

        config.setPackageName(
                properties.getProperty("package")
        );

        config.setGroupId(
                properties.getProperty("group")
        );

        config.setArtifactId(
                properties.getProperty("artifact")
        );

        String buildTool =
                properties.getProperty(
                        "build-tool",
                        "gradle"
                );

        config.setBuildTool(
                BuildTool.valueOf(
                        buildTool.toUpperCase()
                )
        );

        String dependencies =
                properties.getProperty("dependencies", "");

        if (!dependencies.isBlank()) {

            config.setDependencies(
                    Arrays.asList(
                            dependencies.split(",")
                    )
            );

        }
        String format =
                properties.getProperty(
                        "config-format",
                        "properties"
                );

        config.setConfigFormat(
                ConfigFormat.valueOf(
                        format.toUpperCase()
                )
        );

        return config;

    }

}