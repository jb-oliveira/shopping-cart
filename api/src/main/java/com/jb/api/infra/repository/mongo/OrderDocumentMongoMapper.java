package com.jb.api.infra.repository.mongo;

import com.jb.api.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDocumentMongoMapper {

    @Mapping(source = "coupon.id", target = "couponId")
    @Mapping(source = "cpf.value", target = "cpf")
    OrderDocument fromOrder(Order order);

}
