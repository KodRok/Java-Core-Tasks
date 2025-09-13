package school.sorokin.javacore.reflection.lesson1;

import java.lang.reflect.Modifier;
import java.util.Arrays;

import static java.lang.Class.forName;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        // Получаем объект Class тремя способами
        // Способ 1: через .class
        Class<Person> clazz1 = Person.class;

        // Способ 2: через getClass()
        Person person = new Person("John Doe", 30);
        Class<? extends Person> clazz2 = person.getClass();

        try {
            // Исправленная строка
            Class<?> clazz3 = Class.forName("school.sorokin.javacore.reflection.lesson1.Person");

            System.out.println("Полное имя класса: " + clazz3.getName());
            System.out.println("Простое имя класса: " + clazz3.getSimpleName());

            System.out.println("\nРеализованные интерфейсы:");
            Class<?>[] interfaces = clazz3.getInterfaces();
            if (interfaces.length > 0) {
                Arrays.stream(interfaces).forEach(i -> System.out.println("- " + i.getName()));
            } else {
                System.out.println("Класс не реализует интерфейсы.");
            }

            int modifiers = clazz3.getModifiers();
            System.out.println("\nМодификаторы класса (числом): " + modifiers);
            System.out.println("Модификаторы класса (читабельный вид): " + Modifier.toString(modifiers));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
