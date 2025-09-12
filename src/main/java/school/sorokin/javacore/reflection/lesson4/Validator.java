package school.sorokin.javacore.reflection.lesson4;

import java.lang.reflect.Field;


public class Validator {

    public static <T> void validate(T object) throws IllegalAccessException {
        // Получаем объект Class из переданного объекта
        Class<?> clazz = object.getClass();

        // Получаем все объявленные поля
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            // Устанавливаем доступ к приватному полю
            field.setAccessible(true);
            Object value = field.get(object);

            // 1. Проверка на аннотацию @NotNull
            if (field.isAnnotationPresent(NotNull.class)) {
                if (value == null) {
                    System.err.println("Ошибка валидации: Поле '" + field.getName() + "' не может быть null.");
                    return; // Прекращаем проверку после первой ошибки
                }
            }

            // 2. Проверка на аннотацию @Size
            if (field.isAnnotationPresent(Size.class)) {
                if (value instanceof String) {
                    Size sizeAnnotation = field.getAnnotation(Size.class);
                    String stringValue = (String) value;

                    if (stringValue.length() < sizeAnnotation.min() || stringValue.length() > sizeAnnotation.max()) {
                        System.err.println("Ошибка валидации: Поле '" + field.getName() +
                                "' имеет недопустимую длину. Должна быть от " +
                                sizeAnnotation.min() + " до " + sizeAnnotation.max() + " символов.");
                        return; // Прекращаем проверку после первой ошибки
                    }
                }
            }
        }
        System.out.println("Объект успешно прошел валидацию!");
    }
}

