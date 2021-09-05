package com.jb.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Cpf cpf;
    private List<OrderItem> items;

    public Order(String cpf) {
        this.cpf = new Cpf(cpf);
        this.items = new ArrayList<>();
    }

    public void addItem(String description, Double price, int quantity){
        this.items.add( new OrderItem(description,price,quantity) );
    }

    public void validate() throws InvalidCpfException {
        cpf.validate();
    }

    public Double getTotal(){
        return this.items.stream().mapToDouble(OrderItem::getTotal).sum();
    }
}

