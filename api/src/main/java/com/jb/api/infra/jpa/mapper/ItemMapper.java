package com.jb.api.infra.jpa.mapper;

import com.jb.api.domain.entity.Item;
import com.jb.api.infra.jpa.entity.ItemJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item fromJpa(ItemJpa jpa);

    ItemJpa toJpa(Item item);
}
