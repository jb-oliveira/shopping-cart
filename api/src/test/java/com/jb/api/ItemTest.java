package com.jb.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    void shouldCalculateItemVolume(){
        Item item = new Item("1", "Amplificaodr", 5000.0, 50,50,50, 22);
        Double volume = item.getVolume();
        assertEquals(volume, 0.125);
    }
    @Test
    void shouldCalculateItemDensity(){
        Item item = new Item("1", "Amplificaodr", 5000.0, 50,50,50, 22);
        Double density = item.getDensity();
        assertEquals(density, 176.0);
    }
}
