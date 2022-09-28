package com.gradprj.erp.web.adminApp.DTO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MemoryDatagridRepository implements DatagridRepository {
    /**
     * Datagrid Data
     * 0번째 인덱스 : Datagrid Column Name
     * 1번째 인덱스 이후: Datagrid Data
     */
    private static final ArrayList<Datagrid> datagrid_data = new ArrayList<>();


    @Override
    public void save(Datagrid datagrid) {
        datagrid_data.add(datagrid);
    }

}
