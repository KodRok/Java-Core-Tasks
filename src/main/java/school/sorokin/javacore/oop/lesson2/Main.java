package school.sorokin.javacore.oop.lesson2;

/*Задача: На практике оценить различие примитивов и ссылочных типов,
а также поэкспериментировать со сборкой мусора.

Создайте класс Main с методом main.
Объявите в методе несколько примитивных переменных (например, int, double, boolean). Выведите их в консоль.
Создайте объект вашего собственного класса (например, Person или Car) и присвойте ссылку локальной переменной.
Выведите поля объекта.
Присвойте переменной null. Проверьте, что дальнейший доступ к объекту теперь невозможен.
(Опционально) Вызовите System.gc() и посмотрите, будет ли в консоли какое-то сообщение о сборке мусора
(возможно, не будет, так как это поведение зависит от конкретной JVM и её настроек).
Для наглядности можно добавить finalize() метод в свой класс,
который выводит сообщение (но учтите, что finalize() устарел
и не гарантируется к немедленному вызову — лучше воспринимать это как эксперимент).

Что проверить:
Убедитесь, что при присвоении null объект становится недостижим.
Разберитесь, что значит «копирование значения» для примитива
и «копирование ссылки» для объектного типа (напишите метод, который пытается «изменить» ваш объект,
и метод, который пытается изменить примитив).*/
public class Main {
    public static void main(String[] args) {
        int intNum = 10;
        double doubleNum = 10.0;
        boolean bool = true;
        System.out.println(intNum);
        System.out.println(doubleNum);
        System.out.println(bool);
        Memory memory = new Memory();
        memory.capacity = 225;
        memory.name = "stack";
        System.out.println(memory.capacity + " - " + memory.name);
        memory = null;
        System.gc();


        int x = 10;
        System.out.println("Исходное значение x: " + x);
        int num = x;
        num = 20;
        System.out.println("Значение num : " + num);
        System.out.println("Значение x после : " + x);

        Integer x1;
        x1 = new Integer(5);
        System.out.println("Исходное Значение x1 : " + x1);

        Integer x2 = new Integer(555);
        System.out.println("Значение x1 после : " + x1);
        System.out.println("Исходное Значение x2 : " + x2);
        x2 = 10;
        x1 = x2;
        System.out.println("Значение x1 после : " + x1);
        System.out.println("Значение x2 после : " + x2);
    }
}
