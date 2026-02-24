# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /src/advshop
COPY . .

# Install minimal dependencies for Gradle wrapper
RUN apk add --no-cache bash curl

# Build Spring Boot jar
RUN ./gradlew clean bootJar

# Stage 2: Run
FROM eclipse-temurin:21-jre-alpine AS runner

# Create non-root user
ARG USER_NAME=advshop
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

RUN addgroup -g ${USER_GID} ${USER_NAME} \
 && adduser -h /opt/advshop -D -u ${USER_UID} -G ${USER_NAME} ${USER_NAME}

USER ${USER_NAME}
WORKDIR /opt/advshop

# Copy the built jar
COPY --from=builder --chown=${USER_UID}:${USER_GID} /src/advshop/build/libs/*.jar app.jar

# Fly.io dynamic port
ENV SERVER_PORT=$PORT
EXPOSE 8080

# Limit JVM memory for free tier
ENTRYPOINT ["java", "-Xmx128m", "-Xms64m", "-jar", "app.jar"]