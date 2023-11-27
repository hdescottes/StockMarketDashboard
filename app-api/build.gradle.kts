val springBootVersion by extra { "3.1.5" }
val redisJedisVersion by extra { "5.0.2" }
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

tasks.register("launchRedis") {
	doLast {
		val redisExePath = "./redis-server.exe"
		Runtime.getRuntime().exec(redisExePath)
	}
}

tasks.named("bootRun") {
	dependsOn("launchRedis")
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
	implementation("org.springframework.boot:spring-boot-starter-data-redis:$springBootVersion")
	implementation("redis.clients:jedis:$redisJedisVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
	testImplementation("org.apache.commons:commons-lang3:$apacheLang3Version")
}
