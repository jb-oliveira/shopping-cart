package com.jb.api.domain.service;

import com.jb.api.application.placeOrder.PlaceOrderImputDTO;
import com.jb.api.application.placeOrder.PlaceOrderInputItemDTO;
import com.jb.api.application.placeOrder.PlaceOrderMapper;
import com.jb.api.application.placeOrder.PlaceOrderOutputDTO;
import com.jb.api.domain.entity.*;
import com.jb.api.domain.exception.BaseException;
import com.jb.api.domain.exception.InvalidCouponException;
import com.jb.api.domain.exception.InvalidItemException;
import com.jb.api.domain.factory.TaxCalculatorFactory;
import com.jb.api.domain.gateway.ZipCodeCalculatorApi;
import com.jb.api.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class OrderService {

    @Autowired
    private ZipCodeCalculatorApi zipCodeCalculatorApi;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TaxTableRepository taxTableRepository;
    @Autowired
    private SequenceGenerator sequenceGenerator;


    public Order create(PlaceOrderImputDTO input) throws BaseException {
        Long sequence = sequenceGenerator.generateId(SequenceGenerator.ORDER_ANUAL_SEQUENCE + "_" + LocalDate.now().getYear());
        TaxCalculator taxCalculator = TaxCalculatorFactory.create(input.getIssueDate());
        Order order = new Order(Cpf.parseCpf(input.getCpf()), input.getIssueDate(), sequence);
        Double distance = this.zipCodeCalculatorApi.distance(input.getZipCode(), "99.999-999");
        for (PlaceOrderInputItemDTO orderItem : input.getItems()) {
            Item item = this.itemRepository
                    .findById(orderItem.getItemId())
                    .orElseThrow(() -> new InvalidItemException("Item not found"));
            order.addItem(orderItem.getItemId(), item.getPrice(), orderItem.getQuantity());
            // Calcula o frete
            Double freight = FreightCalculator.calculate(distance, item) * orderItem.getQuantity();
            order.addFreight(freight);
            // calcula imposto
            List<TaxTable> taxTables = taxTableRepository.findByItemId(orderItem.getItemId());
            Double tax = taxCalculator.calculate(item, taxTables);
            order.addTax(tax * orderItem.getQuantity());
        }
        if (input.getCoupon() != null) {
            Coupon coupon = this.couponRepository
                    .findById(input.getCoupon())
                    .orElseThrow(() -> new InvalidCouponException("Coupon not found"));
            order.addCoupon(coupon);
        }
        order.validate();
        orderRepository.save(order);
        return order;
    }
}
