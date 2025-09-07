package school.sorokin.javacore.collections.collections.lesson1;

public class Main {
    public static void main(String[] args) {
        // Демонстрация с Integer
        System.out.println("--- Демонстрация с Integer ---");
        GenericBox<Integer> integerBox1 = new GenericBox<>(100);
        GenericBox<Integer> integerBox2 = new GenericBox<>(200);

        System.out.println("Исходное состояние:");
        System.out.println("integerBox1: " + integerBox1.getContent());
        System.out.println("integerBox2: " + integerBox2.getContent());

        // Обмен содержимым
        integerBox1.swap(integerBox2);

        System.out.println("\nПосле обмена:");
        System.out.println("integerBox1: " + integerBox1.getContent());
        System.out.println("integerBox2: " + integerBox2.getContent());

        // Сравнение
        GenericBox<Integer> integerBox3 = new GenericBox<>(200);
        System.out.println("\nСравнение:");
        System.out.println("Содержимое integerBox1 и integerBox2 одинаково? " + integerBox1.isEqual(integerBox2));
        System.out.println("Содержимое integerBox1 и integerBox3 одинаково? " + integerBox1.isEqual(integerBox3));

        // Установка нового значения
        integerBox1.setContent(999);
        System.out.println("\nПосле установки нового значения в integerBox1: " + integerBox1.getContent());

        System.out.println("\n-----------------------------");

        // Демонстрация со String
        System.out.println("\n--- Демонстрация со String ---");
        GenericBox<String> stringBox1 = new GenericBox<>("Hello");
        GenericBox<String> stringBox2 = new GenericBox<>("World");

        System.out.println("Исходное состояние:");
        System.out.println("stringBox1: " + stringBox1.getContent());
        System.out.println("stringBox2: " + stringBox2.getContent());

        // Обмен содержимым
        stringBox1.swap(stringBox2);

        System.out.println("\nПосле обмена:");
        System.out.println("stringBox1: " + stringBox1.getContent());
        System.out.println("stringBox2: " + stringBox2.getContent());

        // Сравнение
        GenericBox<String> stringBox3 = new GenericBox<>("Hello");
        System.out.println("\nСравнение:");
        System.out.println("Содержимое stringBox1 и stringBox2 одинаково? " + stringBox1.isEqual(stringBox2));
        System.out.println("Содержимое stringBox1 и stringBox3 одинаково? " + stringBox1.isEqual(stringBox3));

        // Установка нового значения
        stringBox1.setContent("Java");
        System.out.println("\nПосле установки нового значения в stringBox1: " + stringBox1.getContent());
    }
}
