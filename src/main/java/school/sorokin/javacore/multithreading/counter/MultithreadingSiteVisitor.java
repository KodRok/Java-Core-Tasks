package school.sorokin.javacore.multithreading.counter;

public class MultithreadingSiteVisitor {
    private final SiteVisitCounter counter;
    private final int numberOfThreads;
    private long startTime;
    private final Thread[] threads;

    public MultithreadingSiteVisitor(SiteVisitCounter counter, int numberOfThreads) {
        this.counter = counter;
        this.numberOfThreads = numberOfThreads;
        this.threads = new Thread[numberOfThreads];
    }

    public void visitMultithread() {
        startTime = System.nanoTime();
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(counter::incrementVisitCount);
            threads[i].start();
        }
    }

    public void waitUntilAllVisited() throws InterruptedException {
        for (Thread thread : threads) {
            if (thread != null) {
                thread.join();
            }
        }
    }

    public double getTotalTimeOfHandling() {
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }
}
