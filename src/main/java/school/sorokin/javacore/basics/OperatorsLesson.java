package school.sorokin.javacore.basics;
/*Допустим, у вас есть три оценки студента (целые числа в диапазоне 0..10). Нужно:
Вычислить средний балл (просто (оценка1 + оценка2 + оценка3) / 3.0 как double).

Проверить, все ли оценки равны 10 (== 10).
Если да, то вывести особое сообщение "У студента все оценки максимальные!".

Проверить, есть ли хотя бы одна оценка 2 или ниже.
Если да, то вывести сообщение "Встречается очень низкая оценка!".

Если средний балл >= 5,
вывести "У студента удовлетворительная успеваемость", иначе — "У студента неудовлетворительная успеваемость".

Попробуйте добавить проверку: лежит ли каждая из оценок в диапазоне 0..10.
Если вдруг одна из оценок меньше 0 или больше 10, вывести "Ошибка: некорректная оценка!".*/

import java.util.Scanner;

public class OperatorsLesson {
    public static void main(String[] args) {
        byte mark1;
        byte mark2;
        byte mark3;
        Scanner scr = new Scanner(System.in);
        try {
            mark1 = scr.nextByte();
            mark2 = scr.nextByte();
            mark3 = scr.nextByte();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка: некорректная оценка!");
        }
        scr.close();

        if (mark1 < 0 || mark1 > 10
                || mark2 < 0 || mark2 > 10
                || mark3 < 0 || mark3 > 10) {
            System.out.println("Ошибка: некорректная оценка!");
            return;
        }

        double averageMark = (mark1 + mark2 + mark3) / 3.0;
        System.out.printf("Средний балл: %s", averageMark);
        System.out.println();

        if (mark1 == 10 && mark2 == 10 && mark2 == 10) {
            System.out.println("У студента все оценки максимальные!");
        }

        if (mark1 <= 2 || mark2 <= 2 || mark3 <= 3) {
            System.out.println("Встречается очень низкая оценка!");
        }

        if (averageMark >= 5) {
            System.out.println("У студента удовлетворительная успеваемость");
        } else {
            System.out.println("У студента неудовлетворительная успеваемость");
        }
    }
}
