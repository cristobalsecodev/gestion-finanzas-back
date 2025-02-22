FROM maven:3.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/gestion-finanzas-back-1.0.0.jar /app/gestion-finanzas-back-1.0.0.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/gestion-finanzas-back-1.0.0.jar"]