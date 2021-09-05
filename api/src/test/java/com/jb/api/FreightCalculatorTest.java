package com.jb.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FreightCalculatorTest {

    @Test
    void shouldCalculateFreightOfItem() {
        Item guitar = new Item("1", "Guitarra", 1000.0, 100, 50, 15, 3.0);
        Double distance = 1000.0;
        Double value = FreightCalculator.calculate(distance, guitar);
        assertEquals(30, value);
        Item amplificador = new Item("2", "Amplificador", 5000.0, 50, 50, 50, 22.0);
        value = FreightCalculator.calculate(distance, amplificador);
        assertEquals(220, value);
        Item minimum = new Item("99999", "Minimu", 1.0, 1, 1, 1, 0.1);
        value = FreightCalculator.calculate(distance, minimum);
        assertEquals(10, value);
    }
}
