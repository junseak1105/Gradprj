package com.jhk.Spring.web.table;

import java.util.Map;

public interface TableRepository {

    void save(Table table);
    Table findByField(String field);
    Map findAll();
}
