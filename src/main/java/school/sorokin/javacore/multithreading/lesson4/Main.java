package school.sorokin.javacore.multithreading.lesson4;

import java.util.concurrent.*;

/*Задача: Написать простую программу, которая:
Создаёт пул потоков с фиксированным количеством (3 потока).
Запускает 5 задач (пускай каждая «спит» разное время, например 1–3 секунды),
 используя разные методы (execute() и submit()).
Для задач, запущенных через submit(Callable), выводит результат.
Для простоты, каждая задача может возвращать строку с текстом «Задача №N завершилась».
Одна из задач должна «спать» особенно долго (8 секунд).
 Попробуйте дождаться её результат в течение 3 секунд через get(3, TimeUnit.SECONDS),
 и если не успеет, отмените её.
В конце выведите информацию о том, какие задачи успешно выполнились, а какие были отменены.

Подсказки:
Используйте isCancelled() или isDone() для проверки состояния задач.
Поэкспериментируйте с newCachedThreadPool() и сравните поведение.*/
public class Main {

    public static void main(String[] args) throws ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(() -> {
            try {
                System.out.println("Задача 1 выполняется в потоке: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executorService.submit(() -> {
            try {
                System.out.println("Задача 2 выполняется в потоке: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executorService.execute(() -> {
            try {
                System.out.println("Задача 3 выполняется в потоке: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Future<String> futureResult = executorService.submit(() -> {
            String str = "";
            try {
                str = "Задача 4 выполняется в потоке: " + Thread.currentThread().getName();
                TimeUnit.SECONDS.sleep(9);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return str;
        });

        try {
            String result = futureResult.get(3, TimeUnit.SECONDS);
            System.out.println("Результат: " + result);
        } catch (TimeoutException e) {
            System.out.println("Время ожидания истекло. Можно отменить задачу.");
            futureResult.cancel(true);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.execute(() -> {
            try {
                System.out.println("Задача 5 выполняется в потоке: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        executorService.shutdown();
    }
}
