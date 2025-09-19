package school.sorokin.javacore.multithreading.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor {

    private final ExecutorService executorService;
    private final AtomicInteger taskCounter;
    private final AtomicInteger activeTasks;
    private final Map<String, Integer> resultsMap;

    public DataProcessor(int threadPoolSize) {
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
        this.taskCounter = new AtomicInteger(0);
        this.activeTasks = new AtomicInteger(0);
        this.resultsMap = new HashMap<>();
    }

    public void submitTask(List<Integer> numbers) {
        final String taskName = "task " + taskCounter.incrementAndGet();
        activeTasks.incrementAndGet();

        System.out.printf("DataProcessor: Отправляю %s на выполнение. Активных задач: %d%n",
                taskName, activeTasks.get());

        CompletableFuture.supplyAsync(() -> {
                    try {
                        return new CalculateSumTask(numbers, taskName).call();
                    } catch (Exception e) {
                        throw new CompletionException(e);
                    }
                }, executorService)
                .whenComplete((result, ex) -> {
                    activeTasks.decrementAndGet();
                    if (ex == null) {
                        synchronized (resultsMap) {
                            resultsMap.put(taskName, result);
                        }
                        System.out.printf("DataProcessor: %s выполнена. Результат: %d%n",
                                taskName, result);
                    } else {
                        System.err.printf("Завершилась %s: %s%n",
                                taskName, ex.getMessage());
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