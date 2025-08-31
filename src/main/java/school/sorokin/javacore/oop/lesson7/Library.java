package school.sorokin.javacore.oop.lesson7;
/*Задача:
Создайте класс Book с полями title (название) и author (автор).
Переопределите в классе Book методы toString(), equals() и hashCode() так,
чтобы два объекта считались равными, если их title и author совпадают.
Напишите класс Library, где в методе main создайте несколько объектов Book,
сравните их между собой с помощью equals(),
выведите их строковое представление через toString()
и продемонстрируйте корректность работы переопределённых методов.*/
public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("Достоевский", "Бесы");
        Book book2 = new Book("Достоевский", "Идиот");
        Book book3= new Book("Замятин", "Мы");
        Book book4 = new Book("Достоевский", "Идиот");
        System.out.println(book4.equals(book2));
        System.out.println(book4.equals(book1));
        System.out.println(book4.equals(book3));
        System.out.println(book1.equals(book3));
        System.out.println(book1);
    }
}
