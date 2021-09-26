package com.jb.api.domain.service;

import com.jb.api.domain.entity.Item;
import com.jb.api.domain.entity.TaxTable;
import com.jb.api.domain.exception.BaseException;
import com.jb.api.domain.exception.InvalidTaxException;

import java.util.List;

public abstract class TaxCalculator {

    public Double calculate(Item item, List<TaxTable> taxTableList) {
        return (item.getPrice() * getTax(taxTableList)) / 100.0;
    }

    public abstract Double getTax(List<TaxTable> taxTableList);
}
