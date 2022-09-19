package com.gradprj.web.web.table.service;


import com.gradprj.web.web.DBService.DB_Service;
import com.gradprj.web.web.table.TableRepository;
import com.gradprj.web.web.table.Table_Control;
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
