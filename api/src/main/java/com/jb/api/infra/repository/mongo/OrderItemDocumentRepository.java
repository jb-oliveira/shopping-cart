package com.jb.api.infra.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderItemDocumentRepository extends MongoRepository<OrderItemDocument,String> {
}
