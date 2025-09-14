package school.sorokin.javacore.multithreading.lesson1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedCounter sharedCounter = new SharedCounter();

        // Поток 1: Наследование от класса Thread
        Thread thread1 = new MyThread1(sharedCounter);

        // Поток 2: Реализация интерфейса Runnable
        Thread thread2 = new Thread(new MyRunnable(sharedCounter));

        // Поток 3: Анонимный класс
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    sharedCounter.counter++;
                }
            }
        });

        // Поток 4: Лямбда-выражение
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sharedCounter.counter++;
            }
        });

        // Запуск всех потоков
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        // Ожидание завершения всех потоков
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        // Вывод итогового значения
        System.out.println("Ожидаемый результат: 40000");
        System.out.println("Фактический результат: " + sharedCounter.counter);
    }
}
