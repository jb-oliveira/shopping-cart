package com.jb.api.infra.repository.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "mongoSequences")
public class MongoSequence {
    @Id
    private String id;
    private Long seq;
}
