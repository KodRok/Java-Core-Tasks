package school.sorokin.javacore.multithreading.lesson4;

import java.util.concurrent.*;

public class CallableClass {
    public static void main(String[] args) throws ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<String> futureResult1 = executorService.submit(() -> {
            String str = "";
            try {
                str = "Задача 1 выполняется в потоке: " + Thread.currentThread().getName();
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return str;
        });


        try {
            String result1 = futureResult1.get(3, TimeUnit.SECONDS);
            System.out.println("Результат: " + result1);
            System.out.println(futureResult1.isDone());
        } catch (TimeoutException e) {
            System.out.println("Время ожидания истекло. Можно отменить задачу.");
            futureResult1.cancel(true);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Future<String> futureResult2 = executorService.submit(() -> {
            String str = "";
            try {
                str = "Задача 2 выполнилась в потоке: " + Thread.currentThread().getName();
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return str;
        });

        try {
            String result2 = futureResult2.get(3, TimeUnit.SECONDS);
            System.out.println("Результат: " + result2);
            System.out.println(futureResult2.isDone());
        } catch (TimeoutException e) {
            System.out.println("Время ожидания истекло. Можно отменить задачу.");
            futureResult2.cancel(true);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Future<String> futureResult3 = executorService.submit(() -> {
            String str = "";
            try {
                str = "Задача 3 выполняется в потоке: " + Thread.currentThread().getName();
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return str;
        });

        try {
            String result = futureResult3.get(3, TimeUnit.SECONDS);
            System.out.println("Результат: " + result);
            System.out.println(futureResult3.isCancelled()
            );
        } catch (TimeoutException e) {
            System.out.println("Время ожидания истекло. Можно отменить задачу.");
            futureResult3.cancel(true);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Future<String> futureResult4 = executorService.submit(() -> {
            String str = "";
            try {
                str = "Задача 4 выполняется в потоке: " + Thread.currentThread().getName();
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return str;
        });

        try {
            String result4 = futureResult4.get(3, TimeUnit.SECONDS);
            System.out.println("Результат: " + result4);
        } catch (TimeoutException e) {
            System.out.println("Время ожидания истекло. Можно отменить задачу.");
            futureResult4.cancel(true);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Future<String> futureResult5 = executorService.submit(() -> {
            String str = "";
            try {
                str = "Задача 5 выполняется в потоке: " + Thread.currentThread().getName();
                TimeUnit.SECONDS.sleep(9);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return str;
        });

        try {
            String result5 = futureResult5.get(3, TimeUnit.SECONDS);
            System.out.println("Результат: " + result5);
        } catch (TimeoutException e) {
            System.out.println("Время ожидания истекло. Можно отменить задачу.");
            futureResult5.cancel(true);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
    }
}
