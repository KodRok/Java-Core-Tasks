package school.sorokin.javacore.multithreading.lesson4;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class AdvancedThreadPoolPractice {
    public static void main(String[] args) throws Exception {
        System.out.println("=== ЭКСПЕРИМЕНТ 1: FixedThreadPool(3) ===\n");
        runExperiment("FixedThreadPool", Executors.newFixedThreadPool(3));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("=== ЭКСПЕРИМЕНТ 2: CachedThreadPool ===\n");
        runExperiment("CachedThreadPool", Executors.newCachedThreadPool());
    }

    private static void runExperiment(String poolType, ExecutorService executor) throws Exception {
        System.out.println("🏊‍♂️ Используем " + poolType);
        System.out.println("Запускаем 5 задач с разными временами выполнения...\n");

        // Список для отслеживания всех Future
        List<TaskInfo> tasks = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        // ЗАДАЧА 1: execute() с Runnable - быстрая задача (1 сек)
        System.out.println("🚀 Задача 1: execute() - 1 секунда");
        executor.execute(createRunnableTask("Задача 1", 1));
        tasks.add(new TaskInfo("Задача 1", null, "execute()", 1));

        // ЗАДАЧА 2: submit() с Runnable - средняя задача (2 сек)
        System.out.println("🚀 Задача 2: submit(Runnable) - 2 секунды");
        Future<?> future2 = executor.submit(createRunnableTask("Задача 2", 2));
        tasks.add(new TaskInfo("Задача 2", future2, "submit(Runnable)", 2));

        // ЗАДАЧА 3: submit() с Callable - быстрая задача (1 сек)
        System.out.println("🚀 Задача 3: submit(Callable) - 1 секунда");
        Future<String> future3 = executor.submit(createCallableTask("Задача 3", 1));
        tasks.add(new TaskInfo("Задача 3", future3, "submit(Callable)", 1));

        // ЗАДАЧА 4: submit() с Callable - ДОЛГАЯ задача (8 секунд)
        System.out.println("🚀 Задача 4: submit(Callable) - 8 секунд (будем отменять!)");
        Future<String> future4 = executor.submit(createCallableTask("Задача 4", 8));
        tasks.add(new TaskInfo("Задача 4", future4, "submit(Callable)", 8));

        // ЗАДАЧА 5: submit() с Callable - средняя задача (3 сек)
        System.out.println("🚀 Задача 5: submit(Callable) - 3 секунды");
        Future<String> future5 = executor.submit(createCallableTask("Задача 5", 3));
        tasks.add(new TaskInfo("Задача 5", future5, "submit(Callable)", 3));

        System.out.println("\n" + "-".repeat(50));

        // Мониторинг в реальном времени
        System.out.println("📊 Мониторинг выполнения (каждые 1 сек):");
        for (int i = 1; i <= 5; i++) {
            System.out.printf("⏰ Прошло %d сек: ", i);
            for (TaskInfo task : tasks) {
                if (task.future != null) {
                    System.out.printf("%s[%s] ", task.name.substring(7), getTaskStatus(task.future));
                } else {
                    System.out.printf("%s[exec] ", task.name.substring(7));
                }
            }
            System.out.println();
            Thread.sleep(1000);
        }

        // Пытаемся получить результат долгой задачи с таймаутом
        System.out.println("\n🎯 Пытаемся получить результат Задачи 4 за 3 секунды...");
        try {
            String result4 = future4.get(3, TimeUnit.SECONDS);
            System.out.println("✅ Задача 4 успела выполниться: " + result4);
        } catch (TimeoutException e) {
            System.out.println("⏰ Задача 4 не успела за 3 секунды - ОТМЕНЯЕМ!");
            boolean cancelled = future4.cancel(true); // Прерываем выполнение
            System.out.println("🚫 Отмена задачи 4: " + (cancelled ? "успешно" : "неудачно"));
        }

        // Получаем результаты остальных задач
        System.out.println("\n📋 Получаем результаты всех submit() задач:");

        // Задача 2 (Runnable через submit)
        try {
            Object result2 = future2.get(1, TimeUnit.SECONDS);
            System.out.println("✅ Задача 2: " + (result2 == null ? "завершена (Runnable возвращает null)" : result2));
        } catch (TimeoutException e) {
            System.out.println("⏰ Задача 2 всё ещё выполняется");
        } catch (Exception e) {
            System.out.println("❌ Задача 2: ошибка - " + e.getMessage());
        }

        // Задача 3 (Callable)
        try {
            String result3 = future3.get(1, TimeUnit.SECONDS);
            System.out.println("✅ Задача 3: " + result3);
        } catch (TimeoutException e) {
            System.out.println("⏰ Задача 3 всё ещё выполняется");
        } catch (Exception e) {
            System.out.println("❌ Задача 3: ошибка - " + e.getMessage());
        }

        // Задача 5 (Callable)
        try {
            String result5 = future5.get(2, TimeUnit.SECONDS);
            System.out.println("✅ Задача 5: " + result5);
        } catch (TimeoutException e) {
            System.out.println("⏰ Задача 5 всё ещё выполняется");
        } catch (Exception e) {
            System.out.println("❌ Задача 5: ошибка - " + e.getMessage());
        }

        // Завершаем executor
        System.out.println("\n🏁 Завершаем executor...");
        executor.shutdown();
        boolean terminated = executor.awaitTermination(5, TimeUnit.SECONDS);

        if (!terminated) {
            System.out.println("⚠️ Принудительная остановка оставшихся задач");
            executor.shutdownNow();
        }

        // Финальный отчёт
        System.out.println("\n📊 ФИНАЛЬНЫЙ ОТЧЁТ (" + poolType + "):");
        System.out.println("-".repeat(40));

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.printf("⏱️ Общее время выполнения: %.1f сек\n", totalTime / 1000.0);

        int completed = 0, cancelled = 0, unknown = 0;

        for (TaskInfo task : tasks) {
            String status;
            if (task.future == null) {
                status = "выполнена (execute)";
                completed++;
            } else if (task.future.isCancelled()) {
                status = "❌ ОТМЕНЕНА";
                cancelled++;
            } else if (task.future.isDone()) {
                status = "✅ выполнена";
                completed++;
            } else {
                status = "⏳ неопределено";
                unknown++;
            }

            System.out.printf("%-10s [%s, %d сек]: %s\n",
                    task.name, task.method, task.duration, status);
        }

        System.out.println("-".repeat(40));
        System.out.printf("Итого: ✅ %d выполнено, ❌ %d отменено, ⏳ %d неопределено\n",
                completed, cancelled, unknown);

        // Особенности пула
        if (poolType.equals("CachedThreadPool")) {
            System.out.println("\n🔍 Особенности CachedThreadPool:");
            System.out.println("   • Может создать неограниченное количество потоков");
            System.out.println("   • Все задачи могут выполняться одновременно");
            System.out.println("   • Потоки удаляются через 60 сек неактивности");
        } else {
            System.out.println("\n🔍 Особенности FixedThreadPool(3):");
            System.out.println("   • Ровно 3 потока, задачи ждут в очереди");
            System.out.println("   • Предсказуемое потребление ресурсов");
            System.out.println("   • Может быть медленнее при большом количестве задач");
        }
    }

    // Создаёт Runnable задачу
    private static Runnable createRunnableTask(String taskName, int sleepSeconds) {
        return () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("  🏁 " + taskName + " СТАРТ [" + threadName + "]");

            try {
                // Имитируем работу с периодическими проверками на прерывание
                for (int i = 0; i < sleepSeconds * 10; i++) {
                    Thread.sleep(100);
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("  🚫 " + taskName + " ПРЕРВАНА [" + threadName + "]");
                        return;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("  🚫 " + taskName + " ПРЕРВАНА [" + threadName + "]");
                return;
            }

            System.out.println("  ✅ " + taskName + " ЗАВЕРШЕНА [" + threadName + "]");
        };
    }

    // Создаёт Callable задачу
    private static Callable<String> createCallableTask(String taskName, int sleepSeconds) {
        return () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("  🏁 " + taskName + " СТАРТ [" + threadName + "]");

            try {
                // Имитируем работу с периодическими проверками на прерывание
                for (int i = 0; i < sleepSeconds * 10; i++) {
                    Thread.sleep(100);
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("  🚫 " + taskName + " ПРЕРВАНА [" + threadName + "]");
                        return taskName + " - ПРЕРВАНА";
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("  🚫 " + taskName + " ПРЕРВАНА [" + threadName + "]");
                Thread.currentThread().interrupt(); // Восстанавливаем флаг
                return taskName + " - ПРЕРВАНА";
            }

            System.out.println("  ✅ " + taskName + " ЗАВЕРШЕНА [" + threadName + "]");
            return taskName + " завершилась успешно за " + sleepSeconds + " сек";
        };
    }

    // Получает статус задачи
    private static String getTaskStatus(Future<?> future) {
        if (future.isCancelled()) return "❌";
        if (future.isDone()) return "✅";
        return "⏳";
    }

    // Класс для хранения информации о задаче
    static class TaskInfo {
        final String name;
        final Future<?> future;
        final String method;
        final int duration;

        TaskInfo(String name, Future<?> future, String method, int duration) {
            this.name = name;
            this.future = future;
            this.method = method;
            this.duration = duration;
        }
    }
}