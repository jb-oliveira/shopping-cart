package com.jb.api.domain.repository;

import com.jb.api.domain.entity.TaxTable;

import java.util.List;

public interface TaxTableRepository {

    List<TaxTable> findByItemId(Long itemId);
}
