package school.sorokin.javacore.multithreading.lesson3;

public class Main {
    private final static int THREADS_COUNT = 100;

    public static void main(String[] args) {

        Object obj1 = new Object();
        Object obj2 = new Object();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < THREADS_COUNT; i++) {
                synchronized (obj1){
                    System.out.println("First grabbed the first monitor");
                    synchronized (obj2){
                        System.out.println("First grabbed the second monitor");
                        System.out.println();
                    }
                }
            }
        });

        Thread thread2 = new Thread(()->{
            synchronized ((obj1)){
                System.out.println("Second grabbed the second");
                synchronized (obj2){
                    System.out.println("Second grabbed the first");
                    System.out.println();
                }
            }
        }
    );
        thread1.start();
        thread2.start();
    }
}
