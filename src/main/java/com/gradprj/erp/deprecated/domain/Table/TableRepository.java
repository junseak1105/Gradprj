package com.gradprj.erp.deprecated.domain.Table;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
public interface TableRepository {

    void save(Table table);
    Table findByField(String field);
    Map findAll();

    String getTableName();

    String getTableComment();

    ArrayList<String> getRownames();

    void deleteAll();
}
