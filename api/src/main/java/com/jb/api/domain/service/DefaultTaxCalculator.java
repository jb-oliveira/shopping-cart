package com.jb.api.domain.service;

import com.jb.api.domain.entity.Item;
import com.jb.api.domain.entity.TaxTable;
import com.jb.api.domain.exception.BaseException;
import com.jb.api.domain.exception.InvalidTaxException;

import java.util.List;

public class DefaultTaxCalculator extends TaxCalculator {

    @Override
    public Double getTax(List<TaxTable> taxTables) {
        TaxTable taxTable = taxTables.stream()
                .filter(tt -> "default".equals(tt.getType()))
                .findFirst()
                .orElse(null);
        return taxTable == null ? 0.0 : taxTable.getValue();
    }
}
