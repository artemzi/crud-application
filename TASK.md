Проект можно выложить в публичный репозиторий github

1. Написать небольшой WEB сервис (CRUD), который оперирует 2-мя взаимосвязанными сущностями:

Сотрудник (Имя)
Оклад (Сумма)

сущности лежат в разных таблицах СУБД

В сервисе должна быть возможность выполнить операции:

1) одиночное создание сотрудника с окладом
2) одиночное изменение оклада сотрудника
3) получение всех сотрудников с окладами
4) удаление всех сотрудников с окладами


За основу взять Spring Boot
инструмент сборки gradle или maven,
сервер приложений Tomcat (embedded),
формат передачи данных JSON,
СУБД - любая из транзационных,
любые внешние библиотеки
IDE - любая.

2. Покрыть тестами любой метод сущности из пункта 1 (на всех определенных уровнях абстракций)

3. Предположим что ваш проект будет помещен в облако в качестве микросервиса с поддержкой докер контейнеров

Дополнить пункт 1 простеньким Dockerfile' ом для сборки Docker-образа

4. Подключить к пункту 1 Spring Security, дать доступ к одному из методов любой сущности только пользователю логин: USER,
   пароль: USER с ролью USER, при этом остальные методы должны быть доступны всем

5. Написать мини-форму на React.js + Redux (+ любые другие библиотеки)

форма содержит:
1) 2 поля ввода: Имя, Оклад и кнопка Создать
   , по нажатию на кнопку Создать - выполняется создание сотрудника с окладом из введенных поля ввода (Имя, Оклад) данных
   с помощью вызова метода WEB сервиса п.1 - 1)
3) таблицу всех сотрудников с окладами Имя (readonly) - Оклад  - кнопка Сохранить (таблица отображается по умолчанию)
   данные берутся из метода WEB сервиса п.1 - 3)
   , по нажатию на кнопку Cохранить в строке - выполняется изменение оклада сотрудника,
   указанного в строке с помощью вызова метода WEB сервиса п.1 - 2)
4) кнопку Удалить все, при нажатии на которую вызывается метод WEB сервиса п.1 - 4)

