package com.jb.api.infra.repository.jpa;

import com.jb.api.domain.entity.Order;
import com.jb.api.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderRepositoryJpa implements OrderRepository {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderEntityRepository orderEntityRepository;
    @Autowired
    OrderItemEntityRepository orderItemEntityRepository;

    @Override
    public Order save(Order order) {
        OrderEntity document = orderMapper.fromOrder(order);
        OrderEntity savedDocument = orderEntityRepository.save(document);
        Long orderId = savedDocument.getId();
        order.setId(orderId);
        order.getItems().forEach(orderItem -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity(null, orderId, orderItem.getItemId(), orderItem.getPrice(), orderItem.getQuantity());
            orderItemEntity = orderItemEntityRepository.save(orderItemEntity);
            orderItem.setId(orderItemEntity.getId());
        });
        return order;
    }
}
