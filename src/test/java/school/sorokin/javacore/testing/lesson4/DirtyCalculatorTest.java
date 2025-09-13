package school.sorokin.javacore.testing.lesson4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirtyCalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void testSum() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(1, calculator.subtract(3, 2));
    }
}
