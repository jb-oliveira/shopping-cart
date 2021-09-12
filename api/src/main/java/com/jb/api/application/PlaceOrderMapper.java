package com.jb.api.application;

import com.jb.api.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaceOrderMapper {

    @Mapping(source = "orderCode.value", target = "code")
    PlaceOrderOutputDTO toOutputDto(Order order);
}
