package com.jb.api.application;

import com.jb.api.domain.entity.Coupon;
import com.jb.api.domain.entity.Item;
import com.jb.api.domain.entity.Order;
import com.jb.api.domain.exception.DomainException;
import com.jb.api.domain.exception.InvalidCouponException;
import com.jb.api.domain.exception.InvalidItemException;
import com.jb.api.domain.gateway.ZipCodeCalculatorApi;
import com.jb.api.domain.repository.CouponRepository;
import com.jb.api.domain.repository.ItemRepository;
import com.jb.api.domain.service.FreightCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceOrder {

    @Autowired
    private ZipCodeCalculatorApi zipCodeCalculatorApi;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CouponRepository couponRepository;

    private List<Order> orders;

    public PlaceOrder() {
        this.orders = new ArrayList<>();
    }

    public PlaceOrderOutputDTO execute(PlaceOrderImputDTO input) throws DomainException {
        Order order = new Order(input.getCpf());
        Double distance = this.zipCodeCalculatorApi.distance(input.getZipCode(), "99.999-999");
        for (PlaceOrderInputItemDTO orderItem : input.getItems()) {
            Item item = this.itemRepository
                    .findById(orderItem.getItemId())
                    .orElseThrow(() -> new InvalidItemException("Item not found"));
            order.addItem(orderItem.getItemId(), item.getPrice(), orderItem.getQuantity());
            Double freight = FreightCalculator.calculate(distance, item) * orderItem.getQuantity();
            order.addFreight(freight);
        }
        if (input.getCoupon() != null) {
            Coupon coupon = this.couponRepository
                    .findById(input.getCoupon())
                    .orElseThrow(() -> new InvalidCouponException("Coupon not found"));
            order.addCoupon(coupon);
        }
        order.validate();
        orders.add(order);
        return new PlaceOrderOutputDTO(order.getTotal(), order.getFreight());
    }
}
