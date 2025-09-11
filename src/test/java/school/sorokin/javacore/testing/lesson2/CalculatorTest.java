package school.sorokin.javacore.testing.lesson2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/*Затем создайте тестовый класс CalculatorTest с использованием JUnit:
Подключите JUnit
Создайте тестовые методы,
используя аннотации @Test, @BeforeEach (если нужно для подготовки объекта)
и, при необходимости, @AfterEach.
Проверьте не только положительные исходы,
но и негативные, например деление на 0 и крайние варианты исходов.
Примените стиль Arrange-Act-Assert в каждом тесте.
Используйте assertEquals для проверки корректности умножения.
Запустите тесты через IntelliJ IDEA и проверьте, что они проходят.
*/

class CalculatorTest {
    private int a;
    private int b;
    private Calculator calculator;

    @BeforeEach
    void init() {
        a = 3;
        b = 5;
        calculator = new Calculator(); // Предполагается, что есть класс Calculator
    }

    @Test
    void testSum() {
        int expectedResult = 8;
        int actualResult = calculator.add(a, b);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testMultiply() {
        int expectedResult = 15;
        int actualResult = calculator.multiply(a, b);
        assertEquals(expectedResult, actualResult);
    }
}
