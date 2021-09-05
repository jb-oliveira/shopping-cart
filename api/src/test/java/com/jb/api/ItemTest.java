package com.jb.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    void shouldCalculateItemVolume() {
        Item item = new Item("1", "Amplificaodr", 5000.0, 50, 50, 50, 22.0);
        Double volume = item.getVolume();
        assertEquals(0.125, volume);
    }

    @Test
    void shouldCalculateItemDensity() {
        Item item = new Item("1", "Amplificaodr", 5000.0, 50, 50, 50, 22.0);
        Double density = item.getDensity();
        assertEquals(176.0, density);
    }
}
