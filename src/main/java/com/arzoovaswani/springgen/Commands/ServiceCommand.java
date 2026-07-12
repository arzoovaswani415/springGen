package com.arzoovaswani.springgen.commands;

import com.arzoovaswani.springgen.config.ProjectConfig;
import com.arzoovaswani.springgen.config.ProjectConfigReader;
import com.arzoovaswani.springgen.model.TemplateType;
import com.arzoovaswani.springgen.service.TemplateGenerationService;
import com.arzoovaswani.springgen.template.TemplateEngine;
import com.arzoovaswani.springgen.util.NamingUtil;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;

@Command(
        name = "service",
        description = "Generates a Service class."
)
public class ServiceCommand implements Runnable {

    @Parameters(
            index = "0",
            description = "Service name"
    )
    private String className;

    @Override
    public void run() {

        try {

            Path projectRoot =
                    Path.of(System.getProperty("user.dir"));

            ProjectConfigReader reader =
                    new ProjectConfigReader();

            ProjectConfig config =
                    reader.read(projectRoot);

            className =
                    NamingUtil.toPascalCase(className);

            TemplateEngine engine =
                    new TemplateEngine();

            TemplateGenerationService generator =
                    new TemplateGenerationService(engine);

            generator.generate(
                    projectRoot,
                    config,
                    TemplateType.SERVICE,
                    className
            );

        } catch (Exception e) {

            System.err.println(
                    "Failed to generate Service: "
                            + e.getMessage()
            );

        }

    }

}