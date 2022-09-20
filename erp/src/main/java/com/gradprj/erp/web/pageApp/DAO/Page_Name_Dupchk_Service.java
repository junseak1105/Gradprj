package com.gradprj.erp.web.pageApp.DAO;

import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.pageApp.DTO.PageRepository;
import com.gradprj.erp.web.tableApp.DAO.Table_Control;
import com.gradprj.erp.web.tableApp.DTO.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Page_Name_Dupchk_Service extends BaseApp implements Page_Service {

    private final PageRepository pageRepository;

    @Autowired
    public Page_Name_Dupchk_Service(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public String Execute() throws SQLException {
        String name = "";//tableRepository.getTableName();
        ResultSet rs = db_service.DB_Ex_query("select count(*) cnt from information_schema.tables where TABLE_NAME='" + name + "'");
        while (rs.next()) {
            String result = rs.getString("cnt");
            if (result.equals("0")) {
                return "true";//중복되지 않음
            } else {
                return "false";//중복됨
            }
        }

        return "true";
    }
}

