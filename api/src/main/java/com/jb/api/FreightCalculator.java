package com.jb.api;

public class FreightCalculator {

    public static Double calculate(Double distance, Item item) {
        double value = distance * item.getVolume() * (item.getDensity() / 100);
        return Math.max(value, 10.0);
    }
}
