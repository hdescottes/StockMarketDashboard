buildscript {
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath(libs.gradle.plugin)
	}
}

plugins {
	java
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.dependency.management)
}

group = "com.project"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

tasks.named<Test>("test") {
	useJUnitPlatform()
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.bundles.spring.boot)
	implementation(libs.jsr310)
	implementation(libs.liquibase)
	developmentOnly(libs.docker.compose)
	runtimeOnly(libs.postgresql)
	testImplementation(libs.starter.test)
	testImplementation(libs.lang3)
	testImplementation(libs.bundles.zonky)
	testImplementation(libs.archunit)
}
