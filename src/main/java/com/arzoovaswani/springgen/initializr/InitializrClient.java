package com.arzoovaswani.springgen.initializr;

import com.arzoovaswani.springgen.model.BuildTool;
import com.arzoovaswani.springgen.config.ProjectConfig;
import com.arzoovaswani.springgen.service.HttpClientService;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class InitializrClient {

    private static final String BASE_URL =
            "https://start.spring.io/starter.zip";

    private final HttpClientService httpClientService;

    public InitializrClient(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    /**
     * Downloads the Spring Boot starter project.
     *
     * @param config Project configuration
     * @return Path to downloaded starter.zip
     */
    public Path downloadProject(ProjectConfig config)
            throws IOException, InterruptedException {

        URI uri = buildUri(config);

        Path destination = Path.of("starter.zip");

        return httpClientService.downloadFile(uri, destination);

    }

    /**
     * Builds the Spring Initializr URI.
     */
    private URI buildUri(ProjectConfig config) {

        StringBuilder builder = new StringBuilder(BASE_URL);

        builder.append("?");

        String buildType =
                config.getBuildTool() == BuildTool.GRADLE
                        ? "gradle-project"
                        : "maven-project";

        appendParameter(builder, "type", buildType);

        appendParameter(builder, "language", "java");

        appendParameter(builder, "groupId",
                config.getGroupId());

        appendParameter(builder, "artifactId",
                config.getArtifactId());

        appendParameter(builder, "name",
                config.getProjectName());

        appendParameter(builder, "packageName",
                config.getPackageName());

        appendParameter(builder, "javaVersion",
                config.getJavaVersion().getVersion());

        appendParameter(builder, "bootVersion",
                config.getSpringBootVersion().getVersion());
        appendParameter(
                builder,
                "configurationFileFormat",
                config.getConfigFormat().getId()
        );

        appendParameter(builder, "dependencies",
                String.join(",", config.getDependencies()));

        return URI.create(builder.toString());

    }

    /**
     * Appends one query parameter.
     *
     * Example:
     *
     * groupId=com.arzoovaswani&
     */
    private void appendParameter(
            StringBuilder builder,
            String key,
            String value
    ) {

        if (builder.charAt(builder.length() - 1) != '?') {
            builder.append("&");
        }

        builder.append(encode(key))
                .append("=")
                .append(encode(value));

    }

    /**
     * URL encodes a value.
     */
    private String encode(String value) {

        return URLEncoder.encode(
                value,
                StandardCharsets.UTF_8
        );

    }

}