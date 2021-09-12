package com.jb.api.infra.jpa.repository;

import com.jb.api.domain.entity.Item;
import com.jb.api.domain.repository.ItemRepository;
import com.jb.api.infra.jpa.entity.ItemJpa;
import com.jb.api.infra.jpa.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class ItemRepositoryJpa implements ItemRepository {


    @Autowired
    private ItemRepositoryTemplate repository;

    @Autowired
    private ItemMapper mapper;

    @Override
    public Optional<Item> findById(Long id) {
        ItemJpa itemJpa = repository.findById(id).orElse(null);
        return Optional.ofNullable(mapper.fromJpa(itemJpa));
    }

    @Override
    public Item save(Item item) {
        ItemJpa jpa = mapper.toJpa(item);
        jpa  = repository.save(jpa);
        return mapper.fromJpa(jpa);
    }
}
