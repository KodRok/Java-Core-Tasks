package school.sorokin.javacore.multithreading.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter implements SiteVisitCounter{
    private final AtomicInteger visitCount = new AtomicInteger(0);

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        visitCount.incrementAndGet();
    }

    @Override
    public int getVisitCount() {
        return visitCount.get();
    }
}
