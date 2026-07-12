package com.arzoovaswani.springgen.service;

import com.arzoovaswani.springgen.config.ProjectConfig;
import com.arzoovaswani.springgen.util.NamingUtil;
import com.arzoovaswani.springgen.util.PackageResolver;
import com.arzoovaswani.springgen.model.TemplateType;
import com.arzoovaswani.springgen.template.TemplateEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
public class TemplateGenerationService {

        private final TemplateEngine templateEngine;

        public TemplateGenerationService(TemplateEngine templateEngine) {
            this.templateEngine = templateEngine;
        }


        public void generate(Path projectRoot,
                             ProjectConfig config,
                             TemplateType templateType,
                             String className)
                throws IOException {

            PackageResolver resolver =
                    new PackageResolver(projectRoot, config);

            String packageName =
                    resolver.resolvePackage(templateType);

            Path outputDirectory =
                    resolver.resolvePath(templateType);

            Files.createDirectories(outputDirectory);

            Map<String, String> placeholders = new HashMap<>();

            String generatedClassName =
                    templateType.getClassName(className);

            placeholders.put("PACKAGE", packageName);

            placeholders.put(
                    "CLASS_NAME",
                    generatedClassName
            );

            placeholders.put(
                    "ENTITY_NAME",
                    className
            );

            placeholders.put(
                    "BASE_PACKAGE",
                    config.getPackageName()
            );
            placeholders.put(
                    "API_PATH",
                    NamingUtil.toKebabCase(className)
            );

            Path outputFile =
                    outputDirectory.resolve(
                            templateType.getFileName(className)
                    );

                    templateEngine.generate(
                            templateType.getTemplateName(),
                            placeholders,
                            outputFile
                    );

            System.out.println(
                    "✓ Generated "
                            + outputFile.getFileName()
            );
        }
    }

