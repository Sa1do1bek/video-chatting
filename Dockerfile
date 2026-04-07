# 1. Этап сборки (Build stage)
# Используем JDK 21, так как Spring Boot 4 требует именно эту версию
FROM gradle:9.0-jdk21 AS build
WORKDIR /app

# Копируем файлы сборки
# ВАЖНО: сначала копируем только конфиги, чтобы закешировать зависимости
COPY build.gradle settings.gradle ./
COPY src ./src

# Собираем проект (пропускаем тесты для ускорения деплоя на Render)
RUN gradle clean bootJar --no-daemon -DskipTests

# 2. Этап запуска (Run stage)
# Используем современный и легкий образ Eclipse Temurin вместо устаревшего openjdk
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Копируем только исполняемый JAR
# В Gradle результат сборки лежит в build/libs/
COPY --from=build /app/build/libs/*.jar app.jar

# Порт для Render (он обычно дает его через переменную среды PORT)
EXPOSE 8081

# Запуск с учетом динамического порта и профилей
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8081} -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE:-default} -jar app.jar"]
