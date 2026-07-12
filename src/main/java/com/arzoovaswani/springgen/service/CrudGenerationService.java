package com.arzoovaswani.springgen.service;

import com.arzoovaswani.springgen.config.ProjectConfig;
import com.arzoovaswani.springgen.model.TemplateType;

import java.io.IOException;
import java.nio.file.Path;

public class CrudGenerationService {

    private final TemplateGenerationService generator;

    public CrudGenerationService(
            TemplateGenerationService generator
    ) {
        this.generator = generator;
    }

    public void generateCrud(
            Path projectRoot,
            ProjectConfig config,
            String entityName
    ) throws IOException {

        TemplateType entityTemplate =
                config.isJpaProject()
                        ? TemplateType.ENTITY
                        : TemplateType.DTO;

        TemplateType repositoryTemplate =
                config.isJpaProject()
                        ? TemplateType.JPA_REPOSITORY
                        : TemplateType.JDBC_REPOSITORY;

        generator.generate(
                projectRoot,
                config,
                entityTemplate,
                entityName
        );

        generator.generate(
                projectRoot,
                config,
                repositoryTemplate,
                entityName
        );

        TemplateType dtoTemplate =
                config.hasLombok()
                        ? TemplateType.LOMBOK_DTO
                        : TemplateType.DTO;

        generator.generate(
                projectRoot,
                config,
                dtoTemplate,
                entityName
        );

        generator.generate(
                projectRoot,
                config,
                TemplateType.CRUD_SERVICE,
                entityName
        );

        generator.generate(
                projectRoot,
                config,
                TemplateType.CRUD_CONTROLLER,
                entityName
        );
        // Mapper

        generator.generate(
                projectRoot,
                config,
                TemplateType.MAPPER,
                entityName
        );

        // Exception

        generator.generate(
                projectRoot,
                config,
                TemplateType.EXCEPTION,
                "ResourceNotFound"
        );

    }

}