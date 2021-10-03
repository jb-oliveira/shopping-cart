package com.jb.api.application.placeOrder;

import com.jb.api.domain.entity.Order;
import com.jb.api.domain.exception.BaseException;
import com.jb.api.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaceOrder {


    @Autowired
    private PlaceOrderMapper mapper;

    @Autowired
    private OrderService service;


    public PlaceOrderOutputDTO execute(PlaceOrderImputDTO input) throws BaseException {
        Order order = this.service.create(input);
        return this.mapper.toOutputDto(order);
    }
}
