# Stage 1: Build stage
FROM gradle AS builder
WORKDIR /app

# Copy only the gradle files first to leverage Docker layer caching for dependencies
COPY build.gradle settings.gradle ./
COPY src ./src

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$PATH:$JAVA_HOME/bin

# Build the application (skipping tests for speed, optional)
RUN gradle clean build -x test

# Stage 2: Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy only the compiled JAR from the builder stage
# Adjust the path to your JAR (usually build/libs/your-app-name.jar)
COPY --from=builder /app/build/libs/*.jar app.jar

# Define the entry point to run the application
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "-Dserver.port=8081", "app.jar"]
