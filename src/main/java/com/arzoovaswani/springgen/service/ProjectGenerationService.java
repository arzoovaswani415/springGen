package com.arzoovaswani.springgen.service;

import com.arzoovaswani.springgen.config.ProjectConfig;
import com.arzoovaswani.springgen.config.ProjectConfigWriter;
import com.arzoovaswani.springgen.generator.FolderGenerator;
import com.arzoovaswani.springgen.util.PackageResolver;
import com.arzoovaswani.springgen.initializr.InitializrClient;
import com.arzoovaswani.springgen.wizard.ProjectWizard;
import com.arzoovaswani.springgen.zip.ZipExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProjectGenerationService {

    private final ProjectWizard projectWizard;

    private final InitializrClient initializrClient;

    private final ZipExtractor zipExtractor;

    private final FolderGenerator folderGenerator;

    public ProjectGenerationService(
            ProjectWizard projectWizard,
            InitializrClient initializrClient,
            ZipExtractor zipExtractor,
            FolderGenerator folderGenerator
    ) {

        this.projectWizard = projectWizard;
        this.initializrClient = initializrClient;
        this.zipExtractor = zipExtractor;
        this.folderGenerator=folderGenerator;

    }

    public void generateProject() {

        try {

            /*
             * Step 1
             * Collect project configuration.
             */
            ProjectConfig config =
                    projectWizard.start();

            /*
             * Step 2
             * Create project directory.
             */
            Path projectDirectory =
                    createProjectDirectory(config);

            /*
             * Step 3
             * Download starter project.
             */
            Path zipFile =
                    initializrClient.downloadProject(config);

            /*
             * Step 4
             * Extract ZIP.
             */
            zipExtractor.extract(
                    zipFile,
                    projectDirectory
            );

            /*
             * Step 5
             * Delete temporary ZIP.
             */

            PackageResolver resolver =
                    new PackageResolver(projectDirectory, config);

            folderGenerator.generate(resolver);

            ApplicationConfigGeneratorService configGenerator =
                    new ApplicationConfigGeneratorService();

            configGenerator.generate(
                    projectDirectory,
                    config
            );

            ProjectConfigWriter writer =
                    new ProjectConfigWriter();

            writer.write(
                    projectDirectory,
                    config
            );
            Files.deleteIfExists(zipFile);

            System.out.println();

            System.out.println(
                    "✅ Project generated successfully!"
            );

            System.out.println(
                    "Location : "
                            + projectDirectory.toAbsolutePath()
            );

        }

        catch (IOException e) {

            System.err.println(
                    "File Error : "
                            + e.getMessage()
            );

        }

        catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.err.println(
                    "Download interrupted."
            );

        }

    }

    private Path createProjectDirectory(ProjectConfig config) throws IOException {

        Path projectDirectory =
                Path.of(config.getArtifactId());
        // Prevent overwriting an existing project
        if (Files.exists(projectDirectory)) {
            throw new IOException(
                    "Directory '" +
                            projectDirectory.getFileName() +
                            "' already exists."
            );
        }



        Files.createDirectories(projectDirectory);

        return projectDirectory;

    }

}