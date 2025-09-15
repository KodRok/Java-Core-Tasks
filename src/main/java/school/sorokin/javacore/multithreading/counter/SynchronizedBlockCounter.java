package school.sorokin.javacore.multithreading.counter;

public class SynchronizedBlockCounter implements SiteVisitCounter{
    private int visitCount = 0;
    private final Object lock = new Object();

    @Override
    public void incrementVisitCount() {
        synchronized (lock) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            visitCount++;
        }
    }

    @Override
    public int getVisitCount() {
        synchronized (lock) {
            return visitCount;
        }
    }
}
