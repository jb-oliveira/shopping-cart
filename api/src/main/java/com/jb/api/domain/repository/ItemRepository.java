package com.jb.api.domain.repository;

import com.jb.api.domain.entity.Item;

import java.util.Optional;

public interface ItemRepository  {

    Optional<Item> findById(Long id);

    Item save(Item guitarra);
}
