package school.sorokin.javacore.exceptions.exceptions_lesson2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /**
     * Читает первую строку из файла. Это пример метода с проверяемым (checked) исключением.
     * Компилятор требует, чтобы этот метод был обернут в try-catch или его сигнатура содержала throws IOException.
     * Мы используем throws, чтобы передать ответственность за обработку исключения вызывающему методу (main).
     *
     * @param filename Имя файла для чтения.
     * @return Первая строка файла.
     * @throws IOException Если произошла ошибка ввода-вывода.
     */
    public static String readFirstLineWithThrows(String filename) throws IOException {
        // "try-with-resources" автоматически закроет BufferedReader после использования.
        try (BufferedReader reader = new BufferedReader(new FileReader((filename)))) {
            return reader.readLine();
        }
    }

    /**
     * Пытается получить доступ к элементу массива по несуществующему индексу.
     * Это пример метода с непроверяемым (unchecked) исключением.
     * Компилятор не требует, чтобы этот метод был обернут в try-catch.
     *
     * @param array Массив.
     * @param index Индекс для доступа.
     */
    public static void accessArrayElement(int[] array, int index) {
        System.out.println("Вы пытаетесь получить доступ к элементу по индексу: " + index);
        // Здесь произойдет ArrayIndexOutOfBoundsException, если индекс некорректен
        int value = array[index];
        System.out.println("Значение элемента: " + value);
    }

    public static void main(String[] args) {
        // --- Демонстрация проверяемого (checked) исключения ---
        String filename = "test.txt";

        System.out.println("--- 1. Демонстрация IOException ---");
        // Компилятор не даст скомпилировать код без try-catch или throws.
        try {
            String firstLine = readFirstLineWithThrows(filename);
            System.out.println("Первая строка из файла: " + firstLine);
        } catch (IOException e) {
            System.err.println("Поймано проверяемое исключение IOException: " + e.getMessage());
            System.err.println("Это исключение связано с внешними ресурсами (файлом), поэтому его нужно обрабатывать.");
        }

        // --- Демонстрация непроверяемого (unchecked) исключения ---
        System.out.println("\n--- 2. Демонстрация ArrayIndexOutOfBoundsException ---");
        int[] numbers = {10, 20, 30, 40, 50};

        // Мы можем обернуть вызов в try-catch, чтобы поймать ошибку и не дать программе "упасть".
        try {
            accessArrayElement(numbers, 10);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Поймано непроверяемое исключение: " + e.getMessage());
            System.err.println("Компилятор не требует его обрабатывать, но это хорошая практика для защиты программы.");
        }

        // --- Эксперимент с общим try-catch ---
        System.out.println("\n--- 3. Эксперимент с общим try-catch ---");
        try {
            // Вызов метода, который вызовет ошибку
            accessArrayElement(numbers, -1);
            // Если предыдущая строка не вызовет исключение, выполнится эта
            System.out.println("Эта строка не будет выполнена, потому что будет исключение.");
        } catch (Throwable t) {
            // Общий блок catch (Throwable) поймает и Error, и Exception
            System.err.println("Поймано любое исключение или ошибка: " + t.getClass().getName());
            System.err.println("Сообщение: " + t.getMessage());
        }
    }
}
