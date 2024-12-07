FROM openjdk:17-jdk-alpine

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app-api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/app-api-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080