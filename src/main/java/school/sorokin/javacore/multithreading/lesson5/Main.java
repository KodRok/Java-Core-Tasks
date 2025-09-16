package school.sorokin.javacore.multithreading.lesson5;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Создаем экземпляр чат-комнаты
        ChatRoom chatRoom = new ChatRoom();

        // Создаем пул потоков для управления нашими "пользователями" и "читателем"
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

        // Запуск "пользователей" (потоков, которые пишут сообщения)
        executor.scheduleAtFixedRate(() -> chatRoom
                .postMessage("User1", "Привет всем!"), 0, 2, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(() -> chatRoom
                .postMessage("User2", "Как дела?"), 0, 3, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(() -> chatRoom
                .postMessage("User3", "Отличный день сегодня!"),
                0, 4, TimeUnit.SECONDS);

        // Запуск "читателя" (потока, который читает последние сообщения)
        executor.scheduleAtFixedRate(() -> {
            System.out.println("\n*** Читатель проверяет последние сообщения ***");
            List<String> recentMessages = chatRoom.getRecentMessages(5);
            if (recentMessages.isEmpty()) {
                System.out.println("(Сообщений пока нет)");
            } else {
                recentMessages.forEach(System.out::println);
            }
            System.out.println("----------------------------------------\n");
        }, 0, 5, TimeUnit.SECONDS);

        // Даем потокам поработать 30 секунд
        System.out.println("Симуляция чата на 30 секунд. Нажмите Ctrl+C, чтобы остановить.");
        TimeUnit.SECONDS.sleep(30);

        // Корректно завершаем работу пула потоков
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("\nСимуляция завершена.");
    }
}
