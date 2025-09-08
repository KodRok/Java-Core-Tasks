package school.sorokin.javacore.collections.collections.lesson5;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Product, String> productMap = new HashMap<>();

        // Добавляем объекты с разными именами, но одинаковым id
        Product p1 = new Product(1, "Молоко", 1.50);
        Product p2 = new Product(1, "Сыр", 2.20);

        System.out.println("Хеш-коды объектов:");
        System.out.println("p1.hashCode(): " + p1.hashCode());
        System.out.println("p2.hashCode(): " + p2.hashCode());
        System.out.println("p1.equals(p2): " + p1.equals(p2));

        // Добавляем объекты в HashMap
        productMap.put(p1, "В наличии");
        productMap.put(p2, "Снят с производства");

        // Пытаемся получить значение по новому, логически равному ключу
        Product p3 = new Product(1, "Чай", 3.00); // Этот объект логически равен p1 и p2
        System.out.println("\nПоиск элемента по новому ключу (p3):");
        System.out.println("p3.hashCode(): " + p3.hashCode());
        System.out.println("p1.equals(p3): " + p1.equals(p3));

        System.out.println("\nЗначение, полученное по ключу p1: " + productMap.get(p1));
        System.out.println("Значение, полученное по ключу p3: " + productMap.get(p3));

        // Выводим все содержимое карты
        System.out.println("\nСодержимое карты:");
        for (Map.Entry<Product, String> entry : productMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
