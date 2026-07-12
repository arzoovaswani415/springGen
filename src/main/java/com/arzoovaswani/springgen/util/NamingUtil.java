package com.arzoovaswani.springgen.util;

public final class NamingUtil {

    private NamingUtil() {
    }

    /**
     * Converts user input into a valid Java class name.
     *
     * Examples:
     *
     * user
     * -> User
     *
     * USER
     * -> User
     *
     * user_profile
     * -> UserProfile
     *
     * user-profile
     * -> UserProfile
     */
    public static String toPascalCase(String input) {

        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Class name cannot be empty.");
        }

        StringBuilder builder = new StringBuilder();

        String[] parts = input
                .trim()
                .split("[_\\-\\s]+");

        for (String part : parts) {

            if (part.isBlank()) {
                continue;
            }

            part = part.toLowerCase();

            builder.append(
                    Character.toUpperCase(part.charAt(0))
            );

            if (part.length() > 1) {
                builder.append(part.substring(1));
            }

        }

        return builder.toString();

    }
    public static String toKebabCase(String value) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {

            char current = value.charAt(i);

            if (Character.isUpperCase(current) && i > 0) {
                builder.append("-");
            }

            builder.append(Character.toLowerCase(current));
        }

        return builder.toString();
    }

}