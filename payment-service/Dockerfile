
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем JAR-файл в контейнер
COPY target/payment-service-0.0.1-SNAPSHOT.jar app.jar

# Указываем порт, который будет использоваться
EXPOSE 8081

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]