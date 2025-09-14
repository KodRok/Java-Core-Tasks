package school.sorokin.javacore.multithreading.lesson1;

public class MyThread1 extends Thread{
    private SharedCounter sharedCounter;

    public MyThread1(SharedCounter sharedCounter) {
        this.sharedCounter = sharedCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            sharedCounter.counter++;
        }
    }
}