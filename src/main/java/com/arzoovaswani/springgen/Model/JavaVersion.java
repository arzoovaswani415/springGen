package com.arzoovaswani.springgen.Model;

/**
 * Supported Java versions.
 */
public enum JavaVersion {

    JAVA_17("17"),
    JAVA_21("21"),
    JAVA_24("24");

    private final String version;

    JavaVersion(String version) {
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