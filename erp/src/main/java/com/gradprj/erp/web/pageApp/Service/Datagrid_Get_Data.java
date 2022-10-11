package com.gradprj.erp.web.pageApp.Service;

import com.gradprj.erp.web.pageApp.DTO.MemoryPageDataRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gradprj.erp.BaseApp.db_service;

public class Datagrid_Get_Data implements Page_Service {
    private final MemoryPageDataRepository memoryPageDataRepository;
    @Autowired
    public Datagrid_Get_Data(MemoryPageDataRepository memoryPageDataRepository) {
        this.memoryPageDataRepository = memoryPageDataRepository;
    }

    @Override
    public String Execute(String table_name) throws SQLException {
        JSONArray result = new JSONArray();
        String query = "select * from "+table_name+";";
        ResultSet rs = db_service.DB_Ex_query(query);

        JSONArray columns = (JSONArray) memoryPageDataRepository.findAll().get("columns");
        while(rs.next()){
            JSONObject col = new JSONObject();
            for(int i=0;i<columns.size();i++){
                JSONObject column = (JSONObject) columns.get(i);
                String column_name = (String) column.get("column_name");
                String DATA_TYPE = (String) column.get("DATA_TYPE");
                String CHARACTER_MAXIMUM_LENGTH = (String) column.get("CHARACTER_MAXIMUM_LENGTH");
                String COLUMN_KEY = (String) column.get("COLUMN_KEY");
                if(DATA_TYPE.equals("int")){
                    col.put(column_name,rs.getInt(column_name));
                }else if(DATA_TYPE.equals("varchar")){
                    col.put(column_name,rs.getString(column_name));
                }else if(DATA_TYPE.equals("datetime")){
                    col.put(column_name,rs.getString(column_name));
                }
            }
            result.add(col);
        }
        memoryPageDataRepository.save("data",result);
        return null;
    }
}

