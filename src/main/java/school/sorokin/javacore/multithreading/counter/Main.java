package school.sorokin.javacore.multithreading.counter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        testCounter(new UnsynchronizedCounter(), 10);
        testCounter(new UnsynchronizedCounter(), 100);
        System.out.println("Быстро и неправильно. В многопоточной среде - бесполезно!");

        System.out.println();
        testCounter(new VolatileCounter(), 10);
        testCounter(new VolatileCounter(), 100);
        System.out.println("Быстро и некорректно: обеспечивает только видимость, а не атомарность.");

        System.out.println();
        testCounter(new SynchronizedBlockCounter(), 10);
        testCounter(new SynchronizedBlockCounter(), 100);
        System.out.println("Долго и правильно. " +
                "Решает проблему race condition за счет времени работы всей программы. " +
                "Блокирует критическую секцию для остальных потоков.");

        System.out.println();
        testCounter(new ReentrantLockCounter(), 10);
        testCounter(new ReentrantLockCounter(), 100);
        System.out.println("Правильно и долго. Потокобезопасность — это не бесплатно.");

        System.out.println();
        testCounter(new AtomicIntegerCounter(), 10);
        testCounter(new AtomicIntegerCounter(), 100);
        System.out.println("Быстро и корректно: " +
                "не блокирует куски кода, " +
                "\nобеспечивает атомарность за счет использования механизма CAS.");


    }

    private static void testCounter(SiteVisitCounter counter, int numberOfThreads) throws InterruptedException {
        MultithreadingSiteVisitor visitor = new MultithreadingSiteVisitor(counter, numberOfThreads);
        visitor.visitMultithread();
        visitor.waitUntilAllVisited();

        String result = String.format("Счетчик - %s, кол-во потоков - %d," +
                        " время: %.2f секунд, план - %d, факт - %d",
                counter.getClass().getSimpleName(),
                numberOfThreads,
                visitor.getTotalTimeOfHandling(),
                numberOfThreads,
                counter.getVisitCount());

        System.out.println(result);
    }
}

