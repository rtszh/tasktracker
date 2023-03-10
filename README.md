### *tasktracker* (*To-Do List*)

#### 1. Описание сервиса

Приложение для записи повседневных дел. Взаимодействие с пользователем осуществляется через *telegram*-бота.
Примеры команд и функционал:
1. `/create_task` - создать задачу. После ввода этой команды нужно ввести название (*title*) задачи и ее описание (*description*), используя шаблоны: 

a) шаблон 1: ***//t title //d description*** - шаблон для создания задачи с названием "**title**" и описанием "**description**"

b) шаблон 2: ***//t title*** - шаблон для создания задачи с названием "**title**" 

Пример создания задачи при взаимодействии с ботом:
```
1) Сообщение 1:  /create_task

2) Сообщение 2:  //t сходить на встречу в 17:00 //d встреча с заказчиком
```

Если необходимо создать несколько задач, то нужно перед созданием каждой задачи выполнять команду `/create_task`.

Пример создания нескольких задач:

```
1) Сообщение 1: /create_task

2) Сообщение 2: //t забрать машину из ремонта

3) Сообщение 3: /create_task

4) Сообщение 4: //tсходить на встречу //d встреча в офисе в 11:00

5) Сообщение 5: /create_task

6) Сообщение 6: //tсходить на встречу //d встреча с заказчиком в 17:00
```

2. `/delete_task` - удалить задачу. После ввода этой команды нужно ввести название (*title*) задачи и порядковый номер (*order number*) для задачи, которую требуется удалить: 

a) шаблон 1: ***//t title //o 2*** - шаблон для удаления задачи с названием "**title**" и порядковым номером "**order nmumber**", равным 2. Значение **order number** нужно заполнять в случае, если присутствуют **title** с одинаковым названием.

Пример к шаблону 1:

```
Текущий список задач в БД:
забрать машину из ремонта,
сходить на встречу: встреча в офисе в 11:00,
сходить на встречу (2): встреча с заказчиком в 17:00
В этом списке задач есть две задачи, у которых одинаковый title: 'сходить на встречу'

Взаимодействие с ботом:
1) Сообщение 1:  /delete_task

2) Сообщение 2:  //t сходить на встречу //o 2

В результате удалится задача, у которой title: "сходить на встречу (2)"

```


b) шаблон 2: ***//t title*** - шаблон для удаления задачи с названием "**title**". При этом порядковый номер будет автоматически принят равным единице;

3. `/get_tasks` - получить список задач

Пример для получения списка задач:

```
Текущий список задач в БД:
забрать машину из ремонта,
сходить на встречу: встреча в офисе в 11:00,
сходить на встречу (2): встреча с заказчиком в 17:00

Взаимодействие с ботом:
1) Сообщение 1:  /get_tasks

2) Ответ: 
забрать машину из ремонта,
сходить на встречу: встреча в офисе в 11:00,
сходить на встречу (2): встреча с заказчиком в 17:00

В результате вывелся список задач, который сохранен в БД для данного chatId

```

#### 2. Техническая часть сервиса
1. *service1* - принимает сообщения от *telegram*-клиента и складывает их во внешнюю очередь (*kafka*);
2. *kafka* - в данном случае, выступает как ребалансировщик нагрузки.

*Docker image* для *kafka* находится в *service1*

3. *service2* - содержит логику обработки сообщений, а также отвечает за сохранение сообщений в БД;