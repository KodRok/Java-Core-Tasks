package school.sorokin.javacore.streamapi.lesson1;


public class Main {
    public void testCalculator(MyCalculator calc) {
        double result = calc.calculate(10, 5);
        System.out.println("Результат: " + result);
    }

    public static void main(String[] args) {
        Main mainApp = new Main();

        MyCalculator sumWithAnonymousClass = new MyCalculator() {
            @Override
            public double calculate(double a, double b) {
                return a + b;
            }
        };
        System.out.println("Использование анонимного класса:");
        mainApp.testCalculator(sumWithAnonymousClass);
        System.out.println();


        MyCalculator sumWithLambda = (a, b) -> a + b;

        System.out.println("Использование лямбда-выражения:");
        mainApp.testCalculator(sumWithLambda);
    }
}
