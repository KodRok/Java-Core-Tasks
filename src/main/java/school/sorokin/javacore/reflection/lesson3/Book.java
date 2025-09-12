package school.sorokin.javacore.reflection.lesson3;


public class Book {

    private String title;

    public Book(String title) {
        this.title = title;
    }

    @Author(name = "Иван Петров", date = "20.10.2023")
    public void read() {
        System.out.println("Reading the book: " + title);
    }

    @Author(name = "Мария Сидорова", date = "15.05.2024")
    public void writeChapter(String chapterName) {
        System.out.println("Writing chapter: " + chapterName);
    }

    public void openBook() {
        System.out.println("Opening the book.");
    }
}

