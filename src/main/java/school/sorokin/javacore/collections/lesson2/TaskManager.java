package school.sorokin.javacore.collections.lesson2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaskManager {
    private List<String> tasks;

    // Конструктор, который позволяет выбрать тип списка
    public TaskManager(String listType) {
        if ("arraylist".equalsIgnoreCase(listType)) {
            this.tasks = new ArrayList<>();
        } else if ("linkedlist".equalsIgnoreCase(listType)) {
            this.tasks = new LinkedList<>();
        } else {
            System.out.println("Неверный тип списка. По умолчанию будет использоваться ArrayList.");
            this.tasks = new ArrayList<>();
        }
    }

    // Метод для добавления задачи в конец списка
    public void addTask(String task) {
        tasks.add(task);
        System.out.println("Задача '" + task + "' добавлена.");
    }

    // Метод для вставки задачи в определенную позицию
    public void insertTask(int index, String task) {
        if (index >= 0 && index <= tasks.size()) {
            tasks.add(index, task);
            System.out.println("Задача '" + task + "' вставлена на позицию " + index + ".");
        } else {
            System.out.println("Неверный индекс для вставки.");
        }
    }

    // Метод для удаления задачи по индексу
    public void removeTaskByIndex(int index) {
        if (index >= 0 && index < tasks.size()) {
            String removedTask = tasks.remove(index);
            System.out.println("Задача '" + removedTask + "' удалена с позиции " + index + ".");
        } else {
            System.out.println("Неверный индекс для удаления.");
        }
    }

    // Метод для удаления задачи по значению
    public boolean removeTaskByName(String task) {
        if (tasks.remove(task)) {
            System.out.println("Задача '" + task + "' удалена.");
            return true;
        } else {
            System.out.println("Задача '" + task + "' не найдена.");
            return false;
        }
    }

    // Метод для вывода всех задач
    public void displayTasks() {
        System.out.println("\nТекущий список задач:");
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println();
    }
}
