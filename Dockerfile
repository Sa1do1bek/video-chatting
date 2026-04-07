# 1. Этап сборки (Build stage)
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app

# Сначала копируем только pom.xml, чтобы закэшировать зависимости
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Теперь копируем исходный код
COPY src ./src

# Собираем проект (теперь JAR создастся внутри контейнера)
RUN mvn clean package -DskipTests

# 2. Этап запуска (Run stage)
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Копируем JAR, созданный на этапе 'build'
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

# Запуск с поддержкой переменной PORT (полезно для облачных сервисов)
CMD ["sh", "-c", "java -Dserver.port=${PORT:-8081} -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-default} -jar app.jar"]