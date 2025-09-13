package school.sorokin.javacore.streamapi.lesson2;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*Напишите консольное приложение, которое:
Создаёт несколько стримов с использованием различных методов создания
(из коллекции, массива, отдельных элементов, генераторов).
Применяет к созданным стримам одну или две промежуточные операции
(например, filter для отбора элементов по условию).
Выводит результат работы с помощью терминальной операции forEach.

Подсказка:
Попробуйте создать стрим чисел, отфильтровать только чётные числа, а затем вывести их.
Сгенерируйте стрим случайных чисел, ограничьте его 10 элементами и выведите результат.*/
public class Main {
    public static void main(String[] args) {
       Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .filter((n) -> n % 2 == 0)
                .forEach(System.out::println);
        Random random = new Random();
        IntStream intStream = random.ints(10);
        intStream.forEach(System.out::println);
       /* Stream<Double> randomNumbers = Stream.generate(Math::random);
        randomNumbers.limit(5).forEach(System.out::println);

        Stream<Integer> evenNumbers = Stream.iterate(0, n -> n + 2);
        evenNumbers.limit(5).forEach(System.out::println);*/

    }
}
