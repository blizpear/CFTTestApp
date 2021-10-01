# Задание для CFT SHIFTLAB (сентябрь 2021)

Язык программирования: **Kotlin**

**Регистрация**

* **Экран 1. Регистрация**
    * На экране находится 6 элементов: Имя, Фамилия, Дата рождения, Пароль и его подтверждение, кнопка "Регистрация"
    * «Регистрация» не может быть завершена, пока все данные не будут валидны. Правила для корректных
      данных придумайте сами. Например, фамилия не может содержать менее двух символов, пароль должен
      содержать цифры и буквы верхнего регистра, и т.д.
    * Если данные валидны, то мы переходим на «Главный экран» приложения.

* **Экран 2. Главный экран**
    * На экране 1 элемент — кнопка «Приветствие».
    * По нажатию на эту кнопку появляется модальное окно, в котором находится приветствие пользователя с
      указанием имени, которое было введено на самом первом экране регистрации.

* **Из необезательного реализовано:**
    * Сделать выбор даты рождения интерактивным
    * Уведомлять/показывать сообщение о том, где именно была допущена ошибка при «Регистрации».
    * Кнопка «Регистрация» должна быть недоступна для нажатия, пока все поля не будут заполнены.
