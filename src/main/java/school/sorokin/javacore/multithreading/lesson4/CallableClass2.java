package school.sorokin.javacore.multithreading.lesson4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class CallableClass2 {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("--- Запуск 5 задач в пул потоков ---");

        // Задачи с разным временем выполнения
        Map<Integer, Integer> taskTimes = Map.of(1, 1, 2, 2, 3, 3, 4, 2, 5, 9);
        List<Future<String>> futures = new ArrayList<>();

        // Запускаем все задачи в цикле и сохраняем Future-объекты
        for (
                Map.Entry<Integer, Integer> entry : taskTimes.entrySet()) {
            int taskNumber = entry.getKey();
            int sleepTime = entry.getValue();

            Future<String> future = executor.submit(() -> {
                String str = "";
                try {
                    str = String.format("Задача %d выполняется в потоке: %s. Спит %d секунд.",
                            taskNumber, Thread.currentThread().getName(), sleepTime);
                    TimeUnit.SECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return str;
            });
            futures.add(future);
        }

        // Ожидаем и обрабатываем результаты всех задач
        System.out.println("\nОжидание результатов от задач...");

        for (
                int i = 0; i < futures.size(); i++) {
            Future<String> future = futures.get(i);
            try {
                String result = future.get(3, TimeUnit.SECONDS);
                System.out.printf("Результат задачи %d: %s\n", (i + 1), result);
                System.out.printf("Задача %d завершена: %b\n", (i + 1), future.isDone());
            } catch (TimeoutException e) {
                System.out.printf("Время ожидания для задачи %d истекло. Попытка отмены.\n", (i + 1));
                future.cancel(true);
                // Повторная попытка получить результат, чтобы поймать CancellationException
                try {
                    future.get();
                } catch (CancellationException ce) {
                    System.out.printf("Задача %d успешно отменена: %b\n", (i + 1), future.isCancelled());
                } catch (InterruptedException | ExecutionException ee) {
                    System.err.printf("Ошибка при обработке отменённой задачи %d.\n", (i + 1));
                }
            } catch (InterruptedException | ExecutionException e) {
                System.err.printf("Ошибка при выполнении задачи %d.\n", (i + 1));
                e.printStackTrace();
            }
        }

        // Завершение работы пула потоков
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (
                InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
