## Дипломный проект профессии «Тестировщик»

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

### Описание программмы

Приложение предлагает купить тур по определённой цене с помощью двух способов:

1.Обычная оплата по дебетовой карте

2.Уникальная технология: выдача кредита по данным банковской карты

### Документация проекта

[Планирование автоматизации](https://github.com/SotAnk/Diplomy/blob/master/documents/Plan.md)

[Отчет по итогам тестирования](https://github.com/SotAnk/Diplomy/blob/master/documents/Report.md)

[Отчет по итогам автоматизации](https://github.com/SotAnk/Diplomy/blob/master/documents/Summary.md)

### Необходимое ПО
IntelliJ IDEA;

Java11;

Docker;

### Инструкция по запуску

1. Склонирровать [репозиторий](https://github.com/SotAnk/Diplomy.git) git clone
2. Открыть в Intellij IDEA
3. Запустить контейнер в терминале IntelliJ IDEA:  `docker-compose up -d --force-recreate`

**Для работы с базой данных MySQL**

1.Запустить SUT во вкладке Terminal Intellij IDEA командой: `java -jar artifacts/aqa-shop.jar`
 
2. Для запуска авто-тестов в Terminal Intellij IDEA ввести команду:  `./gradlew clean test allureReport -Dheadless=true`

 **Для работы с базой данных PostgreSQL** 
 
 1. В файле application.properties закомментировать строку: `#spring.datasource.url=jdbc:mysql://localhost:3306/app`

2.Раскомментировать строку: `spring.datasource.url=jdbc:postgresql://localhost:5432/base_postgresql`

3.запустить SUT командой:  `java -jar artifacts/aqa-shop.jar`

4.Для запуска авто-тестов ввести в Terminal Intellij IDEA команду:
`./gradlew clean test allureReport -Dheadless=true -Ddatasource=jdbc:postgresql://localhost:5432/base_postgresql`

 

