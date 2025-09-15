package school.sorokin.javacore.multithreading.lesson3;

public class OrderedResourceAcquisition {private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();

    public static void main(String[] args) {
        System.out.println("Хэш-коды ресурсов: ResourceA = " + System.identityHashCode(resourceA) +
                ", ResourceB = " + System.identityHashCode(resourceB));

        // Поток 1: пытается захватить ресурсы в определенном порядке
        Thread thread1 = new Thread(() -> acquireResources("Поток 1", resourceA, resourceB));

        // Поток 2: пытается захватить ресурсы в том же порядке
        Thread thread2 = new Thread(() -> acquireResources("Поток 2", resourceB, resourceA));

        thread1.start();
        thread2.start();
    }

    private static void acquireResources(String threadName, Object r1, Object r2) {
        Object firstLock, secondLock;

        // Определяем порядок захвата на основе хэш-кодов
        if (System.identityHashCode(r1) < System.identityHashCode(r2)) {
            firstLock = r1;
            secondLock = r2;
        } else {
            firstLock = r2;
            secondLock = r1;
        }

        System.out.println(threadName + " пытается захватить первый ресурс.");
        synchronized (firstLock) {
            System.out.println(threadName + " захватил первый ресурс.");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(threadName + " пытается захватить второй ресурс.");
            synchronized (secondLock) {
                System.out.println(threadName + " захватил второй ресурс.");
                System.out.println(threadName + " успешно выполнил операцию.");
            }
        }
    }
}
