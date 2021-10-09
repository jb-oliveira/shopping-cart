package com.jb.api.infra.jpa.repository;

import com.jb.api.domain.entity.Coupon;
import com.jb.api.domain.entity.Cpf;
import com.jb.api.domain.entity.Order;
import com.jb.api.domain.exception.InvalidCpfException;
import com.jb.api.domain.repository.CouponRepository;
import com.jb.api.domain.repository.OrderRepository;
import com.jb.api.infra.jpa.entity.OrderItemJpa;
import com.jb.api.infra.jpa.entity.OrderJpa;
import com.jb.api.infra.jpa.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepositoryJpa orderTemplate;

    @Autowired
    private OrderItemRepositoryJpa orderItemTemplate;

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Order save(Order order) {
        OrderJpa document = orderMapper.fromOrder(order);
        OrderJpa savedDocument = orderTemplate.save(document);
        Long orderId = savedDocument.getId();
        order.setId(orderId);
        order.getItems().forEach(orderItem -> {
            OrderItemJpa orderItemJpa = new OrderItemJpa(null, orderId, orderItem.getItemId(), orderItem.getPrice(), orderItem.getQuantity());
            orderItemJpa = orderItemTemplate.save(orderItemJpa);
            orderItem.setId(orderItemJpa.getId());
        });
        return order;
    }

    @Override
    public Optional<Order> findByCode(String code) throws InvalidCpfException {
        OrderJpa orderJpa = this.orderTemplate.findBycode(code);
        if (orderJpa == null) {
            return Optional.empty();
        }
        Order order = new Order(Cpf.parseCpf(orderJpa.getCpf()), orderJpa.getIssueDate(), orderJpa.getSequence());
        order.setId(orderJpa.getId());
        order.addFreight(orderJpa.getFreight());
        List<OrderItemJpa> itemJpas = this.orderItemTemplate.findByOrderId(orderJpa.getId());
        itemJpas.forEach(itemJpa -> order.addItem(itemJpa.getItemId(), itemJpa.getPrice(), itemJpa.getQuantity()));
        if (orderJpa.getCouponId() != null) {
            Optional<Coupon> coupon = this.couponRepository.findById(orderJpa.getCouponId());
            coupon.ifPresent(order::addCoupon);
        }
        return Optional.of(order);
    }
}
