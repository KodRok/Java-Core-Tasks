package school.sorokin.javacore.multithreading.project;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {public static void main(String[] args) throws InterruptedException {
    System.out.println("Если 10 потоков: ");
    testWithThreadPool(10);
    System.out.println();

    System.out.println("\nЕсли только 1 поток:");
    testWithThreadPool(1);
    System.out.println();

    System.out.println("\nИспользую SingleThreadExecutor:");
    testWithSingleThread();
}

    private static void testWithThreadPool(int threadCount) throws InterruptedException {
        DataProcessor processor = new DataProcessor(threadCount);

        System.out.println("Беру 100 задач:");
        for (int i = 0; i < 100; i++) {
            processor.submitTask(List.of(i + 1));
        }

        System.out.println("\nЖду исполнения");
        while (processor.getActiveTaskCount() > 0) {
            System.out.println("Сейчас активных задач: " + processor.getActiveTaskCount());
            TimeUnit.MILLISECONDS.sleep(100);
        }

        System.out.println("\nПолучаю результаты:");
        for (int i = 1; i <= 100; i++) {
            String taskName = "Задача " + i;
            Optional<Integer> result = processor.getTaskResult(taskName);
            System.out.println(String.format("Сумма %s: %s",
                    taskName, result.orElse(-1)));
        }

        System.out.println("\nФинальная проверка:");
        System.out.println("Количество активных задач: " + processor.getActiveTaskCount());

        processor.shutdown();
    }

    private static void testWithSingleThread() throws InterruptedException {
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
        AtomicInteger taskCounter = new AtomicInteger(0);

        System.out.println("SingleThreadExecutor и 5 задач на исполнение:");
        for (int i = 0; i < 5; i++) {
            final String taskName = "singleThreadExecutor-задача " + taskCounter.incrementAndGet();
            final List<Integer> numbers = List.of(i + 1);

            singleExecutor.submit(new CalculateSumTask(numbers, taskName));
        }

        singleExecutor.shutdown();
        singleExecutor.awaitTermination(30, TimeUnit.SECONDS);
    }
}