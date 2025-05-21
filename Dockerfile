FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
EXPOSE 8080
LABEL maintainer="carlos gangazha"
WORKDIR /app
COPY --from=build /app/target/Security-0.0.1-SNAPSHOT.jar security.jar
ENTRYPOINT ["java","-jar","/security.jar"]