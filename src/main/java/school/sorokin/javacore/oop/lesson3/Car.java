package school.sorokin.javacore.oop.lesson3;
/*Задача: Реализовать класс Car с полями brand, model, year.
Сделайте поля private String brand, private String model, private int year.
Реализуйте геттеры и сеттеры для каждого поля:
getBrand(), setBrand(String brand)
getModel(), setModel(String model)
getYear(), setYear(int year)
(проверка: не меньше 1886 — считается, что первая машина была создана тогда).
В методе main (в отдельном классе Main) создайте пару объектов Car,
присвойте значения, попробуйте ввести некорректный год.
Убедитесь, что ваш код работает правильно (не устанавливает неправильные значения).
(Опционально) Добавьте метод toString() для удобного вывода информации об объекте.*/
public class Car {
    private String brand;
    private String model;
    private int year;

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        if (year >= 1886) {
            this.year = year;
        } else {
            System.out.println("Год выпуска не может быть меньше 1886.");
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
