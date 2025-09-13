package school.sorokin.javacore.streamapi.lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*Напишите программу, которая:
Создаёт список строк, где каждая строка представляет предложение
(например, “Java is fun”, “Stream API simplifies data processing”).
С помощью flatMap разбивает каждое предложение на отдельные слова и объединяет их в один стрим.
Применяет filter, чтобы оставить только слова длиной более 3 символов.
Применяет distinct для удаления дубликатов.
Сортирует полученные слова в алфавитном порядке.
Выводит итоговый список слов через forEach.
*/
public class Main {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("AJava is fun");
        stringList.add("Bfun");
        stringList.add("Cis fun");
        stringList.add("DJava is");
        stringList.add("Is");
        stringList.add("Is");
        stringList.add("Is");
        stringList.add(" Is ");
        stringList.add("FStream API simplifies data processing");
        Stream<String> stringStream = stringList.stream();
        stringStream.map(String::trim)
                .filter(s -> s.length() > 3)
                .distinct()
                .sorted()
                .forEach(System.out::println);

    }
}
