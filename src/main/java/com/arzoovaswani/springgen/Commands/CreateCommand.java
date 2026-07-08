package com.arzoovaswani.springgen.Commands;

import com.arzoovaswani.springgen.initializr.InitializrClient;
import com.arzoovaswani.springgen.service.HttpClientService;
import com.arzoovaswani.springgen.service.ProjectGenerationService;
import com.arzoovaswani.springgen.wizard.ProjectWizard;
import com.arzoovaswani.springgen.zip.ZipExtractor;
import picocli.CommandLine.Command;

@Command(
        name = "create",
        description = "Create a new Spring Boot project"
)
public class CreateCommand implements Runnable {

    private final ProjectGenerationService projectGenerationService;

    public CreateCommand() {

        ProjectWizard wizard =
                new ProjectWizard(System.in);

        HttpClientService httpClientService =
                new HttpClientService();

        InitializrClient initializrClient =
                new InitializrClient(httpClientService);

        ZipExtractor zipExtractor =
                new ZipExtractor();

        this.projectGenerationService =
                new ProjectGenerationService(
                        wizard,
                        initializrClient,
                        zipExtractor
                );

    }

    @Override
    public void run() {

        projectGenerationService.generateProject();

    }

}