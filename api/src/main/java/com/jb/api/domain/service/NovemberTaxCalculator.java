package com.jb.api.domain.service;

import com.jb.api.domain.entity.TaxTable;

import java.util.List;

public class NovemberTaxCalculator extends TaxCalculator {
    @Override
    public Double getTax(List<TaxTable> taxTables) {
        TaxTable taxTable = taxTables.stream()
                .filter(tt -> "november".equals(tt.getType()))
                .findFirst()
                .orElse(null);
        return taxTable == null ? 0.0 : taxTable.getValue();
    }
}
