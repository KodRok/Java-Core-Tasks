package school.sorokin.javacore.reflection.lesson2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        try {
            // Получение объекта Class для Student
            Class<?> studentClass = Class.forName("school.sorokin.javacore.reflection.lesson2.Student");

            // 1. Получение и вызов приватного конструктора
            System.out.println("--- Шаг 1: Вызов приватного конструктора ---");
            Constructor<?> privateConstructor = studentClass.getDeclaredConstructor();
            privateConstructor.setAccessible(true); // Обходим ограничение доступа
            Student student = (Student) privateConstructor.newInstance();
            student.introduce();

            // 2. Вывод списка всех полей
            System.out.println("\n--- Шаг 2: Вывод всех полей ---");
            Field[] fields = studentClass.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("Поле: " + field.getName() + " (Тип: " + field.getType().getName() + ")");
            }

            // 3. Чтение и изменение значения поля name
            System.out.println("\n--- Шаг 3: Чтение и изменение поля name ---");
            Field nameField = studentClass.getDeclaredField("name");
            nameField.setAccessible(true);
            System.out.println("Текущее значение поля 'name': " + nameField.get(student));

            nameField.set(student, "Александр"); // Изменение значения
            System.out.println("Новое значение поля 'name': " + nameField.get(student));

            // 4. Нахождение и вызов приватного метода
            System.out.println("\n--- Шаг 4: Вызов приватного метода studySecretly() ---");
            Method privateMethod = studentClass.getDeclaredMethod("studySecretly");
            privateMethod.setAccessible(true); // Обходим ограничение доступа
            privateMethod.invoke(student);

            // Проверка, что изменения применились
            System.out.println("\n--- Проверка результата ---");
            student.introduce();

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}