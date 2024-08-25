rootProject.name = "StockMarketDashboard"
include("app-api", "app-ui")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("spring-boot", "3.2.1")
            library("starter-web", "org.springframework.boot", "spring-boot-starter-web").versionRef("spring-boot")
            library("starter-data-jpa", "org.springframework.boot", "spring-boot-starter-data-jpa").versionRef("spring-boot")
            library("starter-actuator", "org.springframework.boot", "spring-boot-starter-actuator").versionRef("spring-boot")
            library("docker-compose", "org.springframework.boot", "spring-boot-docker-compose").versionRef("spring-boot")
            library("starter-test", "org.springframework.boot", "spring-boot-starter-test").versionRef("spring-boot")
            library("jsr310", "com.fasterxml.jackson.datatype", "jackson-datatype-jsr310").version("2.17.2")
            library("liquibase", "org.liquibase", "liquibase-core").version("4.29.1")
            library("postgresql", "org.postgresql", "postgresql").version("42.7.4")
            library("lang3", "org.apache.commons", "commons-lang3").version("3.14.0")
            library("zonkyEmbeddedDb", "io.zonky.test", "embedded-database-spring-test").version("2.5.1")
            library("zonkyPostgres", "io.zonky.test", "embedded-postgres").version("2.0.7")
            bundle("spring-boot", listOf("starter-web", "starter-data-jpa", "starter-actuator"))
            plugin("spring-boot", "org.springframework.boot").versionRef("spring-boot")
            plugin("dependency-management", "io.spring.dependency-management").version("1.1.3")
        }
    }
}
