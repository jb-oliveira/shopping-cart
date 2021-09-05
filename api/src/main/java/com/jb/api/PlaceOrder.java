package com.jb.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaceOrder {

    private List<Coupon> coupons;
    private List<Order> orders;
    private List<Item> items;

    public PlaceOrder() {
        coupons = new ArrayList<>();
        coupons.add(new Coupon("VALE20", 20.0, LocalDate.of(2021, 10, 10)));
        coupons.add(new Coupon("VALE20_EXPIRED", 20.0, LocalDate.of(2020, 10, 10)));
        this.orders = new ArrayList<>();
        this.items = new ArrayList<>();
        this.items.add(new Item("1", "Guitarra", 1000.0, 100, 50, 15, 3));
        this.items.add(new Item("2", "Amplificador", 5000.0, 50, 50, 50, 22));
        this.items.add(new Item("3", "Cabo", 30.0, 10, 10, 10, 1));
    }

    public PlaceOrderOutputDTO execute(PlaceOrderImputDTO input) throws ApplicationException {
        Order order = new Order(input.getCpf());
        for (PlaceOrderInputItemDTO orderItem : input.getItems()) {
            Item item = this.items.stream()
                    .filter(i -> i.getId().equals(orderItem.getItemId()))
                    .findFirst()
                    .orElse(null);
            if (item == null) {
                throw new InvalidItemException("Item not found");
            }
            order.addItem(orderItem.getItemId(), item.getPrice(), orderItem.getQuantity());
        }
        if (input.getCoupon() != null) {
            Coupon coupon = this.coupons.stream()
                    .filter(c -> c.getDescription().equals(input.getCoupon()))
                    .findFirst()
                    .orElse(null);
            if (coupon == null) {
                throw new InvalidCouponException("Coupon not found");
            }
            order.addCoupon(coupon);
        }
        order.validate();
        orders.add(order);
        return new PlaceOrderOutputDTO(order.getTotal());
    }
}
