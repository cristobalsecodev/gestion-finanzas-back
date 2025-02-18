# Usa una imagen base que soporte Java 21
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR compilado dentro del contenedor
COPY target/gestion-finanzas-back-1.0.0.jar /app/gestion-finanzas-back-1.0.0.jar

# Exponer el puerto que tu aplicación utilizará (el puerto por defecto de Spring Boot es 8080)
EXPOSE 8080

# Comando para ejecutar tu aplicación Spring Boot con Java 21
CMD ["java", "-jar", "/app/gestion-finanzas-back-1.0.0.jar"]
