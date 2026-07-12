package com.arzoovaswani.springgen.zip;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractor {

    /**
     * Extracts the downloaded Spring Initializr ZIP
     * into the destination project directory.
     */
    public void extract(Path zipFile,
                        Path destinationDirectory)
            throws IOException {

        Files.createDirectories(destinationDirectory);

        try (InputStream inputStream = Files.newInputStream(zipFile);
             ZipInputStream zipInputStream =
                     new ZipInputStream(new BufferedInputStream(inputStream))) {

            ZipEntry entry;

            while ((entry = zipInputStream.getNextEntry()) != null) {

                Path outputPath = destinationDirectory
                        .resolve(entry.getName())
                        .normalize();

                /*
                 * Prevent Zip Slip attack.
                 *
                 * Example:
                 * ../../../../etc/passwd
                 *
                 * should never be extracted.
                 */
                if (!outputPath.startsWith(destinationDirectory)) {
                    throw new IOException(
                            "Invalid ZIP entry: " + entry.getName()
                    );
                }

                if (entry.isDirectory()) {

                    Files.createDirectories(outputPath);

                } else {

                    Files.createDirectories(outputPath.getParent());

                    Files.copy(
                            zipInputStream,
                            outputPath,
                            StandardCopyOption.REPLACE_EXISTING
                    );

                }

                zipInputStream.closeEntry();

            }

        }

    }

}