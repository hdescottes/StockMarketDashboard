val springBootVersion by extra { "3.2.1" }
val postgresVersion by extra { "42.7.1" }
val jsr310Version by extra { "2.16.1" }
val liquibaseVersion by extra { "4.25.1" }
val apacheLang3Version by extra { "3.14.0" }
val zonkyEmbeddedDbVersion by extra { "2.5.0" }
val zonkyPostgresVersion by extra { "2.0.6" }

buildscript {
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:3.3.3")
	}
}

plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.project"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${jsr310Version}")
	implementation("org.liquibase:liquibase-core:${liquibaseVersion}")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose:$springBootVersion")
	runtimeOnly("org.postgresql:postgresql:${postgresVersion}")
	testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
	testImplementation("org.apache.commons:commons-lang3:$apacheLang3Version")
	testImplementation("io.zonky.test:embedded-database-spring-test:$zonkyEmbeddedDbVersion")
	testImplementation("io.zonky.test:embedded-postgres:$zonkyPostgresVersion")
}
