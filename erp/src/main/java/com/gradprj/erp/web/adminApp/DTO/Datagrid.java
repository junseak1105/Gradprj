package com.gradprj.erp.web.adminApp.DTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Datagrid {
    private final ArrayList datagrid_data = new ArrayList(); // Datagrid Data

    public void add(String str) {
        datagrid_data.add(str);
    }
}
