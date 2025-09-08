package school.sorokin.javacore.collections.collections.lesson7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class OptimizeDupCheck {
    public static void main(String[] args) {
        // Создаем список с дубликатом
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 100000; i++) {
            numbers.add(i);
        }
        numbers.add(50000); // Вставляем дубликат

        // Измеряем время для исходного метода
        long startTimeOriginal = System.nanoTime();
        boolean hasDuplicatesOriginal = checkDuplicatesOriginal(numbers);
        long endTimeOriginal = System.nanoTime();
        long durationOriginal = endTimeOriginal - startTimeOriginal;

        System.out.println("Исходный метод (O(n^2)):");
        System.out.println("Дубликаты найдены: " + hasDuplicatesOriginal);
        System.out.println("Время выполнения: " + TimeUnit.NANOSECONDS.toMillis(durationOriginal) + " мс");
        System.out.println("----------------------------------------");

        // Измеряем время для оптимизированного метода
        long startTimeOptimized = System.nanoTime();
        boolean hasDuplicatesOptimized = checkDuplicatesOptimized(numbers);
        long endTimeOptimized = System.nanoTime();
        long durationOptimized = endTimeOptimized - startTimeOptimized;

        System.out.println("Оптимизированный метод (O(n)):");
        System.out.println("Дубликаты найдены: " + hasDuplicatesOptimized);
        System.out.println("Время выполнения: " + TimeUnit.NANOSECONDS.toMillis(durationOptimized) + " мс");
    }

    // Исходная реализация
    public static boolean checkDuplicatesOriginal(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i).equals(numbers.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    // Оптимизированная реализация с использованием HashSet
    public static boolean checkDuplicatesOptimized(List<Integer> numbers) {
        // Создаем HashSet из списка. HashSet автоматически отбросит дубликаты.
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        // Если размер Set меньше размера исходного списка, значит, были дубликаты.
        return uniqueNumbers.size() < numbers.size();
    }
}
