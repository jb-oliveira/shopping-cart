package com.jb.api.domain.repository;

import com.jb.api.domain.entity.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {

}
