package com.jb.api.infra.repository.jpa;

import com.jb.api.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "coupon.id", target = "couponId")
    @Mapping(source = "cpf.value", target = "cpf")
    OrderEntity fromOrder(Order order);

}
