package school.sorokin.javacore.multithreading.lesson1;

public class MyRunnable implements  Runnable{
    private SharedCounter sharedCounter;

    public MyRunnable(SharedCounter sharedCounter) {
        this.sharedCounter = sharedCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            sharedCounter.counter++;
        }
    }
}
