# Задание
Приложение Just Another Application обрабатывает некоторые операции.  Обработка операции состоит из трёх этапов Authentication, Authorization, Balance modification. Операции обрабатываются параллельно несколькими потоками, каждый поток в один момент времени обрабатывает только одну операцию. Во время обработки приложение пишет лог файл.

### Необходимо разработать консольное приложение, обрабатывающее лог файл и подсчитывающее:

- Число успешно и неуспешно обработанных операций.
- Среднее время обработки операций в разрезе успешных / неуспешных. 
- Среднее время выполнения этапов (Authentication, Authorization, Balances  modification) обработки операций в разрезе успешных / неуспешных.

Приложение должно принимать на вход полный путь к файлу с логами, результат должен выводиться в консоль.

### Нефункциональные требования к приложению:

- Написано на языке программирования Java или Kotlin.
- Собирается в jar-файл системой сборки maven.
- Запускается как java -jar <application_name.jar> <path_to_log_file> (без указания Main класса).
- Не требует внешних библиотек.

### Формат лог сообщений:  

Каждое сообщение располагается на новой строке файла логов. Формат строки (нотация библиотеки logback.xml):

- -%date{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %msg%n}
- Перечень возможных сообщений ( %msg ):
- “Operation processing [id=????] has been started.” — начало обработки операции.
- “Operation processing [id=????] has been finished.” —  успешный конец обработки операции.
- “Operation processing [id=????] has been failed.” —  неуспешный конец обработки операции.
- “Authentication has been started.” — начало процесса аутентификации (выполняется в рамках обработки операции). 
- “Authentication has been finished.” — завершение процесса аутентификации (выполняется в рамках обработки операции). 
- “Authorization has been started.” — начало процесса авторизации (выполняется в рамках обработки операции). 
- “Authorization has been finished.” — завершение процесса авторизации (выполняется в рамках обработки операции). 
- “Balances modification has been started.” — начало процесса изменения балансов (выполняется в рамках обработки операции).
- “Balance modification has been finished.” — завершение процесса изменения балансов (выполняется в рамках обработки операции).

### Пример лог-файла:
```
2022-05-01 11:04:26.300 [thread-1] Operation processing [id=100] has been started.
2022-05-01 11:04:26.300 [thread-1] Authentication has been started.
2022-05-01 11:04:26.500 [thread-1] Authentication has been finished.
2022-05-01 11:04:26.500 [thread-1] Authorization has been started.
2022-05-01 11:04:26.700 [thread-1] Authorization has been finished.
2022-05-01 11:04:26.700 [thread-1] Balances modification has been started.
2022-05-01 11:04:27.300 [thread-1] Balances modification has been finished.
2022-05-01 11:04:27.300 [thread-1] Operation processing [id=100] has been finished.
2022-05-01 11:04:28.000 [thread-2] Operation processing [id=101] has been started.
2022-05-01 11:04:28.000 [thread-2] Authentication has been started.
2022-05-01 11:04:28.100 [thread-2] Authentication has been finished.
2022-05-01 11:04:28.100 [thread-2] Authorization has been started.
2022-05-01 11:04:28.200 [thread-2] Authorization has been finished.
2022-05-01 11:04:28.200 [thread-2] Balances modification has been started.
2022-05-01 11:04:28.400 [thread-2] Balances modification has been finished.
2022-05-01 11:04:28.400 [thread-2] Operation processing [id=101] has been finished.
2022-05-01 11:04:29.300 [thread-1] Operation processing [id=103] has been started.
2022-05-01 11:04:29.300 [thread-1] Authentication has been started.
2022-05-01 11:04:29.500 [thread-1] Authentication has been finished.
2022-05-01 11:04:29.500 [thread-1] Authorization has been started.
2022-05-01 11:04:29.700 [thread-1] Authorization has been finished.
2022-05-01 11:04:29.700 [thread-1] Balances modification has been started.
2022-05-01 11:04:30.700 [thread-1] Balances modification has been finished.
2022-05-01 11:04:30.700 [thread-1] Operation processing [id=103] has been finished.
2022-05-01 11:04:31.000 [thread-1] Operation processing [id=104] has been started.
2022-05-01 11:04:31.000 [thread-1] Authentication has been started.
2022-05-01 11:04:31.100 [thread-1] Authentication has been finished.
2022-05-01 11:04:31.100 [thread-1] Authorization has been started.
2022-05-01 11:04:31.200 [thread-1] Authorization has been finished.
2022-05-01 11:04:31.200 [thread-1] Balances modification has been started.
2022-05-01 11:04:31.300 [thread-1] Balances modification has been finished.
2022-05-01 11:04:31.300 [thread-1] Operation processing [id=104] has been failed.
```
### Пример вывода для данного лога:
```
Successful operation count: 3
Average operation successful processing time: 934ms
Failed operation count: 1
Average operation failed processing time: 300ms
Average Authentication time (for successful operations): 167ms
Average Authentication time (for failed operations): 100ms
Average Authorization time (for successful operations): 167ms
Average Authorization time (for failed operations): 100ms
Average Balance modification time (for successful operations): 600ms
Average Balance modification time (for failed operations): 100ms
```