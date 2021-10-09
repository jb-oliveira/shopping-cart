package com.jb.api.infra.jpa.repository;

import com.jb.api.infra.jpa.entity.TaxTableJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxTableRepositoryJpa extends JpaRepository<TaxTableJpa, Long> {

    List<TaxTableJpa> findByItemId(Long itemId);
}
