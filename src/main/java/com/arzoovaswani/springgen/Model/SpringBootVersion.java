package com.arzoovaswani.springgen.Model;

/**
 * Supported Spring Boot versions.
 */
public enum SpringBootVersion {

    V3_5_4("3.5.4"),
    V3_5_3("3.5.3"),
    V3_4_8("3.4.8");

    private final String version;

    SpringBootVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return version;
    }
}