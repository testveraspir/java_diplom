# Инструкция для запуска приложения “Путешествие дня “ и тестов

## Запуск приложения:
1. Открыть в Intelij IDEA проект **diplom1**
1. Открыть терминал, нажав клавиши **Alt** и **F12**
1. Запустить docker-compose командой: **docker-compose up -d**
1. Запустить приложение командой: 
   - **java -jar aqa-shop.jar --spring.config.name=application_mysql** (для базы данных **MySQL**)
   - **java -jar aqa-shop.jar --spring.config.name=application_postgresql** (для базы данных **Postgres**)

## Запуск тестов:
1. Открыть в Intelij IDEA проект **diplom1**
1. Запустить приложение
1. Открыть второе окно в терминале (нажать "+" во вкладке "Terminal")
1. Ввести команду: **gradlew clean test allureReport**
1. Ввести команду: **gradlew allureServe**
