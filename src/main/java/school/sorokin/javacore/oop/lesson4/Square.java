package school.sorokin.javacore.oop.lesson4;

public class Square extends Shape {

    @Override
    public void draw() {
        System.out.println("Drawing a Square");
    }

    @Override
    public double calculatePerimetr() {
        double a = 20;
        double b = 10;
        return 2 * (a + b);
    }
}
