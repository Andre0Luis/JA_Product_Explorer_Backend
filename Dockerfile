# Etapa de build
FROM maven:3.8.2-openjdk-17-slim AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Etapa de run
FROM ubuntu:22.04
WORKDIR /app

# Instalar Java
RUN apt-get update && apt-get install -y openjdk-17-jre

COPY --from=build /workspace/target/ja_product_explorer_backend-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]