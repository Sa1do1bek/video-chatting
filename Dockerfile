# Multi-Stage Dockerfile for Spring Boot

# Start with a base image that is lightweight and secure
FROM openjdk:17-alpine AS build

# Set the working directory
WORKDIR /app

# Copy only the necessary files for dependency resolution
COPY pom.xml .
COPY src ./src

# Resolve dependencies
RUN ./mvnw dependency:go-offline -B

# Package the application
RUN ./mvnw package -DskipTests

# Start a new stage for the final image
FROM openjdk:17-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application on a specific port
EXPOSE 8080

# Define health check to ensure the app is running
HEALTHCHECK --interval=30s --timeout=10s --retries=3 CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]