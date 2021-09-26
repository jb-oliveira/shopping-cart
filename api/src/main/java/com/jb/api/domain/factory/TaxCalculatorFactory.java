package com.jb.api.domain.factory;

import com.jb.api.domain.service.DefaultTaxCalculator;
import com.jb.api.domain.service.NovemberTaxCalculator;
import com.jb.api.domain.service.TaxCalculator;

import java.time.LocalDate;
import java.time.Month;

public class TaxCalculatorFactory {

    public static TaxCalculator create(LocalDate date) {
        if (Month.NOVEMBER.equals(date.getMonth())) {
            return new NovemberTaxCalculator();
        }
        return new DefaultTaxCalculator();
    }
}
