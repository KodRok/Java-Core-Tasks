package school.sorokin.javacore.collections.collections.lesson4;

import java.util.HashMap;
import java.util.Map;

public class FruitPriceManager {
    public static void main(String[] args) {

        // Шаг 1: Создаем HashMap для хранения фруктов и их цен
        Map<String, Integer> fruitPrices = new HashMap<>();

        // Шаг 2: Добавляем пары "фрукт - цена"
        fruitPrices.put("Яблоко", 100);
        fruitPrices.put("Банан", 80);
        fruitPrices.put("Апельсин", 120);
        fruitPrices.put("Манго", 150);

        // Шаг 3: Выводим начальное содержимое HashMap
        System.out.println("Начальный список цен на фрукты:");
        for (Map.Entry<String, Integer> entry : fruitPrices.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " руб.");
        }
        System.out.println("-----------------------------");

        // Шаг 4: Обновляем цену одного из фруктов (Банан)
        fruitPrices.put("Банан", 90);
        System.out.println("Цена на Банан обновлена до 90 руб.");
        System.out.println("Обновленный список цен:");
        for (Map.Entry<String, Integer> entry : fruitPrices.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " руб.");
        }
        System.out.println("-----------------------------");

        // Шаг 5: Удаляем пару по ключу (Апельсин)
        fruitPrices.remove("Апельсин");
        System.out.println("Апельсин удален из списка.");
        System.out.println("Финальный список цен:");
        for (Map.Entry<String, Integer> entry : fruitPrices.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " руб.");
        }
    }
}
