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
        name = "repository",
        description = "Generates a Repository."
)
public class RepositoryCommand implements Runnable {

    @Parameters(
            index = "0",
            description = "Entity class name"
    )
    private String entityName;

    @Override
    public void run() {

        try {

            Path projectRoot =
                    Path.of(System.getProperty("user.dir"));

            entityName =
                    NamingUtil.toPascalCase(entityName);

            ProjectConfigReader reader =
                    new ProjectConfigReader();

            ProjectConfig config =
                    reader.read(projectRoot);

            TemplateType templateType;

            if (config.isJpaProject()) {

                templateType =
                        TemplateType.JPA_REPOSITORY;

            }
            else if (config.isJdbcProject()) {

                templateType =
                        TemplateType.JDBC_REPOSITORY;

            }
            else {

                throw new IllegalStateException(
                        "Repository generation requires Spring Data JPA or JDBC."
                );

            }

            TemplateEngine engine =
                    new TemplateEngine();

            TemplateGenerationService generator =
                    new TemplateGenerationService(engine);

            generator.generate(
                    projectRoot,
                    config,
                    templateType,
                    entityName
            );

        }
        catch (Exception e) {

            System.err.println(e.getMessage());

        }

    }

}