package com.jb.api.application.getOrder;

import com.jb.api.application.exception.InvalidOrderException;
import com.jb.api.domain.entity.Item;
import com.jb.api.domain.entity.Order;
import com.jb.api.domain.entity.OrderItem;
import com.jb.api.domain.exception.BaseException;
import com.jb.api.domain.exception.InvalidItemException;
import com.jb.api.domain.repository.CouponRepository;
import com.jb.api.domain.repository.ItemRepository;
import com.jb.api.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GetOrder {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private OrderRepository orderRepository;


    public GetOrderOutputDTO execute(String code) throws BaseException {
        Order order = this.orderRepository
                .findByCode(code)
                .orElseThrow(() -> new InvalidOrderException(code));
        GetOrderOutputDTO outputDTO = new GetOrderOutputDTO(order.getOrderCode().getValue(), order.getFreight(), order.getTotal(), new ArrayList<>(), order.getTaxes());
        for (OrderItem orderItem : order.getItems()) {
            Item item = this.itemRepository
                    .findById(orderItem.getItemId())
                    .orElseThrow(() -> new InvalidItemException("Item not found " + orderItem.getItemId()));
            outputDTO.items.add(new GetOrderItemOtuputDTO(item.getDescription(), orderItem.getPrice(), orderItem.getQuantity()));
        }
        return outputDTO;
    }
}
