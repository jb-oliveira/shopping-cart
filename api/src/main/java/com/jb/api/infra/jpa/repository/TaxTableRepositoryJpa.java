package com.jb.api.infra.jpa.repository;

import com.jb.api.domain.entity.TaxTable;
import com.jb.api.domain.repository.TaxTableRepository;
import com.jb.api.infra.jpa.entity.TaxTableJpa;
import com.jb.api.infra.jpa.mapper.TaxTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaxTableRepositoryJpa implements TaxTableRepository {

    @Autowired
    private TaxTableRepositoryTemplate template;

    @Autowired
    private TaxTableMapper mapper;

    @Override
    public List<TaxTable> findByItemId(Long itemId) {
        List<TaxTableJpa> list = template.findByItemId(itemId);
        return mapper.map(list);
    }
}
