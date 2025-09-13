package school.sorokin.javacore.testing.lesson1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int result = add(a, b);
            System.out.println(result);
        } catch (Exception e) {
            throw new IllegalArgumentException("Але, введи число");
        }
    }

    public static int add(int a, int b) {
        return a + b;
    }
}

