package school.sorokin.javacore.multithreading.counter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {
    private int visitCount = 0;
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void incrementVisitCount() {
        lock.lock();
        try {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            visitCount++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getVisitCount() {
        lock.lock();
        try {
            return visitCount;
        } finally {
            lock.unlock();
        }
    }
}
