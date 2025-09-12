package school.sorokin.javacore.reflection.lesson2;

public class Student {
    private String name;
    private int age;
    public String university;

    // Публичный конструктор с параметрами
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.university = "University of Science and Technology";
        System.out.println("Публичный конструктор вызван.");
    }

    // Приватный конструктор без параметров
    private Student() {
        this.name = "Unknown";
        this.age = 0;
        this.university = "No University";
        System.out.println("Приватный конструктор вызван.");
    }

    // Публичный метод
    public void introduce() {
        System.out.println("Привет, меня зовут " + name + ", " +
                "мне " + age + " лет, и я учусь в " + university + ".");
    }

    // Приватный метод
    private void studySecretly() {
        System.out.println(name + " тайно изучает квантовую физику.");
    }
}
