FROM gradle:9.0-jdk21 AS build
WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle clean bootJar --no-daemon -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8081} -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-default} -jar app.jar"]
