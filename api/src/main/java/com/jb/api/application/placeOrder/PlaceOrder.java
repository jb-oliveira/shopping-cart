package com.jb.api.application.placeOrder;

import com.jb.api.domain.entity.Coupon;
import com.jb.api.domain.entity.Item;
import com.jb.api.domain.entity.Order;
import com.jb.api.domain.exception.DomainException;
import com.jb.api.domain.exception.InvalidCouponException;
import com.jb.api.domain.exception.InvalidItemException;
import com.jb.api.domain.gateway.ZipCodeCalculatorApi;
import com.jb.api.domain.repository.CouponRepository;
import com.jb.api.domain.repository.ItemRepository;
import com.jb.api.domain.repository.OrderRepository;
import com.jb.api.domain.repository.SequenceGenerator;
import com.jb.api.domain.service.FreightCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PlaceOrder {

    @Autowired
    private ZipCodeCalculatorApi zipCodeCalculatorApi;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SequenceGenerator sequenceGenerator;
    @Autowired
    private PlaceOrderMapper mapper;


    public PlaceOrderOutputDTO execute(PlaceOrderImputDTO input) throws DomainException {
        Long sequence = sequenceGenerator.generateId(SequenceGenerator.ORDER_ANUAL_SEQUENCE+ "_" + LocalDate.now().getYear());
        Order order = new Order(input.getCpf(), input.getIssueDate(), sequence);
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
        orderRepository.save(order);
        return this.mapper.toOutputDto(order);
    }
}
