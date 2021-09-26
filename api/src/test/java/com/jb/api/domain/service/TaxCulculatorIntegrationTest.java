package com.jb.api.domain.service;

import com.jb.api.domain.entity.Item;
import com.jb.api.domain.entity.TaxTable;
import com.jb.api.domain.factory.TaxCalculatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaxCulculatorIntegrationTest {


    @Test
    @DisplayName("Devbe calcular o imposto de um item Guitarra em meses normais")
    public void calculateTax() {
        Item guitarra = Item.builder()
                .id(1L)
                .description("Guitarra")
                .price(1000.0)
                .width(100)
                .height(50)
                .length(30)
                .weight(8.0)
                .build();
        List<TaxTable> taxTableList = Arrays.asList(new TaxTable(1L, "default", 15.0), new TaxTable(1L, "november", 5.0));
        LocalDate date = LocalDate.of(2021, 10, 10);
        TaxCalculator taxCalculator = TaxCalculatorFactory.create(date);
        Double amount = taxCalculator.calculate(guitarra, taxTableList);
        Assertions.assertEquals(150.0, amount);
    }

    @Test
    @DisplayName("Devbe calcular o imposto de um item Guitarra no mes de novemvbro")
    public void calculateTaxNovember() {
        Item guitarra = Item.builder()
                .id(1L)
                .description("Guitarra")
                .price(1000.0)
                .width(100)
                .height(50)
                .length(30)
                .weight(8.0)
                .build();
        List<TaxTable> taxTableList = Arrays.asList(new TaxTable(1L, "default", 15.0), new TaxTable(1L, "november", 5.0));
        LocalDate date = LocalDate.of(2021, 11, 11);
        TaxCalculator taxCalculator = TaxCalculatorFactory.create(date);
        Double amount = taxCalculator.calculate(guitarra, taxTableList);
        Assertions.assertEquals(50.0, amount);
    }

    @Test
    @DisplayName("Devbe calcular o imposto de um item Cabo")
    public void calculateCableTax() {
        Item cabo = Item.builder()
                .id(3L)
                .description("Cabo")
                .price(30.0)
                .width(10)
                .height(10)
                .length(10)
                .weight(1.0)
                .build();
        List<TaxTable> taxTableList = Arrays.asList(new TaxTable(3L, "default", 5.0), new TaxTable(3L, "november", 1.0));
        LocalDate date = LocalDate.of(2021, 10, 10);
        TaxCalculator taxCalculator = TaxCalculatorFactory.create(date);
        Double amount = taxCalculator.calculate(cabo, taxTableList);
        Assertions.assertEquals(1.5, amount);
    }

    @Test
    @DisplayName("Devbe calcular o imposto de um item Cabo em novembro")
    public void calculateCableTaxNovember() {
        Item cabo = Item.builder()
                .id(3L)
                .description("Cabo")
                .price(30.0)
                .width(10)
                .height(10)
                .length(10)
                .weight(1.0)
                .build();
        List<TaxTable> taxTableList = Arrays.asList(new TaxTable(3L, "default", 5.0), new TaxTable(3L, "november", 1.0));
        LocalDate date = LocalDate.of(2021, 11, 11);
        TaxCalculator taxCalculator = TaxCalculatorFactory.create(date);
        Double amount = taxCalculator.calculate(cabo, taxTableList);
        Assertions.assertEquals(0.3, amount);
    }
}
