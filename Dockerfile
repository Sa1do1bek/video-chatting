# 1. Этап сборки (Build stage)
FROM gradle:8.7-jdk17 AS build
WORKDIR /app

# Копируем файлы конфигурации Gradle для кэширования зависимостей
COPY build.gradle settings.gradle ./
# Если у вас есть папка gradle (wrapper), ее тоже стоит скопировать
# COPY gradle ./gradle
# COPY gradlew ./

# Скачиваем зависимости (опционально, ускоряет повторные сборки)
RUN gradle dependencies --no-daemon

# Копируем исходный код
COPY src ./src

# Собираем проект
RUN gradle clean bootJar --no-daemon

# 2. Этап запуска (Run stage)
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Копируем собранный jar из этапа build
# В Gradle результат сборки обычно лежит в build/libs/
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8081

# Запуск приложения
CMD ["sh", "-c", "java -Dserver.port=${PORT:-8081} -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-default} -jar app.jar"]