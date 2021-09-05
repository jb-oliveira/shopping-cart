package com.jb.api;

public class FreightCalculator {

    public static Double calculate(int distance, Item item) {
        return distance * item.getVolume() * (item.getDensity() / 100);
    }
}
