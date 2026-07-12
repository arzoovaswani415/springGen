package com.arzoovaswani.springgen.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class PackageDetector {

    private PackageDetector() {
    }

    public static String detect(Path projectRoot)
            throws IOException {

        Path javaRoot = projectRoot
                .resolve("src")
                .resolve("main")
                .resolve("java");

        try (Stream<Path> paths = Files.walk(javaRoot)) {

            Path applicationClass = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .filter(PackageDetector::isSpringBootApplication)
                    .findFirst()
                    .orElseThrow(() ->
                            new IOException(
                                    "Could not locate SpringBootApplication class."
                            )
                    );

            Path relative =
                    javaRoot.relativize(applicationClass.getParent());

            return relative
                    .toString()
                    .replace('/', '.')
                    .replace('\\', '.');
        }
    }

    private static boolean isSpringBootApplication(Path file) {

        try {

            String source = Files.readString(file);

            return source.contains("@SpringBootApplication");

        }

        catch (IOException e) {

            return false;

        }

    }

}