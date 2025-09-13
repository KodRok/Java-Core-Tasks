package school.sorokin.javacore.reflection.lesson3;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        try {
            // Получение объекта Class для Book
            Class<?> bookClass = Book.class;

            // Получение всех методов класса
            Method[] methods = bookClass.getDeclaredMethods();

            System.out.println("Проверка методов на наличие аннотации @Author:");

            // Перебор методов
            for (Method method : methods) {
                // Проверка, есть ли у метода аннотация @Author
                if (method.isAnnotationPresent(Author.class)) {
                    // Получение объекта аннотации
                    Author authorAnnotation = method.getAnnotation(Author.class);

                    // Вывод информации об авторе
                    System.out.println("------------------------------------");
                    System.out.println("Метод: " + method.getName());
                    System.out.println("Автор: " + authorAnnotation.name());
                    System.out.println("Дата: " + authorAnnotation.date());
                    System.out.println("------------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
