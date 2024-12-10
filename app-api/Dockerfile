FROM gradle:8.10-alpine AS builder
WORKDIR /app-api
COPY gradle gradle
COPY app-api .
RUN gradle clean build -x test

FROM eclipse-temurin:21.0.2_13-jre-alpine AS runner
RUN apk --no-cache add bash curl
RUN mkdir /app-api
RUN addgroup -S spring && adduser -S spring -G spring
WORKDIR /app-api
COPY --from=builder app-api/build/libs/app-api-0.0.1-SNAPSHOT.jar app-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","app-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080