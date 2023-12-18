val springBootVersion by extra { "3.1.5" }
val postgresVersion by extra { "42.7.1" }
val jsr310Version by extra { "2.16.0" }
val liquibaseVersion by extra { "4.25.0" }
val apacheLang3Version by extra { "3.13.0" }

buildscript {
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:3.1.5")
	}
}

plugins {
	java
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.project"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}

tasks.named("bootRun") {
	dependsOn("dockerComposeUp")
}

tasks.register<Exec>("dockerComposeUp") {
	commandLine("docker-compose", "-f", "docker/docker-compose.yml", "up", "-d")
}

tasks.register<Exec>("dockerComposeDown") {
	commandLine("docker-compose", "-f", "docker/docker-compose.yml", "down")
}

tasks.register<Exec>("fullApp") {
	dependsOn("bootRun")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.boot:spring-boot-starter:$springBootVersion")
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${jsr310Version}")
	implementation("org.liquibase:liquibase-core:${liquibaseVersion}")
	runtimeOnly("org.postgresql:postgresql:${postgresVersion}")
	testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
	testImplementation("org.apache.commons:commons-lang3:$apacheLang3Version")
}
