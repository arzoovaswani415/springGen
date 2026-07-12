package com.arzoovaswani.springgen.service;

import com.arzoovaswani.springgen.config.ProjectConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class YamlConfigWriter {

    public void generate(Path projectRoot,
                         ProjectConfig config)
            throws IOException {

        Path yamlFile = projectRoot
                .resolve("src")
                .resolve("main")
                .resolve("resources")
                .resolve("application.yml");

        StringBuilder builder = new StringBuilder();

        /*
         * -------------------------------------------------
         * Application
         * -------------------------------------------------
         */

        builder.append("""
                spring:
                  application:
                    name: """)
                .append(config.getArtifactId())
                .append("\n");

        /*
         * -------------------------------------------------
         * Database
         * -------------------------------------------------
         */

        if (config.hasMySql()) {

            builder.append("""
                
                  datasource:
                    url: jdbc:mysql://localhost:3306/your_database
                    username: root
                    password: change_me
                """);

        } else if (config.hasPostgreSql()) {

            builder.append("""
                
                  datasource:
                    url: jdbc:postgresql://localhost:5432/your_database
                    username: postgres
                    password: change_me
                """);

        } else if (config.hasMongoDb()) {

            builder.append("""
                
                  data:
                    mongodb:
                      uri: mongodb://localhost:27017/your_database
                """);

        }

        /*
         * -------------------------------------------------
         * JPA
         * -------------------------------------------------
         */

        if (config.isJpaProject()) {

            builder.append("""
                
                  jpa:
                    hibernate:
                      ddl-auto: update
                    show-sql: true
                    properties:
                      hibernate:
                        format_sql: true
                """);

        }

        /*
         * -------------------------------------------------
         * Redis
         * -------------------------------------------------
         */

        if (config.hasRedis()) {

            builder.append("""
                
                  data:
                    redis:
                      host: localhost
                      port: 6379
                """);

        }

        /*
         * -------------------------------------------------
         * Kafka
         * -------------------------------------------------
         */

        if (config.hasKafka()) {

            builder.append("""
                
                  kafka:
                    bootstrap-servers: localhost:9092
                """);

        }

        /*
         * -------------------------------------------------
         * Security
         * -------------------------------------------------
         */

        if (config.hasSecurity()) {

            builder.append("""
                
                  security:
                    # Configure security properties here
                """);

        }

        Files.writeString(
                yamlFile,
                builder.toString()
        );

    }

}