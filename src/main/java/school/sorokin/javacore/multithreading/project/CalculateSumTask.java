package school.sorokin.javacore.multithreading.project;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CalculateSumTask implements Callable<Integer> {

    private final List<Integer> numbers;
    private final String taskName;

    public CalculateSumTask(List<Integer> numbers, String taskName) {
        this.numbers = numbers;
        this.taskName = taskName;
    }

    @Override
    public Integer call() throws Exception {
        System.out.printf("Выполняем %s в потоке %s%n", taskName, Thread. currentThread ().getName());
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.printf("Сумма %s - %d%n", taskName, sum);
        System.out.println();
        return sum;
    }
}

