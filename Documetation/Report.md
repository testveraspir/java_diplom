# Отчёт по итогам тестирования
## Краткое описание
24.06.2021 -  19.07.2021г было проведено:  тестирование приложения “Путешествие дня” согласно [плану автоматизации тестирования](https://github.com/testveraspir/java_diplom/blob/master/Documetation/Plan.md).  
## Количество тест-кейсов
Всего 26, из них:  успешных - 14 (53,8%) и неуспешных - 12 (46,2%)
### Отчёт по Gradle
![Отчёт по Gradle](https://github.com/testveraspir/java_diplom/blob/master/Documetation/report.png)

### Отчёт по Allure
![Отчёт по Allure](https://github.com/testveraspir/java_diplom/blob/master/Documetation/AllureReport.png)
## Общие рекомендации и ссылки на найденные баги
- Исправить ошибку в слове “Маракэш”, на “Маракеш”

[Опечатка в слове “Марракэш”](https://github.com/testveraspir/java_diplom/issues/9)
- Убрать сообщение об успехе при вводе отклонённой карты

[Выдается сообщение “Успешно” при вводе отклоненной номера карты](https://github.com/testveraspir/java_diplom/issues/3)
- Убрать сообщение об успехе, которое отображается после закрытия сообщения об ошибки при вводе несуществующего номера карты

[Появляется сообщение “Успешно ...” при вводе несуществующего номера карты](https://github.com/testveraspir/java_diplom/issues/7)
- Изменить сообщение под полями, если поля оставить незаполненными

[Неверные сообщения об ошибке под полями: “Номер карты”, “Месяц”, “Год”, “CVC/CVV”, если их оставить незаполненными](https://github.com/testveraspir/java_diplom/issues/1)
- Для поля “Месяц” сделать невозможным ввода “00”

[В поле “Месяц” можно ввести два нуля](https://github.com/testveraspir/java_diplom/issues/12)
- Для поля “Владелец” ограничить количество вводимых символов, сделать невозможным ввода кириллицы и цифр

[В поле “Владелец” можно ввести неограниченное количество символов](https://github.com/testveraspir/java_diplom/issues/6)

[В поле “Владелец” можно ввести русские буквы](https://github.com/testveraspir/java_diplom/issues/13)

[В поле “Владелец” можно ввести цифры](https://github.com/testveraspir/java_diplom/issues/5)
- Исправить сообщение об ошибке под полем “СVC/CVV” при повторном вводе уже верных данных

[Остаётся сообщение об ошибки под полем “CVC/CVV” при вводе верных данных](https://github.com/testveraspir/java_diplom/issues/8)
- Может быть, изменить внешний вид кнопок

[Не подходит фон кнопки “Купить”](https://github.com/testveraspir/java_diplom/issues/2)
