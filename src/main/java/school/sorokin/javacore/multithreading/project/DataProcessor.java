package school.sorokin.javacore.multithreading.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor {
    private final ExecutorService executorService;
    private final AtomicInteger taskCounter = new AtomicInteger(0);
    private final AtomicInteger activeTasks = new AtomicInteger(0);
    private final Map<String, Integer> resultsMap = new HashMap<>();
    private final Map<String, Future<Integer>> futureConcurrentHashMap = new ConcurrentHashMap<>();

    public DataProcessor(int threadPoolSize) {
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void submitTask(List<Integer> numbers) {
        final String taskName = "Задача " + taskCounter.incrementAndGet();

        activeTasks.incrementAndGet();

        System.out.printf("DataProcessor отправил %s на выполнение. Активных задач: %d%n",
                taskName, activeTasks.get());
        System.out.println();

        CalculateSumTask task = new CalculateSumTask(numbers, taskName);
        Future<Integer> future = executorService.submit(task);

        futureConcurrentHashMap.put(taskName, future);

        CompletableFuture.supplyAsync(() -> {
            Integer result = null;

            try {
                result = future.get();
                synchronized (resultsMap) {
                    resultsMap.put(taskName, result);
                }

                System.out.printf("DataProcessor получил для %s результат: %d%n",
                        taskName, result);
                System.out.println();

                return result;
            } catch (Exception e) {
                System.err.printf("%s завершилась с ошибкой: %s%n", taskName, e.getMessage());
                System.out.println();
                return result;
            } finally {
                activeTasks.decrementAndGet();
                futureConcurrentHashMap.remove(taskName);
            }
        });
    }

    public int getActiveTaskCount() {
        return activeTasks.get();
    }

    public Optional<Integer> getTaskResult(String taskName) {
        synchronized (resultsMap) {
            return Optional.ofNullable(resultsMap.get(taskName));
        }
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            boolean isTerminated = executorService.awaitTermination(10, TimeUnit.SECONDS);
            if (!isTerminated) {
                System.out.println("Принудительная остановка незавершенных задач.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            throw new RuntimeException("Ожидание завершения было прервано.", e);
        }
    }
}