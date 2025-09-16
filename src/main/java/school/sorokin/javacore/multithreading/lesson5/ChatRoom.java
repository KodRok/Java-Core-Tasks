package school.sorokin.javacore.multithreading.lesson5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatRoom {
    // Используем CopyOnWriteArrayList, который потокобезопасен и
    // оптимизирован для сценариев с частым чтением и редкой записью.
    private final List<String> messages = new CopyOnWriteArrayList<>();

    // Метод для отправки нового сообщения в чат
    public void postMessage(String user, String text) {
        String message = String.format("[%s]: %s", user, text);
        System.out.println(user + " отправляет: " + text);
        messages.add(message);
    }

    // Метод для получения последних N сообщений
    public List<String> getRecentMessages(int count) {
        int size = messages.size();
        // Вычисляем начальный индекс, чтобы не выйти за пределы списка
        int startIndex = Math.max(0, size - count);
        // Возвращаем подсписок, содержащий последние сообщения
        return new ArrayList<>(messages.subList(startIndex, size));
    }
}
