# Dockerfile

FROM gradle:latest AS build

# Copy the Gradle wrapper and build files
COPY gradlew gradle/ /app/ 
COPY build.gradle /app/ 
COPY settings.gradle /app/ 
COPY src /app/src

# Set the working directory
WORKDIR /app

# Build the project using Gradle
RUN ./gradlew build

# Final stage: Create a minimal image with the jar
FROM openjdk:11-jre-slim

# Copy the built jar from the previous stage
COPY --from=build /app/build/libs/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]