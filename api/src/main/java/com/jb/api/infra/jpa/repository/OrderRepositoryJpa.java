package com.jb.api.infra.jpa.repository;

import com.jb.api.domain.entity.Order;
import com.jb.api.domain.repository.OrderRepository;
import com.jb.api.infra.jpa.entity.OrderItemJpa;
import com.jb.api.infra.jpa.entity.OrderJpa;
import com.jb.api.infra.jpa.mapper.OrderMapperJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class OrderRepositoryJpa implements OrderRepository {

    @Autowired
    private OrderMapperJpa orderMapperJpa;

    @Autowired
    private OrderRepositoryTemplate orderTemplate;

    @Autowired
    private OrderItemRepositoryTemplate orderItemTemplate;

    @Override
    public Order save(Order order) {
        OrderJpa document = orderMapperJpa.fromOrder(order);
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
}
