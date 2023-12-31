# Дипломный проект по профессии «Тестировщик ПО»

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

---
## Документация
[План по автоматизации дипломного проекта](https://github.com/xJAMSEx/Diplom-QA/blob/main/documents/Plan.md)

[Отчёт о проведённом тестировании](https://github.com/xJAMSEx/Diplom-QA/blob/main/documents/Report.md)

[Отчёт о проведённой автоматизации](https://github.com/xJAMSEx/Diplom-QA/blob/main/documents/Summary.md)

---

## Описание приложения

### Бизнес часть

Приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:

1. Обычная оплата по дебетовой карте
2. Уникальная технология: выдача кредита по данным банковской карты

![](https://raw.githubusercontent.com/netology-code/qa-diploma/master/pic/service.png)


Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

* сервису платежей (далее - Payment Gate)
* кредитному сервису (далее - Credit Gate)

  ---

## Инструкция по запуску

1. Склонировать репозиторий с GitHub для запуска на локальном ПК:
   git clone https://github.com/xJAMSEx/Diplom-QA

2. Открыть папку diplom с помощью программы IntelliJ IDEA

3. Работа с базой данных MySQl
   - Запуск контейнера docker:  
      docker-compose up
   - Запуск приложения:  
      java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar ./artifacts/aqa-shop.jar
   - Запуск тестов:  
      ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
   - Сформировать отчет:  
      ./gradlew allureReport
   - Открыть отчет в браузере командой:  
      ./gradlew allureServe
   - Остановить контейнер:
      docker compose down

4. Работа с базой данных Postgres
   - Запуск контейнера docker:  
       docker-compose up
   - Запуск приложения:  
       java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar ./artifacts/aqa-shop.jar
   - Запуск тестов:  
       ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
   - Сформировать отчет:  
       ./gradlew allureReport
   - Открыть отчет в браузере командой:
       ./gradlew allureServe
   - Остановить контейнер:
      docker compose down
