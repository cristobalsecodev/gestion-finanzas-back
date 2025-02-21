# BASE DE DATOS #
https://supabase.com
# SWAGGER #
Swagger UI: http://localhost:8080/swagger-ui.html  
Documentación JSON: http://localhost:8080/v3/api-docs

# Archivo de migración para crear la BBDD #
migracion.sql

# API de conexión #
Conversión de divisas: https://v6.exchangerate-api.com

# Estructura del application.yaml (Crearlo dentro de la carpeta "resources") #
```yaml
spring:
  datasource:
    url: jdbc:postgresql://${URL_DATABASE}:${DATABASE_PORT}/${NAME_DATABASE}
    username: ${DATABASE_USER}
    password: ${DATABASE_PASSWORD}
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  mail:
    host: smtp.gmail.com
    port: ${EMAIL_PORT}
    username: ${EMAIL}
    password: ${EMAIL_PASSWORD}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
exchangeRate:
  api:
    key: ${EXCHANGE_RATE_API_KEY}
  baseUrl: https://v6.exchangerate-api.com
security:
  jwt:
    secret-key: ${SECRET_JWT_KEY}
    expiration-time: ${JWT_EXPIRATION_TIME_IN_MS}
testUser:
  email: test@example.com
  pass: hashed_password
```
