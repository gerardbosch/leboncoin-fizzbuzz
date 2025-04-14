#
# Build it with:
#   docker build -t leboncoin-fizzbuzz .
#
# or use Docker compose to build and run:
#   docker-compose up --build
#

# === Stage 1: Build the JAR
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean test bootJar --no-daemon

# === Stage 2: Runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
