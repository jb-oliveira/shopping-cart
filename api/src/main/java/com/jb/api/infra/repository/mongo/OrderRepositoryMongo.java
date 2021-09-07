package com.jb.api.infra.repository.mongo;

import com.jb.api.domain.entity.Order;
import com.jb.api.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderRepositoryMongo implements OrderRepository {

    @Autowired
    OrderDocumentMongoMapper orderMapper;

    @Autowired
    OrderDocumentRepository orderDocumentRepository;
    @Autowired
    OrderItemDocumentRepository orderItemDocumentRepository;

    @Override
    public Order save(Order order) {
        OrderDocument document = orderMapper.fromOrder(order);
        OrderDocument savedDocument = orderDocumentRepository.save(document);
        String orderId = savedDocument.getId();
        order.setId(orderId);
        order.getItems().forEach(orderItem -> {
            OrderItemDocument orderItemDocument = new OrderItemDocument(null, orderId, orderItem.getItemId(), orderItem.getPrice(), orderItem.getQuantity());
            orderItemDocument = orderItemDocumentRepository.save(orderItemDocument);
            orderItem.setId(orderItemDocument.getId());
        });
        return order;
    }
}
