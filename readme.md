# Диплом по тестированию приложения "Путешествие дня"
## Документация
- [План по автоматизации](https://github.com/testveraspir/java_diplom/blob/master/Documetation/Plan.md)
- [Отчёт по итогам тестирования](https://github.com/testveraspir/java_diplom/blob/master/Documetation/Report.md)
- [Отчёт по итогам автоматизации](https://github.com/testveraspir/java_diplom/blob/master/Documetation/Summary.md)

## Инструкция для запуска приложения “Путешествие дня “ и тестов

### Запуск приложения:
1. Открыть в Intelij IDEA проект **[diplom1](https://github.com/testveraspir/java_diplom.git)**, предварительно его склонировав
1. Открыть терминал, нажав клавиши **Alt** и **F12**
1. Запустить docker-compose командой: **docker-compose up -d**
1. Запустить приложение командой: 
   - **java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar** (для базы данных **MySQL**)
   - **java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar aqa-shop.jar** (для базы данных **PostgreSQL**)

### Запуск тестов:
1. Открыть в Intelij IDEA проект **[diplom1](https://github.com/testveraspir/java_diplom.git)**, предварительно его склонировав
1. Запустить приложение
1. Открыть второе окно в терминале (нажать "+" во вкладке "Terminal")
1. Ввести команды

   для **MySQL**: 
    - gradlew clean -Ddb.url="jdbc:mysql://localhost:3306/app" test allureReport
    - gradlew -Ddb.url="jdbc:mysql://localhost:3306/app" allureServe
    
   для **PostgreSQL**:
    - gradlew clean -Ddb.url="jdbc:postgresal://localhost:5432/app" test allureReport 
    - gradlew -Ddb.url="jdbc:postgresal://localhost:5432/app" allureServe
