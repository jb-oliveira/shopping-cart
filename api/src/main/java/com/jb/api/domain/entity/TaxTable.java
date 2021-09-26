package com.jb.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TaxTable {

    private final Long itemId;
    private final String type;
    private final Double value;

}
