package com.jb.api.infra.jpa.mapper;

import com.jb.api.domain.entity.TaxTable;
import com.jb.api.infra.jpa.entity.TaxTableJpa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaxTableMapper {

    TaxTable map(TaxTableJpa jpa);

    List<TaxTable> map(List<TaxTableJpa> tables);

}
