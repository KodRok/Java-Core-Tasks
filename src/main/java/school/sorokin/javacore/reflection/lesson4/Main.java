package school.sorokin.javacore.reflection.lesson4;

public class Main {
    public static void main(String[] args) {
        // Пример 1: Объект, который пройдет валидацию
        System.out.println("--- Тест 1: Валидный объект ---");
        User validUser = new User("javauser", "securepass123", "I love Java");
        try {
            Validator.validate(validUser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Тест 2: Невалидный объект (username слишком короткий) ---");
        // Пример 2: username слишком короткий
        User invalidUserSize = new User("me", "securepass123", "short bio");
        try {
            Validator.validate(invalidUserSize);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Тест 3: Невалидный объект (password == null) ---");
        // Пример 3: password = null
        User invalidUserNull = new User("validname", null, "another bio");
        try {
            Validator.validate(invalidUserNull);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
