package school.sorokin.javacore.multithreading.counter;

public class VolatileCounter implements SiteVisitCounter {
    private volatile int visitCount = 0;

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        visitCount++;
    }

    @Override
    public int getVisitCount() {
        return visitCount;
    }
}
