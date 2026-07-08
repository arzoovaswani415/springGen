package com.arzoovaswani.springgen.config;

import com.arzoovaswani.springgen.Model.BuildTool;
import com.arzoovaswani.springgen.Model.JavaVersion;
import com.arzoovaswani.springgen.Model.SpringBootVersion;

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