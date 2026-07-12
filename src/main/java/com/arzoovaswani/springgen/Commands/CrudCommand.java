package com.arzoovaswani.springgen.commands;

import com.arzoovaswani.springgen.config.ProjectConfig;
import com.arzoovaswani.springgen.config.ProjectConfigReader;
import com.arzoovaswani.springgen.service.CrudGenerationService;
import com.arzoovaswani.springgen.service.TemplateGenerationService;
import com.arzoovaswani.springgen.template.TemplateEngine;
import com.arzoovaswani.springgen.util.NamingUtil;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;

@Command(
        name = "crud",
        description = "Generates a complete CRUD module."
)
public class CrudCommand implements Runnable {

    @Parameters(
            index = "0",
            description = "Entity name"
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

            TemplateGenerationService templateGenerator =
                    new TemplateGenerationService(engine);

            CrudGenerationService crudGenerator =
                    new CrudGenerationService(templateGenerator);

            crudGenerator.generateCrud(
                    projectRoot,
                    config,
                    className
            );

        } catch (Exception e) {

            System.err.println(
                    "Failed to generate CRUD module: "
                            + e.getMessage()
            );

        }

    }

}