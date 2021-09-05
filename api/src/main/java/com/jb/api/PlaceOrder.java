package com.jb.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaceOrder {

    private List<Coupon> coupons;
    private List<Order> orders;

    public PlaceOrder() {
        coupons = new ArrayList<>();
        coupons.add(new Coupon("VALE20", 20.0, LocalDate.of(2021, 10, 10)));
        this.orders = new ArrayList<>();
    }

    public PlaceOrderOutputDTO execute(PlaceOrderImputDTO input) throws ApplicationException {
        Order order = new Order(input.getCpf());
        for (PlaceOrderInputItemDTO item : input.getItems()) {
            order.addItem(item.getDescription(), item.getPrice(), item.getQuantity());
        }
        if (input.getCoupon() != null) {
            Optional<Coupon> coupon = this.coupons
                    .stream()
                    .filter(c -> c.getDescription().equals(input.getCoupon()))
                    .findFirst();
            coupon.ifPresent(order::addCoupon);
        }
        order.validate();
        orders.add(order);
        return new PlaceOrderOutputDTO(order.getTotal());
    }
}
