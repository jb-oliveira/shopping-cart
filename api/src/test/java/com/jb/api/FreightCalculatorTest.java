package com.jb.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FreightCalculatorTest {

    @Test
    void shouldCalculateFreightOfItem(){
        Item guitar = new Item("1", "Guitarra", 1000.0, 100,50,15, 3);
        int distance = 1000;
        Double value = FreightCalculator.calculate(distance,guitar);
        assertEquals(value, 30);
        Item item = new Item("2", "Amplificador", 5000.0, 50,50,50, 22);
        value = FreightCalculator.calculate(distance,item);
        assertEquals(value, 220);
    }
}
