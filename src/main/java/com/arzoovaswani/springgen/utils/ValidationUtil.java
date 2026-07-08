package com.arzoovaswani.springgen.utils;

import java.util.regex.Pattern;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    private static final Pattern PACKAGE_PATTERN =
            Pattern.compile("^[a-z][a-z0-9]*(\\.[a-z][a-z0-9]*)*$");

    /*
     * ---------------------------
     * Project Name
     * ---------------------------
     */

    public static boolean isValidProjectName(String name) {

        return name != null && !name.trim().isEmpty();

    }

    /*
     * ---------------------------
     * Group Id
     * ---------------------------
     */

    public static boolean isValidGroupId(String groupId) {

        return isValidPackageName(groupId);

    }

    /*
     * ---------------------------
     * Package Name
     * ---------------------------
     */

    public static boolean isValidPackageName(String packageName) {

        if (packageName == null)
            return false;

        packageName = packageName.trim().toLowerCase();

        return PACKAGE_PATTERN.matcher(packageName).matches();

    }

    /*
     * ---------------------------
     * Artifact
     * ---------------------------
     */

    public static String formatArtifactId(String artifact) {

        artifact = artifact.trim().toLowerCase();

        artifact = artifact.replaceAll("\\s+", "-");

        artifact = artifact.replaceAll("[^a-z0-9-]", "");

        return artifact;

    }

    /*
     * ---------------------------
     * Package Formatting
     * ---------------------------
     */

    public static String formatPackageName(String packageName) {

        return packageName.trim().toLowerCase();

    }

    /*
     * ---------------------------
     * Integer Choice
     * ---------------------------
     */

    public static boolean isChoiceBetween(
            int choice,
            int min,
            int max
    ) {

        return choice >= min && choice <= max;

    }

}