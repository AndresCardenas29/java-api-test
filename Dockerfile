# Multi-stage build para optimizar el tamaño de la imagen

# Etapa 1: Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar archivos de configuración de Maven primero (para aprovechar cache)
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Descargar dependencias (esta capa se cachea si pom.xml no cambia)
# Usamos resolve y resolve-plugins además de go-offline para asegurar que se descarguen la mayoría de dependencias
RUN mvn dependency:resolve -B && \
    mvn dependency:resolve-plugins -B && \
    mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar la aplicación
RUN mvn clean package -DskipTests -B

# Etapa 2: Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear usuario no-root por seguridad
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto configurable (por defecto 8080)
ARG SERVER_PORT=8080
ENV SERVER_PORT=${SERVER_PORT}
EXPOSE ${SERVER_PORT}

# Variables de entorno opcionales
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Comando de inicio
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
