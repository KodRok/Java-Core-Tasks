package school.sorokin.javacore.exceptions.lesson3;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;
        List<String> operationHistory = new ArrayList<>();

        while (continueCalculating) {
            System.out.println("Введите первое число:");
            int firstNum = getNum(scanner);

            System.out.println("Введите второе число:");
            int secondNum = getNum(scanner);

            logger.log(Level.INFO, "Введены числа: {0} и {1}", new Object[]{firstNum, secondNum});

            try {
                // Выполняем деление
                int result = firstNum / secondNum;
                logger.log(Level.INFO, "Результат деления: {0}", result);
                System.out.println("Результат деления: " + result);
                operationHistory.add("Деление " + firstNum + " / " + secondNum + " = " + result);
            } catch (ArithmeticException e) {
                // Перехватываем исключение, если второе число равно нулю
                String errorMessage = "Деление " + firstNum + " / " + secondNum + " завершилось ошибкой: деление на ноль";
                logger.log(Level.SEVERE, errorMessage, e);
                operationHistory.add(errorMessage);
            }

            // Спрашиваем пользователя, хочет ли он продолжить
            System.out.println("Хотите выполнить еще одну операцию? (да/нет)");
            String answer = scanner.next();
            if (!answer.equalsIgnoreCase("да")) {
                continueCalculating = false;
            }
        }

        System.out.println("--- Полная история операций ---");
        for (String operation : operationHistory) {
            System.out.println(operation);
        }

        System.out.println("Программа завершена.");
        scanner.close();
    }

    private static int getNum(Scanner scanner) {
        boolean validInput = false;
        int number = 0;

        while (!validInput) {
            try {
                number = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                logger.log(Level.SEVERE, "Нужно ввести число!", e);
                System.out.println("Попробуйте снова:");
                scanner.next(); // Очищаем буфер сканера
            }
        }
        return number;
    }
}
