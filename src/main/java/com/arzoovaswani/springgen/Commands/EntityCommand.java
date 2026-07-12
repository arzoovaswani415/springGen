package com.arzoovaswani.springgen.commands;

import com.arzoovaswani.springgen.config.ProjectConfig;
import com.arzoovaswani.springgen.config.ProjectConfigReader;
import com.arzoovaswani.springgen.model.TemplateType;
import com.arzoovaswani.springgen.service.TemplateGenerationService;
import com.arzoovaswani.springgen.template.PlaceholderReplacer;
import com.arzoovaswani.springgen.template.TemplateEngine;
import com.arzoovaswani.springgen.template.TemplateLoader;
import com.arzoovaswani.springgen.util.NamingUtil;
import com.arzoovaswani.springgen.util.PackageDetector;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;

@Command(
        name = "entity",
        description = "Generates a JPA Entity."
)
public class EntityCommand implements Runnable {

    @Parameters(
            index = "0",
            description = "Entity class name"
    )
    private String className;

    @Override
    public void run() {

        try {

            /*
             * TODO:
             * Replace this by loading
             * .springgen/config.properties
             */
            Path projectRoot =
                    Path.of(System.getProperty("user.dir"));

            ProjectConfigReader reader =
                    new ProjectConfigReader();

            ProjectConfig config =
                    reader.read(projectRoot);

            if (!config.isJpaProject()) {
                throw new IllegalStateException(
                        "Entity generation requires the Spring Data JPA dependency."
                );
            }

            className = NamingUtil.toPascalCase(className);

            TemplateEngine engine =
                    new TemplateEngine();

            TemplateGenerationService generator =
                    new TemplateGenerationService(engine);

            generator.generate(
                    projectRoot,
                    config,
                    TemplateType.ENTITY,
                    className
            );

        } catch (Exception e) {

            System.err.println(
                    "Failed to generate entity: "
                            + e.getMessage()
            );

        }

    }

}