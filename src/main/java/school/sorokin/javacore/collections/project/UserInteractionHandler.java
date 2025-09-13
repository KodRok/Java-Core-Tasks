package school.sorokin.javacore.collections.project;

import school.sorokin.javacore.collections.project.myexceptions.ContactAlreadyExistsException;
import school.sorokin.javacore.collections.project.myexceptions.ContactNotFoundException;

import java.util.List;
import java.util.Scanner;

public class UserInteractionHandler {
    public static final String EMPTY_LIST_MSG = "Телефонная книга пуста.";
    private final Scanner scanner;
    private final PhoneBookManager phoneBookManager;
    private List<Contact> contacts;

    public UserInteractionHandler(PhoneBookManager phoneBookManager) {
        this.scanner = new Scanner(System.in);
        this.phoneBookManager = phoneBookManager;
    }

    public void start() {
        while (true) {
            printMenu();
            String userChoiceActionNumber = scanner.nextLine().trim();
            try {
                handleUserChoice(userChoiceActionNumber);
            } catch (ContactAlreadyExistsException | ContactNotFoundException | IllegalArgumentException e) {
                System.err.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Что-то пошло не так: " + e.getMessage());
            }
        }
    }

    private void handleUserChoice(String userChoiceActionNumber) {
        switch (userChoiceActionNumber) {
            case "1" -> getInputToAddContact();
            case "2" -> getInputToDeleteContact();
            case "3" -> printAllContacts();
            case "4" -> getInputToSearchContact();
            case "5" -> getContactsByGroup();
            case "0" -> {
                System.out.println("До свидания!");
                System.exit(0);
            }
            default -> System.err.println("Нужно ввести цифру от 1 до 5. Для выхода нажмите 0.");
        }
    }

    private void printMenu() {
        System.out.println("\nВыберите действие и нажмите соответствующую цифру:");
        System.out.println("1: Добавить контакт");
        System.out.println("2: Удалить контакт");
        System.out.println("3: Посмотреть все контакты");
        System.out.println("4: Найти контакт");
        System.out.println("5: Посмотреть контакты по группе");
        System.out.println("0: Выход");
    }

    private String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private Contact getContactNameAndPhone() {
        System.out.println("Введите данные контакта:");
        String name = getUserInput("Имя: ");
        String phone = getUserInput("Телефон: ");
        return new Contact(name, phone);
    }

    private void getInputToAddContact() {
        Contact contactNamePhone = getContactNameAndPhone();
        if (phoneBookManager.isExisted(contactNamePhone)) {
            throw new ContactAlreadyExistsException("Такой контакт уже есть!");
        }
        String email = getUserInput("Email: ");
        Group group = getInputToDetermineGroup();
        Contact newContact = new Contact(contactNamePhone.getName(), contactNamePhone.getPhone(),
                email, group);
        phoneBookManager.addContact(newContact);
        System.out.println("Контакт добавлен.");
    }

    private void getInputToDeleteContact() {
        if (isPhoneBookEmpty()) {
            System.out.println(EMPTY_LIST_MSG);
            return;
        }
        Contact deletingContact = getContactNameAndPhone();
        phoneBookManager.deleteContact(deletingContact.getName(), deletingContact.getPhone());
        System.out.println("Контакт удален.");
    }

    private void printAllContacts() {
        if (isPhoneBookEmpty()) {
            System.out.println(EMPTY_LIST_MSG);
            return;
        }
        System.out.println("\nСписок контактов: ");
        contacts.forEach(System.out::println);
    }

    private void getInputToSearchContact() {
        if (isPhoneBookEmpty()) {
            System.out.println(EMPTY_LIST_MSG);
            return;
        }
        Contact searchingContact = getContactNameAndPhone();
        Contact existedContact = phoneBookManager.searchContact(searchingContact.getName(),
                searchingContact.getPhone());

        if (existedContact == null) {
            throw new ContactNotFoundException("Не найден.");
        } else {
            System.out.println("Найден контакт: " + existedContact);
        }
    }

    private boolean isPhoneBookEmpty() {
        contacts = phoneBookManager.getAllContacts();
        return contacts.isEmpty();
    }

    private void getContactsByGroup() {
        if (isPhoneBookEmpty()) {
            System.out.println(EMPTY_LIST_MSG);
            return;
        }

        Group group = getInputToDetermineGroup();
        List<Contact> groupContacts = phoneBookManager.getContactsByGroup(group);
        if (groupContacts.isEmpty()) {
            throw new ContactNotFoundException("В этой группе нет контактов.");
        }

        System.out.println("\nГруппа \"" + group.name() + "\" содержит контакты: ");
        groupContacts.forEach(System.out::println);
    }

    private Group getInputToDetermineGroup() {
        System.out.println("Выберите группу:");
        for (Group g : Group.values()) {
            System.out.println(g.getValue() + ": " + g.name());
        }

        while (true) {
            System.out.print("Введите номер группы: ");
            try {
                int groupInput = Integer.parseInt(scanner.nextLine());
                return Group.fromValue(groupInput);
            } catch (NumberFormatException e) {
                System.err.println("Нужно ввести число.");
            } catch (IllegalArgumentException e) {
                System.err.println("Группы с таким номером нет! Пожалуйста, введите число из списка.");
            }
        }
    }
}