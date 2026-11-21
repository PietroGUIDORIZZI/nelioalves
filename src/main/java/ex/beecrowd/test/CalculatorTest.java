package ex.beecrowd.test;

import ex.beecrowd.main.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void addTest() {
        Calculator calculator = new Calculator();
        int result = calculator.add(10, 30);
        assertEquals(40, result);
    }

    @Test
    public void subtractTest() {
        Calculator calculator = new Calculator();
        double result = calculator.subtract(30.0, -600);
        assertEquals(-570.00, result);
        
    }
}
