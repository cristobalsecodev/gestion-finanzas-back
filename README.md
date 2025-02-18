# BASE DE DATOS #
https://supabase.com
# SWAGGER #
Swagger UI: http://localhost:8080/swagger-ui.html  
Documentación JSON: http://localhost:8080/v3/api-docs

# Archivo de migración para crear la BBDD #
migracion.sql

# Estructura del application.yaml (Crearlo dentro de la carpeta "resources") #
```yaml
spring:
  datasource:
    url: jdbc:postgresql://{URL}:{PORT}/{BBDD}
    username: USER_NAME
    password: PASSWORD
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  mail:
    host: smtp.gmail.com
    port: 587
    username: YOUR_EMAIL
    password: YOUR_PASSWORD
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
exchangeRate:
  api:
    key: API_KEY
  baseUrl: https://v6.exchangerate-api.com
security:
  jwt:
    secret-key: SECRET_KEY
    expiration-time: EXP_TIME_IN_MS
testUser:
  email: test@example.com
  pass: hashed_password
```
