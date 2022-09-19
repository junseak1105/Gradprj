package com.gradprj.web.web.table;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryTableRepository implements TableRepository {

    private static Map<Long,Table> table_data = new HashMap<>();

    @Override
    public void save(Table table) {
        table_data.put(table.getRow(),table);
    }

    @Override
    public Table findByField(String field) {
        return table_data.get(field);
    }

    @Override
    public Map findAll() {
        return table_data;
    }
}
