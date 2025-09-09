package school.sorokin.javacore.collections.lesson6;

import java.util.ArrayList;
import java.util.List;

public class UnsafeRemoval {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            numbers.add(i);
        }

        System.out.println("Попытка удаления без итератора...");
        try {
            // Этот код выбросит ConcurrentModificationException
            for (Integer number : numbers) {
                if (number % 2 != 0) {
                    numbers.remove(number); // Ошибка! Изменение коллекции во время итерации
                }
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getClass().getName());
            System.out.println("Причина: Коллекция была изменена во время обхода.");
        }
    }
}
