package school.sorokin.javacore.oop.lesson7;

import java.util.Objects;

/*Задача:
Создайте класс Book с полями title (название) и author (автор).
Переопределите в классе Book методы toString(), equals() и hashCode() так,
чтобы два объекта считались равными, если их title и author совпадают.
Напишите класс Library, где в методе main создайте несколько объектов Book,
сравните их между собой с помощью equals(),
выведите их строковое представление через toString()
и продемонстрируйте корректность работы переопределённых методов.*/
public class Book {
    String title;
    String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

   /* @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}
