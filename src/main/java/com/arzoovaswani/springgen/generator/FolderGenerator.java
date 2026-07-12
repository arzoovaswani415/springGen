package com.arzoovaswani.springgen.generator;

import com.arzoovaswani.springgen.util.PackageResolver;

import java.io.IOException;
import java.nio.file.Files;

public class FolderGenerator {

    public void generate(PackageResolver resolver)
            throws IOException {

        createDirectory(resolver.getControllerPath());

        createDirectory(resolver.getServicePath());

        createDirectory(resolver.getRepositoryPath());

        createDirectory(resolver.getEntityPath());

        createDirectory(resolver.getDTOPath());

        createDirectory(resolver.getMapperPath());

        createDirectory(resolver.getExceptionPath());

        createDirectory(resolver.getSecurityPath());

        createDirectory(resolver.getValidationPath());

        createDirectory(resolver.getUtilPath());

        createDirectory(resolver.getConstantsPath());

    }

    private void createDirectory(java.nio.file.Path path)
            throws IOException {

        Files.createDirectories(path);

    }

}