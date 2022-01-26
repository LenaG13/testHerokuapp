Автоматизировать тесты для приложения: http://the-internet.herokuapp.com/
Каждая страница - отдельный класс и тест.

1. Add/Remove Elements
    Добавить 2 элемента, удалить элемент, проверить количество элементов
Локаторы xpath:
    - By.xpath(“//button[text()=’Add Element’]”)
    - By.xpath(“//*[text()=’Add Element’]”)
    - By.xpath(“//*[@onclick=’addElement()’]”)

2. Checkboxes
    - проверить, что первый чекбокс unchecked, отметить первый чекбокс, проверить что он checked.
    - проверить, что второй чекбокс checked, сделать uncheck, проверить, что он unchecked
    - Локатор By.cssSelector(“[type=checkbox]”)

3. Dropdown
    - Взять все элементы дроп-дауна и проверить их наличие.
    - Выбрать первый, проверить, что он выбран, выбрать второй, проверить, что он выбран
    - Локатор By.id(“dropdown”)

4. Inputs
    - Проверить на возможность ввести различные цифровые и нецифровые значения:
        используя Keys.ARROW_UP и Keys.ARROW_DOWN
    - Локатор By.tagName(“input”)

5.* Sortable Data Tables
    - Проверить содержимое нескольких (3-5) ячеек таблицы.
    - Использовать xpath типа //table//tr[1]//td[1] -
        получение первой ячейки из первого ряда первой таблицы и так далее

6. Typos
    - Проверить соответствие параграфа орфографии
    - Локатор By.tagName(“p”)

7.* Hovers
    - Сделать цепочку из действий: наведение на профиль,
        проверка имени, клик по ссылке, проверка 404 ошибки.
    - Повторить для каждого из профилей. Использовать класс Actions
и https://stackoverflow.com/questions/17293914/how-to-perform-mouseover-function-in-selenium-webdriver-using-java

8.* Notification Messages
    - кликнуть на кнопку, дождаться появления нотификации, проверить соответствие текста ожиданиям

9. Context Menu
    - Правый клик по элементу
    - Валидация текста на алерте
    - Закрытие алерта

10. Dynamic Controls
    - Найти чекбокс
    - Нажать на кнопку
    - Дождаться надписи “It’s gone”
    - Проверить, что чекбокса нет
    - Найти инпут
    - Проверить, что он disabled
    - Нажать на кнопку
    - Дождаться надписи “It's enabled!”
    - Проверить, что инпут enabled

11. File Upload
    - Загрузить файл. Для относительного пути использовать https://stackoverflow.com/questions/45612089/how-to-upload-file-with-relative-path-in-selenium-webdriver
    - Проверить, что имя файла на странице совпадает с именем загруженного файла

12. Frames
    - Открыть iFrame
    - Проверить, что текст внутри параграфа равен “Your content goes here.”

13.* File Download
    - Изучить https://www.swtestacademy.com/download-file-in-selenium/
    - Скачать файл
    - Проверить наличие файла на файловой системе
