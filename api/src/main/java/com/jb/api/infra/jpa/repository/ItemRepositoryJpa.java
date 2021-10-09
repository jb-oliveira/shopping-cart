package com.jb.api.infra.jpa.repository;

import com.jb.api.infra.jpa.entity.ItemJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositoryJpa extends JpaRepository<ItemJpa, Long> {

}
