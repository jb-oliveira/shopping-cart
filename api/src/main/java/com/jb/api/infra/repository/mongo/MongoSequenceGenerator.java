package com.jb.api.infra.repository.mongo;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.jb.api.domain.repository.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


@Service
public class MongoSequenceGenerator implements SequenceGenerator {

    @Autowired
    private MongoOperations mongo;

    @Override
    @SuppressWarnings("ConstantConditions")
    public Long generateId(String idName) {
        MongoSequence counter = mongo.findAndModify(
                query(where("_id").is(idName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                MongoSequence.class);
        return  counter.getSeq();
    }
}
