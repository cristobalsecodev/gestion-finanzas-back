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
    url: jdbc:postgresql://{URL}:{PUERTO}/{BBDD}
    username: USER_NAME
    password: PASSWORD
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
alpha:
  vantage:
    api:
      key: API_KEY
    baseUrl: https://www.alphavantage.co/query
```
