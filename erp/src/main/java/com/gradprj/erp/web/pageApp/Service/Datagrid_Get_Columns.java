package com.gradprj.erp.web.pageApp.Service;

import com.gradprj.erp.web.pageApp.DTO.MemoryPageDataRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gradprj.erp.BaseApp.db_service;

public class Datagrid_Get_Columns implements Page_Service {
    private final MemoryPageDataRepository memoryPageDataRepository;
    @Autowired
    public Datagrid_Get_Columns(MemoryPageDataRepository memoryPageDataRepository) {
        this.memoryPageDataRepository = memoryPageDataRepository;
    }

    @Override
    public String Execute(String table_name) throws SQLException {
        JSONArray result = new JSONArray();
        String query = "select column_name,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH,COLUMN_KEY,COLUMN_COMMENT from information_schema.columns\n" +
                "where TABLE_NAME = '"+table_name+"';";
        System.out.println(query);
        ResultSet rs = db_service.DB_Ex_query(query);
        while(rs.next()){
            JSONObject col = new JSONObject();
            col.put("column_name",rs.getString("column_name"));
            col.put("DATA_TYPE",rs.getString("DATA_TYPE"));
            col.put("CHARACTER_MAXIMUM_LENGTH",rs.getString("CHARACTER_MAXIMUM_LENGTH"));
            col.put("COLUMN_KEY",rs.getString("COLUMN_KEY"));
            col.put("COLUMN_COMMENT",rs.getString("COLUMN_COMMENT"));
            result.add(col);
        }

        memoryPageDataRepository.save("columns",result);

        return null;
    }
}

