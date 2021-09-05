package com.jb.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Cpf cpf;
    private List<OrderItem> items;
    private Coupon coupon;

    public Order(String cpf) {
        this.cpf = new Cpf(cpf);
        this.items = new ArrayList<>();
    }

    public void addItem(String description, Double price, int quantity){
        this.items.add( new OrderItem(description,price,quantity) );
    }

    public void validate() throws ApplicationException {
        cpf.validate();
        if( this.coupon != null && coupon.isExpired() ){
            throw new ExpiredCouponException("Expired coupon");
        }
    }

    public Double getTotal(){
        double total = this.items.stream().mapToDouble(OrderItem::getTotal).sum();
        if( coupon != null ){
            total -= (total*coupon.getPercent())/100;
        }
        return total;
    }

    public void addCoupon(Coupon coupon){
        this.coupon = coupon;
    }
}

