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
        name = "dto",
        description = "Generates a DTO class."
)
public class DtoCommand implements Runnable {

    @Parameters(
            index = "0",
            description = "DTO name"
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

            TemplateType templateType;

            if (config.hasLombok()) {

                templateType = TemplateType.LOMBOK_DTO;

            } else {

                templateType = TemplateType.DTO;

            }

            TemplateEngine engine =
                    new TemplateEngine();

            TemplateGenerationService generator =
                    new TemplateGenerationService(engine);

            generator.generate(
                    projectRoot,
                    config,
                    templateType,
                    className
            );

        } catch (Exception e) {

            System.err.println(
                    "Failed to generate DTO: "
                            + e.getMessage()
            );

        }

    }

}