package com.jhk.Spring.web.table.service;


import com.jhk.Spring.web.DBService.DB_Service;
import com.jhk.Spring.web.table.TableRepository;
import com.jhk.Spring.web.table.Table_Control;
import org.springframework.beans.factory.annotation.Autowired;

public class Table_Drop_Service implements Table_Control {

    private final TableRepository tableRepository;

    @Autowired
    public Table_Drop_Service(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public String Execute(TableRepository table, DB_Service db) {
        System.out.println("Table_Drop_Service");
        return null;
    }
}
