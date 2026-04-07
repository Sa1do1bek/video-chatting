# 1. Этап сборки (Build stage)
# Используем более свежий образ Gradle (например, 8.14 или выше)
FROM gradle:9.0-jdk17 AS build
WORKDIR /app

# Копируем файлы конфигурации
COPY build.gradle settings.gradle ./

# Скачиваем зависимости
RUN gradle dependencies --no-daemon

# Копируем исходный код
COPY src ./src

# Собираем проект
RUN gradle clean bootJar --no-daemon

# 2. Этап запуска (Run stage)
FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8081

CMD ["sh", "-c", "java -Dserver.port=${PORT:-8081} -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-default} -jar app.jar"]