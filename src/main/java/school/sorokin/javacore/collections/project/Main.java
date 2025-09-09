package school.sorokin.javacore.collections.project;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    static final PhoneBookManager phoneBookManager = new PhoneBookManager();

    public static void main(String[] args) {
        UserInteractionHandler handler = new UserInteractionHandler(phoneBookManager);
        handler.start();
        scanner.close();
    }
}
