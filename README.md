# Gestión de Finanzas - Backend

## Descripción
El backend de la aplicación Gestión de Finanzas está desarrollado con **Spring Boot**, proporcionando una API robusta para gestionar ingresos, gastos, categorías y subcategorías. Actualmente utiliza **Supabase** como base de datos, con planes de migración a **Railway**. Además, cuenta con integración de **Swagger** para documentación de la API y una conexión a la API de **ExchangeRate** para conversión de divisas.

## Funcionalidades Principales
- **Gestión de Ingresos y Gastos**: API para registrar y administrar transacciones financieras.
- **Categorías y Subcategorías**: Permite clasificar los movimientos financieros.
- **Soporte para Múltiples Divisas**: Conversión de divisas a través de la API de ExchangeRate.
- **Documentación con Swagger**:
  - [Swagger UI](http://localhost:8080/swagger-ui.html)
  - [Documentación JSON](http://localhost:8080/v3/api-docs)
- **Autenticación y Seguridad**: Implementación de JWT para autenticación segura.

## Tecnologías Utilizadas
- **Java 21** (Lenguaje de programación)
- **Spring Boot** (Framework principal del backend)
- **Spring Data JPA** (Manejo de persistencia de datos)
- **PostgreSQL** (Base de datos en Supabase, con migración futura a Railway)
- **Swagger** (Generación de documentación de API)
- **Maven** (Gestor de dependencias)
- **JWT** (Autenticación segura con JSON Web Token)
- **Railway** (Plataforma de despliegue)

## Configuración de la Base de Datos
Actualmente, el backend utiliza **Supabase** como base de datos, pero está planeada su migración a **Railway**.

- **Plataforma actual**: [Supabase](https://supabase.com)
- **Futura migración**: [Railway](https://railway.app)
- **Archivo de migración**: `migracion.sql`

## Configuración del Archivo `application.yaml`
Para configurar correctamente el backend, se debe crear el archivo `application.yaml` dentro de la carpeta `resources` con la siguiente estructura:

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

## Instalación y Ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/cristobalsecodev/gestion-finanzas-back.git
   ```
2. Navegar al directorio del proyecto:
   ```bash
   cd gestion-finanzas-back
   ```
3. Construir el proyecto con Maven:
   ```bash
   mvn clean install
   ```
4. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```
5. Acceder a la API en `http://localhost:8080`

## Contribución
Si deseas contribuir a este proyecto, sigue estos pasos:
1. Realiza un fork del repositorio.
2. Crea una rama con tu funcionalidad:
   ```bash
   git checkout -b nueva-funcionalidad
   ```
3. Realiza tus cambios y sube los commits:
   ```bash
   git commit -m 'Añadir nueva funcionalidad'
   ```
4. Sube los cambios a tu fork:
   ```bash
   git push origin nueva-funcionalidad
   ```
5. Abre un Pull Request en este repositorio.

## Licencia
Este proyecto está bajo la licencia MIT.

---
Si tienes alguna sugerencia o mejora, ¡no dudes en contribuir! 🚀

