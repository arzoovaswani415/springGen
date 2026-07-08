package com.arzoovaswani.springgen.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.time.Duration;

public class HttpClientService {

    private final HttpClient httpClient;

    public HttpClientService() {

        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();

    }

    /**
     * Downloads a file from the given URI.
     *
     * @param uri URL to download
     * @param destination Destination path
     * @return downloaded file path
     */
    public Path downloadFile(
            URI uri,
            Path destination
    ) throws IOException, InterruptedException {

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(uri)
                        .GET()
                        .timeout(Duration.ofSeconds(60))
                        .build();

        HttpResponse<Path> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofFile(destination)
                );

        if (response.statusCode() != 200) {

            throw new IOException(
                    "Download failed. HTTP Status: "
                            + response.statusCode()
            );

        }

        return response.body();

    }

}