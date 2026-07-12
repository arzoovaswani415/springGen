package com.arzoovaswani.springgen.model;

public enum ConfigFormat {

    PROPERTIES("properties"),

    YAML("yaml");

    private final String id;

    ConfigFormat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}