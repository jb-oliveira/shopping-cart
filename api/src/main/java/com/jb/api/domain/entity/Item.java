package com.jb.api.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@RequiredArgsConstructor
@Builder
public class Item {
    @Id
    private final Long id;
    private final String description;
    private final Double price;
    private final int width;
    private final int height;
    private final int length;
    private final Double weight;

    public Double getVolume() {
        return (this.width / 100.0) * (this.height / 100.0) * (this.length / 100.0);
    }

    public Double getDensity() {
        return this.weight / getVolume();
    }
}
