package com.jb.api.infra.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDocumentRepository extends MongoRepository<OrderDocument, String> {

}
