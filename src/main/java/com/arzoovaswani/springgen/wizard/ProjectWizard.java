package com.arzoovaswani.springgen.wizard;

import com.arzoovaswani.springgen.config.ProjectConfig;
import com.arzoovaswani.springgen.config.DependencySelector;
import com.arzoovaswani.springgen.Model.BuildTool;
import com.arzoovaswani.springgen.Model.JavaVersion;
import com.arzoovaswani.springgen.Model.SpringBootVersion;
import com.arzoovaswani.springgen.utils.ValidationUtil;


import java.io.InputStream;
import java.util.Scanner;

public class ProjectWizard {

    private final Scanner scanner;
    private final DependencySelector dependencySelector;

    public ProjectWizard(InputStream input) {

        this.scanner = new Scanner(input);

        this.dependencySelector = new DependencySelector(scanner);

    }

    public ProjectConfig start() {

        printWelcome();

        ProjectConfig config = new ProjectConfig();

        String projectName = askProjectName();

        config.setProjectName(projectName);

        String groupId = askGroupId();

        config.setGroupId(groupId);

        config.setArtifactId(
                askArtifactId(projectName)
        );

        config.setPackageName(
                askPackageName(groupId, projectName)
        );

        config.setJavaVersion(askJavaVersion());
        config.setSpringBootVersion(askSpringBootVersion());
        config.setBuildTool(askBuildTool());

        config.setDependencies(
                dependencySelector.selectDependencies()
        );

        return config;
    }

    private void printWelcome() {

        System.out.println("""
                
====================================================
                🚀 Welcome to SpringGen
====================================================

Let's create your Spring Boot project.

Just answer a few questions.
I'll generate the boilerplate for you.

Press ENTER whenever you're ready.

====================================================
""");

        scanner.nextLine();
    }

    private String askProjectName() {

        while (true) {

            System.out.print("Project Name : ");

            String input = scanner.nextLine().trim();

            if (ValidationUtil.isValidProjectName(input)) {
                return input;
            }

            System.out.println("❌ Project name cannot be empty.\n");
        }
    }

    private String askGroupId() {

        while (true) {

            System.out.print("Group Id [com.example] : ");

            String input = scanner.nextLine().trim();

            if (input.isBlank()) {
                input = "com.example";
            }

            input = ValidationUtil.formatPackageName(input);

            if (ValidationUtil.isValidGroupId(input)) {
                return input;
            }

            System.out.println("❌ Invalid Group Id.\n");
        }
    }
    /**
     * Generates a valid artifact id from project name.
     *
     * Expense Tracker
     * ->
     * expense-tracker
     */
    public static String generateArtifactId(String projectName) {

        if (projectName == null) {
            return "";
        }

        return projectName
                .trim()
                .toLowerCase()
                .replaceAll("\\s+", "-")
                .replaceAll("[^a-z0-9-]", "");

    }
    private String askArtifactId(String projectName) {

        String suggestedArtifact = generateArtifactId(projectName);

        while (true) {

            System.out.println();

            System.out.println(
                    "Suggested Artifact Id : " + suggestedArtifact
            );

            System.out.print(
                    "Press ENTER to accept or type another Artifact Id : "
            );

            String input = scanner.nextLine().trim();

            if (input.isBlank()) {
                return suggestedArtifact;
            }

            input = ValidationUtil.formatArtifactId(input);

            if (!input.isBlank()) {
                return input;
            }

            System.out.println("Invalid Artifact Id.\n");

        }

    }
    /**
     * Expense Tracker
     * ->
     * expensetracker
     */
    public static String generatePackageSuffix(String projectName) {

        if (projectName == null) {
            return "";
        }

        return projectName
                .trim()
                .toLowerCase()
                .replaceAll("[^a-z0-9]", "");

    }
    private String askPackageName(String groupId,
                                  String projectName) {

        String suggestedPackage =
                groupId + "." +
                        generatePackageSuffix(projectName);

        while (true) {

            System.out.println();

            System.out.println(
                    "Suggested Package Name : "
                            + suggestedPackage
            );

            System.out.print(
                    "Press ENTER to accept or type another Package Name : "
            );

            String input = scanner.nextLine().trim();

            if (input.isBlank()) {

                return suggestedPackage;

            }

            input = ValidationUtil.formatPackageName(input);

            if (ValidationUtil.isValidPackageName(input)) {

                return input;

            }

            System.out.println("Invalid Package Name.\n");

        }

    }

    private JavaVersion askJavaVersion() {

        while (true) {

            System.out.println("""
                    
Java Version

1. Java 17
2. Java 21 (Recommended)
3. Java 24

Press ENTER for Java 21
""");

            System.out.print("Choice : ");

            String input = scanner.nextLine().trim();

            if (input.isBlank()) {
                return JavaVersion.JAVA_21;
            }

            switch (input) {

                case "1":
                    return JavaVersion.JAVA_17;

                case "2":
                    return JavaVersion.JAVA_21;

                case "3":
                    return JavaVersion.JAVA_24;

                default:
                    System.out.println("❌ Please choose 1, 2 or 3.\n");
            }
        }
    }

    private SpringBootVersion askSpringBootVersion() {

        while (true) {

            System.out.println("""
                    
Spring Boot Version

1. 3.5.4 (Latest Stable)
2. 3.5.3
3. 3.4.8

Press ENTER for latest stable
""");

            System.out.print("Choice : ");

            String input = scanner.nextLine().trim();

            if (input.isBlank()) {
                return SpringBootVersion.V3_5_4;
            }

            switch (input) {

                case "1":
                    return SpringBootVersion.V3_5_4;

                case "2":
                    return SpringBootVersion.V3_5_3;

                case "3":
                    return SpringBootVersion.V3_4_8;

                default:
                    System.out.println("❌ Please choose 1, 2 or 3.\n");
            }
        }
    }

    private BuildTool askBuildTool() {

        while (true) {

            System.out.println("""
                    
Build Tool

1. Gradle (Recommended)
2. Maven

Press ENTER for Gradle
""");

            System.out.print("Choice : ");

            String input = scanner.nextLine().trim();

            if (input.isBlank()) {
                return BuildTool.GRADLE;
            }

            switch (input) {

                case "1":
                    return BuildTool.GRADLE;

                case "2":
                    return BuildTool.MAVEN;

                default:
                    System.out.println("❌ Please choose 1 or 2.\n");
            }
        }
    }

}