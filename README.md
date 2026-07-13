# 🚀 SpringGen

> AI-Powered Spring Boot Project Generator

SpringGen is a command-line tool that automates Spring Boot project setup and boilerplate generation. Instead of manually creating folders, packages, entities, repositories, services, controllers, DTOs, and configuration files, SpringGen scaffolds a clean project structure in seconds.

It builds on top of **Spring Initializr** while adding developer productivity features such as dependency-aware code generation, configurable project templates, and reusable project metadata.

---

## ✨ Features

### Project Generation

- Interactive CLI wizard
- Spring Initializr integration
- Gradle & Maven support
- Java version selection
- Spring Boot version selection
- Properties / YAML configuration selection
- Dependency selection
- Automatic package generation
- Standard Spring Boot folder structure
- `.springgen.properties` metadata generation
- Dependency-aware `application.properties` / `application.yml`

---

### Code Generation

Generate code after project creation using CLI commands.

Supported generators:

- Entity
- Repository (JPA / JDBC aware)
- DTO (Lombok aware)
- Service
- Controller
- Mapper
- Exception
- Complete CRUD module

---

### Smart Generation

SpringGen automatically adapts generated code based on project dependencies.

Examples:

- Generates `JpaRepository` when Spring Data JPA is selected.
- Generates JDBC repository class for Spring JDBC projects.
- Generates Lombok DTOs when Lombok is available.
- Generates application configuration based on selected dependencies.
- Supports both `.properties` and `.yml` configuration formats.

---

## 📦 Installation

Clone the repository

```bash
git clone https://github.com/<your-username>/SpringGen.git
```

Build the project

```bash
./gradlew shadowJar
```

Install globally

```bash
cp build/libs/springGen.jar ~/bin/
```

Run

```bash
springGen --help
```

---

## 🚀 Usage

### Create a Spring Boot project

```bash
springGen create
```

SpringGen launches an interactive wizard.

Example:

```
Project Name
Group Id
Artifact Id
Package Name
Java Version
Spring Boot Version
Build Tool
Configuration Format
Dependencies
```

---

### Generate Entity

```bash
springGen entity User
```

---

### Generate Repository

```bash
springGen repository User
```

Automatically generates

- JPA Repository
- or JDBC Repository

depending on project dependencies.

---

### Generate DTO

```bash
springGen dto User
```

Automatically generates

- Lombok DTO
- or Plain DTO

depending on project dependencies.

---

### Generate Service

```bash
springGen service User
```

---

### Generate Controller

```bash
springGen controller User
```

---

### Generate Complete CRUD Module

```bash
springGen crud User
```

Generates

```
entity/
repository/
dto/
service/
controller/
mapper/
exception/
```

---

## 📁 Generated Project Structure

```
inventory
│
├── src
│   └── main
│       ├── java
│       │   └── com/example/inventory
│       │       ├── controller
│       │       ├── dto
│       │       ├── entity
│       │       ├── exception
│       │       ├── mapper
│       │       ├── repository
│       │       ├── service
│       │       ├── config
│       │       └── util
│       │
│       └── resources
│           ├── application.properties
│           └── application.yml
│
├── .springgen.properties
└── build.gradle / pom.xml
```

---

## ⚙️ Supported Dependencies

SpringGen currently supports

- Spring Web
- Spring Data JPA
- Spring JDBC
- Spring Security
- Validation
- Lombok
- MySQL
- PostgreSQL
- MongoDB
- Redis
- Kafka

---

## 🏗 Architecture

SpringGen follows a modular architecture.

```
CLI
 │
 ▼
Project Wizard
 │
 ▼
Project Generation Service
 ├── Initializr Client
 ├── ZIP Extractor
 ├── Folder Generator
 ├── Application Config Generator
 ├── Template Engine
 └── Project Config Writer
```

The template engine generates source files using reusable templates and placeholder replacement, while project metadata is stored in `.springgen.properties` to support future code generation.

---

## 🛣 Roadmap

Future enhancements include

- Docker support
- Swagger/OpenAPI generation
- Spring Security configuration
- JWT scaffolding
- GitHub Actions workflow generation
- Test generation
- CI/CD templates
- Additional database support

---

Built to simplify Spring Boot project development and eliminate repetitive boilerplate.
