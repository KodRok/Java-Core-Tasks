package school.sorokin.javacore.multithreading.lesson6;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureChain {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Главный поток: Запускаю асинхронные задачи...");

        // Шаг 1: Долгая вычислительная операция (асинхронный поставщик)
        CompletableFuture<Integer> longCalculation = CompletableFuture.supplyAsync(() -> {
            System.out.println("1. supplyAsync(): Начинаю долгий расчёт...");
            try {
                TimeUnit.SECONDS.sleep(2); // Симулируем задержку в 2 секунды
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            int result = 5;
            System.out.println("1. supplyAsync(): Расчёт завершён. Результат: " + result);
            return result;
        });

        // Шаг 2: Преобразование результата с помощью thenApply() и thenCompose()
        // и Шаг 3: Возможная ошибка и её обработка
        CompletableFuture<Integer> transformedResult = longCalculation
                .thenApply(number -> {
                    System.out.println("2. thenApply(): Умножаю " + number + " на 10...");
                    if (number > 4) {
                        System.out.println("2. thenApply(): Число больше 4, выбрасываю исключение.");
                        throw new IllegalArgumentException("Слишком большое число!");
                    }
                    return number * 10;
                })
                .exceptionally(ex -> {
                    System.err.println("3. exceptionally(): Обработал исключение: " + ex.getMessage());
                    return -1; // Возвращаем значение по умолчанию, чтобы цепочка продолжилась
                });

        // Шаг 4: Вывод результата без блокировки
        transformedResult.thenAccept(finalResult -> {
            System.out.println("4. thenAccept(): Финальный результат цепочки: " + finalResult);
        });

        // --- Дополнительная задача: Объединение результатов двух независимых фьючеров ---
        System.out.println("\nЗапускаю две независимые асинхронные задачи...");

        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("   Задача 1: Начинаю вычисление...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
            }
            return 100;
        });

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("   Задача 2: Начинаю вычисление...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            return 50;
        });

        // thenCombine() объединяет результаты task1 и task2
        CompletableFuture<Integer> combinedResult = task1.thenCombine(task2, (result1, result2) -> {
            System.out.println("   thenCombine(): Объединяю результаты: " + result1 + " + " + result2);
            return result1 + result2;
        });

        combinedResult.thenAccept(finalCombined -> {
            System.out.println("   Финальный объединённый результат: " + finalCombined);
        });

        // Ждём завершения всех задач, чтобы программа не завершилась сразу
        CompletableFuture.allOf(longCalculation, transformedResult, task1, task2, combinedResult).join();

        System.out.println("\nГлавный поток: Все задачи завершены.");
        Executors.newSingleThreadExecutor().shutdown();
    }
}
