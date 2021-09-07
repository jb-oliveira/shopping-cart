package com.jb.api.domain.repository;

public interface SequenceGenerator {

    String ORDER_ANUAL_SEQUENCE = "ORDER_ANUAL_SEQUENCE";

    Long generateId(String idName);

}
