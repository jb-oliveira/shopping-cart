package com.jb.api;

import com.jb.api.domain.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemUnitTest {

    @DisplayName("Deve clcular o volume de um item.")
    @Test
    void shouldCalculateItemVolume() {
        Item item = new Item("1", "Amplificaodr", 5000.0, 50, 50, 50, 22.0);
        Double volume = item.getVolume();
        assertEquals(0.125, volume);
    }

    @DisplayName("Deve calcular a desnidade do item")
    @Test
    void shouldCalculateItemDensity() {
        Item item = new Item("1", "Amplificaodr", 5000.0, 50, 50, 50, 22.0);
        Double density = item.getDensity();
        assertEquals(176.0, density);
    }
}
