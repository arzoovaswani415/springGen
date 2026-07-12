package com.arzoovaswani.springgen.config;

import java.util.List;

public final class DependencyCatalog {

    private DependencyCatalog() {}

    public static final List<String> DEPENDENCIES = List.of(

            "web",

            "data-jpa",

            "security",

            "validation",

            "lombok",

            "mysql",

            "postgresql",

            "data-redis",

            "kafka",

            "jdbc",
            "mongodb"



    );

}