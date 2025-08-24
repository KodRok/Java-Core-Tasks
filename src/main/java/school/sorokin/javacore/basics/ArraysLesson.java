package school.sorokin.javacore.basics;
/*Напишите программу, которая:

Создаёт массив из 10 целых чисел (заполните их случайными значениями или вводом с клавиатуры).
Выводит все элементы массива на экран.
Определяет и выводит индекс минимального и максимального элемента (или сами эти элементы).
Сортирует массив по возрастанию (можно любым способом, например, «пузырьком») и выводит результат.
(Опционально) Создаёт двумерный массив размером 2×5, заполняет его и выводит на экран в виде таблицы.

Подсказка: используйте циклы для заполнения и обхода массивов, методы для поиска и сортировки элементов.*/

import java.util.Random;

public class ArraysLesson {
    public static void main(String[] args) {
        int[] arr = new int[10];
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            arr[i] = rnd.nextInt(25);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "; ");
        }
        System.out.println();

        int max_num = arr[0];
        int min_num = arr[0];
        int min_ind = 0;
        int max_ind = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max_num) {
                max_num = arr[i];
                max_ind = i;
            }
            if (arr[i] < min_num) {
                min_num = arr[i];
                min_ind = i;
            }
        }
        System.out.printf("В массиве минимальное значение %d у элемента с индексом %d", min_num, min_ind);
        System.out.println();
        System.out.printf("В массиве максимальное значение %d у элемента с индексом %d", max_num, max_ind);
        System.out.println();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("Отсортированный массив по возрастанию:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("Отсортированный массив по убыванию:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("Двумерный массив:");
        int[][] num = new int[2][5];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                num[i][j] = rnd.nextInt(38);
            }
        }

        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i].length; j++) {
                System.out.print(num[i][j] + " ");
            }
            System.out.println();
        }

    }
}
