package com.arzoovaswani.springgen.util;

import com.arzoovaswani.springgen.config.ProjectConfig;
import com.arzoovaswani.springgen.model.TemplateType;

import java.nio.file.Path;

public class PackageResolver {

    private final String basePackage;

    private final Path basePackagePath;

    public PackageResolver(Path projectRoot,
                           ProjectConfig config) {

        this.basePackage = config.getPackageName();

        this.basePackagePath = projectRoot
                .resolve("src")
                .resolve("main")
                .resolve("java")
                .resolve(basePackage.replace('.', '/'));

    }

    /*
     * ============================================================
     * Explicit Paths
     * Used by FolderGenerator
     * ============================================================
     */

    public Path getBasePackagePath() {
        return basePackagePath;
    }

    public Path getControllerPath() {
        return basePackagePath.resolve("controller");
    }

    public Path getServicePath() {
        return basePackagePath.resolve("service");
    }

    public Path getRepositoryPath() {
        return basePackagePath.resolve("repository");
    }

    public Path getEntityPath() {
        return basePackagePath.resolve("entity");
    }

    public Path getDTOPath() {
        return basePackagePath.resolve("dto");
    }

    public Path getExceptionPath() {
        return basePackagePath.resolve("exception");
    }

    public Path getMapperPath() {
        return basePackagePath.resolve("mapper");
    }

    public Path getSecurityPath() {
        return basePackagePath.resolve("security");
    }

    public Path getValidationPath() {
        return basePackagePath.resolve("validation");
    }

    public Path getUtilPath() {
        return basePackagePath.resolve("util");
    }

    public Path getConstantsPath() {
        return basePackagePath.resolve("constants");
    }

    /*
     * ============================================================
     * Generic Resolver
     * Used by TemplateGenerationService
     * ============================================================
     */

    public Path resolvePath(TemplateType templateType) {

        return switch (templateType) {

            case ENTITY -> getEntityPath();

            case JPA_REPOSITORY,
                 JDBC_REPOSITORY -> getRepositoryPath();

            case SERVICE,
                 CRUD_SERVICE ->
                    getServicePath();
            case CONTROLLER,
                 CRUD_CONTROLLER ->
                    getControllerPath();

            case DTO,
                 LOMBOK_DTO ->
                    getDTOPath();

            case MAPPER -> getMapperPath();

            case EXCEPTION -> getExceptionPath();

            default -> getBasePackagePath();
        };

    }

    public String resolvePackage(TemplateType templateType) {

        return switch (templateType) {

            case ENTITY -> basePackage + ".entity";

            case JPA_REPOSITORY,
                 JDBC_REPOSITORY -> basePackage + ".repository";

            case SERVICE,
                 CRUD_SERVICE -> basePackage+".service";

            case CONTROLLER,
                 CRUD_CONTROLLER ->basePackage+ ".controller";

            case DTO,
                 LOMBOK_DTO ->
                    basePackage + ".dto";

            case MAPPER -> basePackage + ".mapper";

            case EXCEPTION -> basePackage + ".exception";

            default -> basePackage;
        };

    }

}