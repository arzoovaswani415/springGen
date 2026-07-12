package com.arzoovaswani.springgen.config;

import com.arzoovaswani.springgen.model.BuildTool;
import com.arzoovaswani.springgen.model.ConfigFormat;
import com.arzoovaswani.springgen.model.JavaVersion;
import com.arzoovaswani.springgen.model.SpringBootVersion;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores all information collected from the user
 * required to generate a Spring Boot project.
 */
public class ProjectConfig {

    private String projectName;

    private String groupId;

    private String artifactId;

    private String packageName;

    private JavaVersion javaVersion;

    private SpringBootVersion springBootVersion;

    private BuildTool buildTool;

    private ConfigFormat configFormat;


    /**
     * Spring Initializr dependency IDs.
     *
     * Example:
     * web
     * data-jpa
     * security
     * mysql
     * data-redis
     */
    private List<String> dependencies = new ArrayList<>();

    public ProjectConfig() {
    }

    // ----------------------------
    // Getters & Setters
    // ----------------------------

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
    public ConfigFormat getConfigFormat() {
        return configFormat;
    }
    public void setConfigFormat(ConfigFormat configFormat) {
        this.configFormat = configFormat;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public JavaVersion getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(JavaVersion javaVersion) {
        this.javaVersion = javaVersion;
    }

    public SpringBootVersion getSpringBootVersion() {
        return springBootVersion;
    }

    public void setSpringBootVersion(SpringBootVersion springBootVersion) {
        this.springBootVersion = springBootVersion;
    }

    public BuildTool getBuildTool() {
        return buildTool;
    }

    public void setBuildTool(BuildTool buildTool) {
        this.buildTool = buildTool;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }
    public boolean hasDependency(String dependency) {
        return dependencies != null &&
                dependencies.contains(dependency);
    }
    public boolean isJpaProject() {
        return hasDependency("data-jpa");
    }

    public boolean isJdbcProject() {
        return hasDependency("jdbc");
    }
    public boolean hasMySql() {
        return hasDependency("mysql");
    }

    public boolean hasPostgreSql() {
        return hasDependency("postgresql");
    }

    public boolean hasMongoDb() {
        return hasDependency("mongodb");
    }
    public boolean hasSecurity() {
        return hasDependency("security");
    }
    public boolean hasLombok() {
        return hasDependency("lombok");
    }

    public boolean hasValidation() {
        return hasDependency("validation");
    }
    public boolean hasKafka() {
        return hasDependency("kafka");
    }
    public boolean hasRedis() {
        return hasDependency("redis");
    }
    public boolean hasOpenApi() {
        return hasDependency("openapi");
    }

    @Override
    public String toString() {

        return """

                ----------------------------------------
                      SpringGen Project Configuration
                ----------------------------------------

                Project Name      : %s
                Group ID          : %s
                Artifact ID       : %s
                Package Name      : %s

                Java Version      : %s
                Spring Boot       : %s
                Build Tool        : %s

                Dependencies      : %s

                ----------------------------------------
                """
                .formatted(
                        projectName,
                        groupId,
                        artifactId,
                        packageName,
                        javaVersion,
                        springBootVersion,
                        buildTool,
                        dependencies
                );
    }
}