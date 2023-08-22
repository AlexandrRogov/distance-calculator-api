# Используем образ OpenJDK 17 как базовый образ для запуска
FROM openjdk:17

WORKDIR /app

# Копируем JAR-файл из этапа сборки
COPY  build/libs/*.jar /app/distance-calculator-api.jar

# Открываем порт, на котором будет работать ваше приложение
EXPOSE 8080

# Запускаем ваше Java 17 приложение при старте контейнера
CMD ["java", "-jar", "distance-calculator-api.jar"]