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

# Variables de ambiente
ENV COSMOSDB_URI=${COSMOSDB_URI}
ENV COSMOSDB_KEY=${COSMOSDB_KEY}
ENV DATABASE_URL=${DATABASE_URL}
ENV DATABASE_DRIVER_CLASS_NAME=${DATABASE_DRIVER_CLASS_NAME}
ENV DATABASE_USERNAME=${DATABASE_USERNAME}
ENV DATABASE_PASSWORD=${DATABASE_PASSWORD}

ENTRYPOINT ["java","-jar","/app/app.jar"]