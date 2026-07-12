package com.arzoovaswani.springgen.service;

import com.arzoovaswani.springgen.config.ProjectConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PropertiesConfigWriter {

    public void generate(Path projectRoot,
                         ProjectConfig config)
            throws IOException {

        Path propertiesFile = projectRoot
                .resolve("src")
                .resolve("main")
                .resolve("resources")
                .resolve("application.properties");

        StringBuilder builder = new StringBuilder();

        /*
         * -------------------------------------------------
         * Application
         * -------------------------------------------------
         */

        builder.append("spring.application.name=")
                .append(config.getArtifactId())
                .append("\n\n");

        /*
         * -------------------------------------------------
         * Database
         * -------------------------------------------------
         */

        if (config.hasMySql()) {

            builder.append("""
                    # MySQL Configuration
                    spring.datasource.url=jdbc:mysql://localhost:3306/your_database
                    spring.datasource.username=root
                    spring.datasource.password=change_me
                    
                    """);

        } else if (config.hasPostgreSql()) {

            builder.append("""
                    # PostgreSQL Configuration
                    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
                    spring.datasource.username=postgres
                    spring.datasource.password=change_me
                    
                    """);

        } else if (config.hasMongoDb()) {

            builder.append("""
                    # MongoDB Configuration
                    spring.data.mongodb.uri=mongodb://localhost:27017/your_database
                    
                    """);

        }

        /*
         * -------------------------------------------------
         * JPA
         * -------------------------------------------------
         */

        if (config.isJpaProject()) {

            builder.append("""
                    spring.jpa.hibernate.ddl-auto=update
                    spring.jpa.show-sql=true
                    spring.jpa.properties.hibernate.format_sql=true
                    
                    """);

        }

        /*
         * -------------------------------------------------
         * Redis
         * -------------------------------------------------
         */

        if (config.hasRedis()) {

            builder.append("""
                    # Redis Configuration
                    spring.data.redis.host=localhost
                    spring.data.redis.port=6379
                    
                    """);

        }

        /*
         * -------------------------------------------------
         * Kafka
         * -------------------------------------------------
         */

        if (config.hasKafka()) {

            builder.append("""
                    # Kafka Configuration
                    spring.kafka.bootstrap-servers=localhost:9092
                    
                    """);

        }

        /*
         * -------------------------------------------------
         * Security
         * -------------------------------------------------
         */

        if (config.hasSecurity()) {

            builder.append("""
                    # Spring Security Configuration
                    
                    """);

        }

        Files.writeString(
                propertiesFile,
                builder.toString()
        );

    }

}