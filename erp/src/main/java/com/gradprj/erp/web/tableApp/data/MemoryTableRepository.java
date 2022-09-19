package com.gradprj.erp.web.tableApp.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryTableRepository implements TableRepository {

    private static Map<String, Table> table_data = new HashMap<>();

    @Override
    public void save(Table table) {
        table_data.put(table.getField(), table);
    }

    @Override
    public Table findByField(String field) {
        return table_data.get(field);
    }

    @Override
    public Map findAll() {
        return table_data;
    }

    @Override
    public String getTableName() {
        return (table_data.get("table_info").getName());
//        return (table_data.get((String)(((HashMap)table_data).keySet().toArray())[0])).getName();
    }
    @Override
    public String getTableComment() {
        return (table_data.get("table_info").getComment());
    }

    @Override
    public ArrayList<String> getRownames() {
        ArrayList<String> keys = new ArrayList<>();
        for (int i = 0; i < table_data.size(); i++) {
            keys.add((String)(((HashMap)table_data).keySet().toArray())[i]);
        }
        return keys;
    }

}
