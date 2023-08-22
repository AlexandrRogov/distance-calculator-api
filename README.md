# Приложение для поиска расстояния по двум точкам на поверхности земли

Ограничения не более 10 запросов к за 10 мин на одну систему 
Выполненно с использование библиотеки buckets4j
[1]: https://bucket4j.com/

в фаиле resource/application.ym конфиг для настройки ограничений

```
buckets:
basedOnTimeForRefillingBucketUpToMax: PT10M
capacity: 10000
period: PT10M
```
basedOnTimeForRefillingBucketUpToMax - время после которого бакет востановиться полностью
capacity - кол-во запросов на 1 ip адрес не более невключительно
period - период в течении которого действует ограничение на capacity

Запуск приложения в docker-compose.yml

* `./gradlew clean build`
   
* `docker-compose up`

Если нужно обновить проект после изменений
* `./gradlew clean build`
* В docker-compose.yml обновляет версию image до +1 было image: distance-calculator-api:0.0.2 должно стать image: distance-calculator-api:0.0.3
* `docker-compose up distance-calculator-api`

# Swagger 

http://localhost:8080/swagger-ui/
