package school.sorokin.javacore.exceptions.exceptions_lesson1;

import java.util.Scanner;

/*Задание: Напишите программу, которая запрашивает у пользователя два числа и делит первое на второе.
Если второе число равно нулю,
программа должна перехватить исключение и вывести понятное сообщение об ошибке.
В любом случае, после попытки деления нужно вывести сообщение «Спасибо за использование программы».

Подсказки:
Используйте класс Scanner для считывания чисел из консоли.
Если пользователь ввёл «0» как второе число, при делении вылетит ArithmeticException.
При желании добавьте finally, чтобы продемонстрировать, что он выполняется всегда.*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите делимое");
        int firstNumber = scanner.nextInt();
        System.out.println("Введите делитель");
        int secondNumber = scanner.nextInt();
        try {
            System.out.printf("%d / %d = %d", firstNumber, secondNumber, firstNumber / secondNumber);
        } catch (ArithmeticException exception) {
            System.out.println("Деление на ноль запрещено.");
        } finally {
            System.out.println("\nСпасибо за использование программы");
        }
    }
}
