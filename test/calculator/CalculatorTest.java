
package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void testAdd() {
        assertEquals(16, Calculator.add(10, 5));
    }

    @Test
    void testSubtract() {
        assertEquals(5, Calculator.subtract(10, 5));
    }

    @Test
    void testMultiply() {
        assertEquals(50, Calculator.multiply(10, 5));
    }

    @Test
    void testDivide() {
        assertEquals(2, Calculator.divide(10, 5));
    }

    @Test
    void testDivideByZero() {
        assertThrows(
            ArithmeticException.class,
            () -> Calculator.divide(10, 0)
        );
    }
}
