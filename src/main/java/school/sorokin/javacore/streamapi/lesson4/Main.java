package school.sorokin.javacore.streamapi.lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("Alice", 30),
                new User("Bob", 22)));

        List<String> result = users.stream()
                .filter(user -> user.getAge() > 25) // Фильтруем по возрасту > 25
                .map(User::getName) // Извлекаем только имена
                .sorted() // Сортируем имена в алфавитном порядке
                .distinct() // Удаляем дубликаты
                .collect(Collectors.toList()); // Собираем результат в список

        System.out.println("Отфильтрованные, отсортированные и уникальные имена (возраст > 25):");
        System.out.println(result);


        int totalAge = users.stream()
                .map(User::getAge)
                .reduce(0, Integer::sum);
        // или
        // int totalAge = users.stream()
        //                     .mapToInt(User::getAge)
        //                     .sum();
        System.out.println("Общая сумма возрастов: " + totalAge);

        // Нахождение минимального возраста
        Optional<Integer> minAge = users.stream()
                .map(User::getAge)
                .reduce(Integer::min);
        minAge.ifPresent(age -> System.out.println("Минимальный возраст: " + age));
// Ожидаемый вывод: Минимальный возраст: 22

// Проверка на пустой Optional
        if (minAge.isEmpty()) {
            System.out.println("Список пользователей пуст.");
        }

        String longestString = Stream.of("apple", "banana", "kiwi", "grapefruit")
                .reduce("", (s1, s2) -> s1.length() > s2.length() ? s1 : s2);

        System.out.println("Самая длинная строка: " + longestString);
// Ожидаемый вывод: Самая длинная строка: grapefruit
    }
}

