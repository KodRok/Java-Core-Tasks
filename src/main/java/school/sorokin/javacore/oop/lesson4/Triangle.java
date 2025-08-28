package school.sorokin.javacore.oop.lesson4;

public class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Triangle");
    }

    @Override
    public double calculatePerimetr() {
        double a = 3.14;
        double b = 10;
        double c = 5;
        return a + b + c;
    }
}
