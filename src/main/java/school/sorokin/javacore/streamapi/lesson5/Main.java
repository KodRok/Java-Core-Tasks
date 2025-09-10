package school.sorokin.javacore.streamapi.lesson5;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


        // 1. Создание списка чисел от 1 до 20
        List<Integer> numbers = IntStream.rangeClosed(1, 20)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("Исходный список: " + numbers);
        System.out.println("----------------------------------------");

        // 2. Использование Stream API для фильтрации, маппинга и сбора
        List<Integer> squaredEvenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0) // Фильтруем только чётные числа
                .map(n -> n * n)         // Возводим каждое число в квадрат
                .collect(Collectors.toList()); // Собираем результат в новый список

        System.out.println("Квадраты чётных чисел:");
        squaredEvenNumbers.forEach(n -> System.out.print(n + " "));
        System.out.println("\n----------------------------------------");

        // 3. Демонстрация ошибки повторного использования стрима
        Stream<Integer> streamToReuse = numbers.stream();
        System.out.println("Попытка повторного использования стрима:");
        try {
            // Первая терминальная операция - стрим потреблён
            streamToReuse.forEach(n -> System.out.print(n + " "));
            System.out.println(); // Новая строка для удобства чтения

            // Вторая терминальная операция на том же стриме - выбрасывает IllegalStateException
            streamToReuse.count();
        } catch (IllegalStateException e) {
            System.err.println("Ошибка! Стрим был использован и закрыт. Невозможно выполнить операцию: " + e.getMessage());
        }
        System.out.println("----------------------------------------");

        // 4. Сравнение производительности (опционально)
        long startTimeStream = System.nanoTime();
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());
        long endTimeStream = System.nanoTime();
        long durationStream = (endTimeStream - startTimeStream);

        long startTimeLoop = System.nanoTime();
        List<Integer> resultList = new java.util.ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                resultList.add(i * i);
            }
        }
        long endTimeLoop = System.nanoTime();
        long durationLoop = (endTimeLoop - startTimeLoop);

        System.out.println("Сравнение производительности:");
        System.out.printf("Время выполнения для стрима: %d нс\n", durationStream);
        System.out.printf("Время выполнения для цикла:  %d нс\n", durationLoop);

        System.out.println("Примечание: Для небольших коллекций, как эта, накладные расходы на создание стрима могут сделать его медленнее, чем обычный цикл.");
    }
}
