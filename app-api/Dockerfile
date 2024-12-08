FROM eclipse-temurin:21.0.2_13-jre-alpine
RUN apk --no-cache add bash curl
RUN mkdir /app
RUN addgroup -S spring && adduser -S spring -G spring
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080