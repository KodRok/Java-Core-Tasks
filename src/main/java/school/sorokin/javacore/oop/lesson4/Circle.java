package school.sorokin.javacore.oop.lesson4;

public class Circle extends Shape {

    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }

    @Override
    public double calculatePerimetr() {
        double a = 3.14;
        double b = 10;
        return 2 * a * b;
    }
}
