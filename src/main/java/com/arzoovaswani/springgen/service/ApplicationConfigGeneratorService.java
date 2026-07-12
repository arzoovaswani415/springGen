package com.arzoovaswani.springgen.service;

import com.arzoovaswani.springgen.config.ProjectConfig;
import com.arzoovaswani.springgen.model.ConfigFormat;
import com.arzoovaswani.springgen.service.PropertiesConfigWriter;
import com.arzoovaswani.springgen.service.YamlConfigWriter;

import java.io.IOException;
import java.nio.file.Path;

public class ApplicationConfigGeneratorService {

    private final PropertiesConfigWriter propertiesWriter =
            new PropertiesConfigWriter();

    private final YamlConfigWriter yamlWriter =
            new YamlConfigWriter();

    public void generate(Path projectRoot,
                         ProjectConfig config)
            throws IOException {

        if (config.getConfigFormat() == ConfigFormat.PROPERTIES) {

            propertiesWriter.generate(projectRoot, config);

        } else {

            yamlWriter.generate(projectRoot, config);

        }
    }
}