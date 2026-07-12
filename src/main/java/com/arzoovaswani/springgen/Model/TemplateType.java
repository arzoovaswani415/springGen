package com.arzoovaswani.springgen.model;

public enum TemplateType {

    ENTITY(
            "entity.tpl",
            "entity",
            "",
            ".java"
    ),

    JPA_REPOSITORY(
            "repository-jpa.tpl",
            "repository",
            "Repository",
            ".java"
    ),

    JDBC_REPOSITORY(
            "repository-jdbc.tpl",
            "repository",
            "Repository",
            ".java"
    ),

    SERVICE(
            "service.tpl",
            "service",
            "Service",
            ".java"
    ),

    CONTROLLER(
            "controller.tpl",
            "controller",
            "Controller",
            ".java"
    ),
    CRUD_SERVICE(
            "service-crud.tpl",
            "service",
            "Service",
            ".java"
    ),

    CRUD_CONTROLLER(
            "controller-crud.tpl",
            "controller",
            "Controller",
            ".java"
    ),
    DTO(
            "dto.tpl",
            "dto",
            "DTO",
            ".java"
    ),

    LOMBOK_DTO(
            "dto-lombok.tpl",
            "dto",
            "DTO",
            ".java"
    ),

    MAPPER(
            "mapper.tpl",
            "mapper",
            "Mapper",
            ".java"
    ),

    EXCEPTION(
            "exception.tpl",
            "exception",
            "Exception",
            ".java"
    );

    /**
     * Template file present inside
     * resources/templates/
     */
    private final String templateName;

    /**
     * Relative package/folder.
     *
     * Example:
     * entity
     * repository
     * dto/request
     */
    private final String packageFolder;

    /**
     * Suffix added to generated class.
     *
     * User
     * ->
     * UserRepository
     */
    private final String classSuffix;

    /**
     * Generated file extension.
     */
    private final String fileExtension;

    TemplateType(String templateName,
                 String packageFolder,
                 String classSuffix,
                 String fileExtension) {

        this.templateName = templateName;
        this.packageFolder = packageFolder;
        this.classSuffix = classSuffix;
        this.fileExtension = fileExtension;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getPackageFolder() {
        return packageFolder;
    }

    public String getClassSuffix() {
        return classSuffix;
    }

    public String getFileExtension() {
        return fileExtension;
    }
    public String getClassName(String entityName) {
        return entityName + classSuffix;
    }


    /**
     * Returns generated filename.
     *
     * Example:
     * User
     * ->
     * UserRepository.java
     */
//    public String getFileName(String className) {
//        return className + classSuffix + fileExtension;
//    }
    public String getFileName(String entityName) {
        return getClassName(entityName) + fileExtension;
    }
}