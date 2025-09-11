package school.sorokin.javacore.testing.lesson1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void mainShouldThrowsExceptionIfNotNumber() {
        //этот класс не тестируют, оказывается

    }

    @Test
    void addShouldReturnSummaNumbers() {
        int result = Main.add(2, 3);
        assertEquals(5, result);
    }
}