package com.jb.api;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository  extends MongoRepository<Item, String> {

}
