package com.gradprj.erp.web.adminApp.DAO;


import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.adminApp.DTO.Datagrid;
import com.gradprj.erp.web.adminApp.DTO.DatagridRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;



public class Page_Get_Datagrid_Service extends BaseApp implements Page_Datagrid {

    private final DatagridRepository datagridRepository;

    @Autowired
    public Page_Get_Datagrid_Service(DatagridRepository datagridRepository) {
        this.datagridRepository = datagridRepository;
    }

    @Override
    public String GetDatagrid(String viewname) throws SQLException {

        ResultSet rs = db_service.DB_Ex_query("select " +
                "(SELECT COUNT(*)\n" +
                "FROM information_schema.columns\n" +
                "WHERE table_name='datatable_list') as colcnt, " +
                viewname + ".*" +
                "* from " + viewname);

        int colcnt = Integer.parseInt(rs.getString("colcnt")) + 1;

        while (rs.next()) {
            Datagrid datagrid = new Datagrid();
            for (int i = 2; i <= colcnt; i++) {
                datagrid.add(rs.getString(i));
            }
            datagridRepository.save(datagrid);
        }
        return null;
    }
}
