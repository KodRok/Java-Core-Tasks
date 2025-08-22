package school.sorokin.javacore.basics;
/*Напишите программу, которая:
С помощью цикла for выводит на экран все числа от 1 до 100, которые делятся на 3.
Затем с помощью цикла while выводит обратный отсчёт от 10 до 1 (10, 9, 8, ...).
Использует вложенные циклы, чтобы вывести на экран таблицу умножения
(формата 1*1=1, 1*2=2, и т. д., до 9*9=81).
(Дополнительно) Остановите вложенный цикл, когда произведение станет больше 30, используя break.

Подсказка: в таблице умножения внешний цикл отвечает за число от 1 до 9, а внутренний — за второе число от 1 до 9.*/


public class CyclesLesson {
    public static void main(String[] args) {
        for (int i = 1; i < 101; i++) {
            if (i % 3 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        int num = 10;
        while (num >= 0) {
            System.out.println(num);
            num /= -1;
        }
        System.out.println();


        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                System.out.printf("%d * %d = %d\n", i, j, i * j);
            }
            System.out.println();
        }
        System.out.println();


        for (int i = 1; i < 10; i++) {

            for (int j = 1; j < 10; j++) {
                if (i * j > 30) {
                    break;
                }
                System.out.printf("%d * %d = %d\n", i, j, i * j);
            }
            System.out.println();

        }

    }
}
