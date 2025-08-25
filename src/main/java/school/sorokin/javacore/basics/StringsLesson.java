package school.sorokin.javacore.basics;
/*Напишите программу, которая:

Считывает строку с клавиатуры (например, "Hello, Java World!").
Выводит длину этой строки.
Выводит её в верхнем регистре.
Проверяет, содержится ли в строке слово "Java".
Если да — выводит индекс первого вхождения, если нет — пишет, что не найдено.
Делит строку на слова (разделитель — пробел) и выводит все слова по одному в консоль.

Подсказка: используйте Scanner для считывания пользовательского ввода,
методы .contains() или .indexOf() для поиска подстроки и .split(" ") для разбиения на слова.*/

import java.util.Scanner;

public class StringsLesson {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        scanner.close();
        System.out.println("Длина введенной строки: " + str.length());
        System.out.println(str.toUpperCase());

        if (str.contains("Java")) {
            System.out.println(str.indexOf("Java"));
        } else {
            System.out.println("\"Java\" не найдено");
        }

       String[] words = str.split(" ");
        for(String word:words){
            System.out.print(word + " ");
        }
    }
}
