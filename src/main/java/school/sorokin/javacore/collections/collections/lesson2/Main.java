package school.sorokin.javacore.collections.collections.lesson2;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Демонстрация работы с ArrayList ---");
        TaskManager arrayListManager = new TaskManager("arraylist");

        arrayListManager.addTask("Помыть посуду");
        arrayListManager.addTask("Сделать домашнее задание");
        arrayListManager.addTask("Купить продукты");
        arrayListManager.displayTasks();

        arrayListManager.insertTask(1, "Позвонить другу");
        arrayListManager.displayTasks();

        arrayListManager.removeTaskByIndex(0);
        arrayListManager.displayTasks();

        arrayListManager.removeTaskByName("Сделать домашнее задание");
        arrayListManager.displayTasks();

        System.out.println("\n--- Демонстрация работы с LinkedList ---");
        TaskManager linkedListManager = new TaskManager("linkedlist");

        linkedListManager.addTask("Заплатить по счетам");
        linkedListManager.addTask("Прочитать книгу");
        linkedListManager.displayTasks();

        linkedListManager.insertTask(1, "Написать письмо");
        linkedListManager.displayTasks();

        linkedListManager.removeTaskByIndex(1);
        linkedListManager.displayTasks();

        linkedListManager.removeTaskByName("Заплатить по счетам");
        linkedListManager.displayTasks();
    }
}
