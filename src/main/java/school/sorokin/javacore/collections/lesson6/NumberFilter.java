package school.sorokin.javacore.collections.lesson6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NumberFilter {
    public static void main(String[] args) {
        // Шаг 1: Создаём ArrayList с числами от 1 до 20
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            numbers.add(i);
        }

        System.out.println("Исходный список: " + numbers);

        // Шаг 2: Используем Iterator для обхода и удаления нечётных чисел
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer currentNumber = iterator.next();
            // Если число нечётное (остаток от деления на 2 не равен 0)
            if (currentNumber % 2 != 0) {
                iterator.remove(); // Правильное удаление
            }
        }

        // Шаг 3: Выводим обновлённый список
        System.out.println("Список после удаления нечётных чисел: " + numbers);
    }
}
