## Дипломный проект профессии «Тестировщик»

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

### Описание программмы

Приложение предлагает купить тур по определённой цене с помощью двух способов:

1.Обычная оплата по дебетовой карте

2.Уникальная технология: выдача кредита по данным банковской карты

### Документация проекта

[Планирование автоматизации](https://github.com/SotAnk/Diplomy/blob/master/documents/Plan.md)

Отчет по итогам тестирования

Отчет по итогам автоматизации

### Необходимое ПО
IntelliJ IDEA;

Java11;

Docker;

### Инструкция по запуску

1. Склонирровать [репозиторий](https://github.com/SotAnk/Diplomy.git) git clone
2. Открыть в Intellij IDEA
3. Запустить контейнер в терминале IntelliJ IDEA:  'docker-compose up -d --force-recreate'
4. Для запуска MySQL использовать команду 'java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar'
5. Для запуска PostgreSQL использовать команду  'java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar'
6. Для запуска тестов команды:
 с postgres:  'gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/postgres'
 с mySql:  'gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app'
