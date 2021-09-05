package com.jb.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Item {
    private final String id;
    private final String description;
    private final double price;
    private final int width;
    private final int height;
    private final int length;
    private final int weight;

    public Double getVolume() {
        return (this.width/100.0) * (this.height/100.0) * (this.length/100.0);
    }

    public Double getDensity() {
        return this.weight / getVolume();
    }
}
