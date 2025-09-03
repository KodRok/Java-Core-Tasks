package school.sorokin.javacore.exceptions.lesson5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*Задание: «Подсчёт слов в файле с защитой от ошибок»
Напишите программу, которая:
Просит пользователя ввести путь к файлу.
Считывает этот файл и считает, сколько всего слов (разделённых пробелами) в первой строке.
Обработайте следующие ошибки:
FileNotFoundException (если файла нет).
IOException (ошибка чтения).
InputMismatchException
(если пользователь вместо строки ввода что-то «сбоит»,
хотя для пути обычно это не критично, но представим, что у нас сложный сценарий ввода).
Любые другие, которые вы сочтёте нужными.
Выведите итоговое число слов или сообщение об ошибке.
В любом случае программа должна выводить «Спасибо за использование!»*/
public class Main {
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        int totalWordCount = 0;

        try {
            System.out.println("Введите абсолютный путь к файлу:");
            String filePath = consoleScanner.nextLine();
            try (Scanner fileScanner = new Scanner(new File(filePath))) {
                if (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        if (!word.trim().isEmpty()) {
                            totalWordCount++;
                        }
                    }
                    System.out.println("\nФайл успешно считан.");
                    System.out.println("Количество слов в первой строке: " + totalWordCount);
                } else {
                    System.out.println("Файл пуст. Количество слов в первой строке: 0");
                }
            } catch (FileNotFoundException e) {
                System.err.println("Файл не найден.");
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("Произошла ошибка ввода/вывода.");
                e.printStackTrace();
            }

        } catch (InputMismatchException e) {
            System.err.println("Ошибка ввода: Введены некорректные данные.");
        } finally {
            consoleScanner.close();
            System.out.println("Спасибо за использование!");
        }
    }
}


