package school.sorokin.javacore.collections.collections.lesson7;

import java.util.ArrayList;
import java.util.List;

public class DuplicateChecker {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        // Заполнение списка числами от 0 до 100000 с дублированием одного значения
        long startTime = System.nanoTime();
        long sum = 0;
        for (int i = 0; i <= 100000; i++) {
            numbers.add(i);
        }
        numbers.add(50000); // Вставляем дубликат

        boolean hasDuplicates = false;
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i).equals(numbers.get(j))) {
                    hasDuplicates = true;
                    break;
                }
            }
            if (hasDuplicates) break;
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // Выводим результат
        System.out.println("Сумма: " + sum);
        System.out.println("Время выполнения: " + duration + " наносекунд");
        System.out.println("Дубликаты найдены: " + hasDuplicates);
    }
}
