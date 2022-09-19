package com.gradprj.erp.web.tableApp.service;


import com.gradprj.erp.web.tableApp.data.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Table_Drop_Service implements Table_Control {

    private final TableRepository tableRepository;

    @Autowired
    public Table_Drop_Service(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public String Execute() {
        System.out.println("Table_Drop_Service");
        return null;
    }
}
