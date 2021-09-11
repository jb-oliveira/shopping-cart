package com.jb.api.infra.jpa.repository;

import com.jb.api.domain.repository.SequenceGenerator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Repository
public class SequenceGeneratorJpa implements SequenceGenerator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long generateId(String idName) {
        String query = "select nextval('" + idName + "')";
        BigInteger singleResult = (BigInteger) entityManager
                .createNativeQuery(query)
                .getSingleResult();
        return singleResult.longValueExact();
    }
}
