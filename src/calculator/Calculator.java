
package calculator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {

    private static final Logger LOGGER =
        Logger.getLogger(Calculator.class.getName());

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    public static void main(String[] args) {

        LOGGER.log(Level.INFO, "Addition: {0}", add(10, 5));
        LOGGER.log(Level.INFO, "Subtraction: {0}", subtract(10, 5));
        LOGGER.log(Level.INFO, "Multiplication: {0}", multiply(10, 5));
        LOGGER.log(Level.INFO, "Division: {0}", divide(10, 5));

    }
}
