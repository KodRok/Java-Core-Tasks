package school.sorokin.javacore.oop.lesson3;
/*Задача: Реализовать класс Car с полями brand, model, year.

Сделайте поля private String brand, private String model, private int year.
Реализуйте геттеры и сеттеры для каждого поля:
getBrand(), setBrand(String brand)
getModel(), setModel(String model)
getYear(), setYear(int year) (проверка: не меньше 1886 — считается, что первая машина была создана тогда).
В методе main (в отдельном классе Main) создайте пару объектов Car,
присвойте значения, попробуйте ввести некорректный год.
Убедитесь, что ваш код работает правильно (не устанавливает неправильные значения).
(Опционально) Добавьте метод toString() для удобного вывода информации об объекте.*/
public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.setBrand("Tesla");
        myCar.setModel("Model S");
        myCar.setYear(1021);

        System.out.println(myCar);

        myCar.setYear(1750);
        System.out.println("После попытки установить некорректный год: " + myCar.getYear());
    }
}
