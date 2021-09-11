package com.jb.api.infra.jpa.mapper;

import com.jb.api.domain.entity.Order;
import com.jb.api.infra.jpa.entity.OrderJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapperJpa {

    @Mapping(source = "coupon.code", target = "couponId")
    @Mapping(source = "cpf.value", target = "cpf")
    OrderJpa fromOrder(Order order);

}
