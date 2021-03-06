# 1. Цель проекта

**Цель проекта** - разработать систему для приготовления *коктейлей из ингредиентов*, выбранных пользователем (далее Система).
Пользователь может ознакомиться с историей коктейля, а также узнать кто из знаменитостей предпочитает его. Помимо этого
в Системе должен быть предусмотрен *личный аккаунт* пользователя, для хранения его *избранных напитков*.

# 2. Описание системы

Система состоит из следующих основных *функциональных блоков*:

1. Регистрация и авторизация;
2. Конструктор коктейля;
3. Функционал с информацией про напиток;
4. Все ингредиенты;
5. Избранные напитки пользователя.

### 2.1. Типы пользователей

Система предусматривает *один* тип пользователя, который имеет доступ к своему *личному кабинету* с избранными напитками.

### 2.2. Регистрация и авторизация

Для ***регистрации*** в Системе пользователю необходимо указать ряд данных:

- username - **обязательное** поле;
- password - **обязательное** поле;
- confirm password - **обязательное** поле.

*Система не допускает повтор username разных пользователей*

При вводе пользователем поля username необходимо организовать *"приветствие"*.

Для ***авторизации*** в Системе пользователю необходимо указать:

- username - **обязательное поле**;
- password - **обязательное поле**.

Далее происходит *поиск по базе данных* пользователя с указанным username.

В случае, если пользователя не существует, *происходит предупреждение* об этом.

### 2.2. Конструктор коктейля

Конструктор коктейля подразумевает *отдельную вкладку* в Системе. Имеется возможность выбора имеющихся ингредиентов,
дополнительных опций, формата коктейля.

По клику на коктейль пользователь должен направляться на личную страницу выбранного коктейля. *(Функционал с информацией про напиток)*

Должен быть сделан *поиск* по различным параметрам. Для вывода данных необходимо нажать либо *Enter*, либо предложенную снизу *кнопку*. Предусмотреть *вывод ошибки* при случае, когда коктейль *не найден*.

### 2.3. Функционал с информацией про напиток

Информация про напитки должна *включать в себя*:

1. Состав;
2. История напитка;
3. Предпочтения знаменитостей;
4. Отзыв других пользователей;
5. Рецепт приготовления напитка;
6. Крепость напитка;
7. Фотографию напитка.

### 2.4. Все ингредиенты

Все ингредиенты подразумевают *отдельную вкладку* в Системе, где пользователь может просмотреть все ингредиенты которые
могут понадобиться для приготовления напитка.

Данная вкладка подразумевает различные категории и крепость ингредиента. 

Необходимо реализовать *поиск* по всем доступным параметрам таким, как категория, крепость, название ингредиента.

Для вывода данных необходимо нажать либо *Enter*, либо предложенную снизу *кнопку*. Предусмотреть *вывод ошибки* при случае, когда ингредиент не найден.

### 2.5. Обработка исключительных ситуаций

**Авторизация**

- *Не заполнение* всех данных (login, password);
- *Не совпадение* пароля/аккаунта с базой данных;
- *Отсутствие* аккаунта в базе данных.

**Регистрация**

- *Не заполнение* всех данных (login, password, confirm password);
- При *переходе на вкладку* регистрации, пользователь должен подтвердить свой возраст всплывающим окном;
- *Не совпадение* полей password и confirm password;
- Пароль *должен включать* в себя минимум 8 символов, не являющимися только цифрами;
- *Отсутствие подтверждения* пользовательского соглашения и политики конфиденциальности. 

В случае возникновения данных ситуаций Пользователь должен быть информирован *выскакивающим предупреждением.*

# 3. Предлагаемый стек технологий

Для реализации Системы предлагается *следующий стек технологий*:

- *Бэкенд*:
    - Язык Java 11
    - Фреймворк JavaFX
    - БД MySQL (Таблицы: User, Ingredients, Recipe, Cocktails)

- *Фронтенд*:
    - Css

# 4. Требования к разработке

1. Все логические задачи необходимо выполнять в разных пакетах

2. Каждая задача подразумевает создание нового метода

3. Код необходимо комментировать

4. Следует избегать глубокой вложенности условных и циклических конструкций: вложенность блоков должна быть не более
   трех

5. Следует избегать длинных функций: текст функции должен умещаться на один экран (размер текста не должен превышать
   25–50 строк)

# 5. Требования к дизайну

Минимализм, лаконичность, акцент на информацию. Белый фон. Должен присутствовать логотип Системы где-то на странице.
Логотип надо разработать в рамках этого проекта.
