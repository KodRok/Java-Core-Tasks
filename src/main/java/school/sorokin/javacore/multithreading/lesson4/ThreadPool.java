package school.sorokin.javacore.multithreading.lesson4;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) {
        // Создаём пул потоков с фиксированным количеством (3)
        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("--- Запуск 5 задач в пул потоков ---");

        // Задача 1: execute(Runnable)
        executor.execute(() -> {
            System.out.println("Задача 1 (execute): Начало. Спит 1 секунду.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Задача 1 (execute): Завершено.");
        });

        // Задача 2: execute(Runnable)
        executor.execute(() -> {
            System.out.println("Задача 2 (execute): Начало. Спит 2 секунды.");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Задача 2 (execute): Завершено.");
        });

        // Задача 3: submit(Callable)
        Future<String> future3 = executor.submit(() -> {
            System.out.println("Задача 3 (submit): Начало. Спит 3 секунды.");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Задача 3 успешно завершилась.";
        });

        // Задача 4: submit(Callable) - долгая задача
        Future<String> future4 = executor.submit(() -> {
            System.out.println("Задача 4 (submit, долгая): Начало. Спит 8 секунд.");
            try {
                // Имитация долгой задачи, которая реагирует на прерывание
                for (int i = 0; i < 8; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Задача 4 прервана!");
                        return "Задача 4 была прервана.";
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Задача 4 прервана (через exception)!");
                return "Задача 4 была прервана.";
            }
            System.out.println("Задача 4 (долгая) завершена.");
            return "Задача 4 успешно завершилась.";
        });

        // Задача 5: submit(Callable)
        Future<String> future5 = executor.submit(() -> {
            System.out.println("Задача 5 (submit): Начало. Спит 2 секунды.");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Задача 5 успешно завершилась.";
        });

        // Ожидание и обработка результатов
        try {
            System.out.println("\nОжидание результатов от задач...");

            // Получаем результат Задачи 3 и 5
            String result3 = future3.get();
            System.out.println("Результат Задачи 3: " + result3);

            String result5 = future5.get();
            System.out.println("Результат Задачи 5: " + result5);

            // Попытка получить результат долгой задачи с таймаутом в 3 секунды
            try {
                String result4 = future4.get(3, TimeUnit.SECONDS);
                System.out.println("Результат Задачи 4: " + result4);
            } catch (TimeoutException e) {
                System.out.println("Задача 4 не завершилась вовремя (3 секунды). Попытка отмены...");
                // Попытка отменить задачу, прерывая её поток
                future4.cancel(true);
                // Повторная попытка получить результат, чтобы поймать CancellationException
                try {
                    future4.get();
                } catch (CancellationException ce) {
                    System.out.println("Задача 4 успешно отменена.");
                }
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Завершение работы пула потоков
            executor.shutdown();
            try {
                // Ждём завершения всех задач
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }

        System.out.println("\n--- Результаты ---");
        System.out.println("Задача 3 завершена: " + future3.isDone());
        System.out.println("Задача 4 отменена: " + future4.isCancelled());
        System.out.println("Задача 5 завершена: " + future5.isDone());
    }
}
