package com.gradprj.erp.service;

import com.gradprj.erp.domain.DataGrid.PageDataRepository;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Log4j2
public class DatagridServiceImpl implements DatagridService {

    @Autowired
    private PageDataRepository pageDataRepository;

    @Override
    public String getTableName(String pagename) throws SQLException {
        String sql = "select page_table from page_list\n" +
                "left join page_category pc on pc.page_category = page_list.page_category\n" +
                "where page_name='"+pagename+"';";
        ResultSet rs = DBService.DB_Ex_query(sql);
        rs.next();
        return rs.getString("page_table");
    }

    @Override
    public String deleteData(JSONObject data) throws SQLException {
        log.info("deleteData");
        data.put("table_name", getTableName(data.get("pagename").toString()));
        String query = "";
        JSONObject result=new JSONObject();
        HashMap<String, String> formdata = new HashMap();
        JSONArray formarray = (JSONArray) data.get("formdata");
        for (int i = 0; i < formarray.size(); i++) {
            JSONObject json_temp = (JSONObject) formarray.get(i);
            formdata.put((String) json_temp.get("name"), (String) json_temp.get("value"));

        }
        query="delete from "+data.get("table_name")+" where idx='"+formdata.get("idx")+"'";


        if(DBService.DB_Ex_query_nr(query)){
            result.put("result","success");
            return result.toJSONString();
        }else{
            result.put("result","fail");
            return result.toJSONString();
        }
    }
    @Override
    public void getColumns(String table_name) throws SQLException {
        log.info("getColumns");
        JSONArray result = new JSONArray();
        String query = "select column_name,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH,COLUMN_KEY,COLUMN_COMMENT from information_schema.columns\n" +
                "where TABLE_NAME = '"+table_name+"';";
        System.out.println(query);
        ResultSet rs = DBService.DB_Ex_query(query);
        while(rs.next()){
            JSONObject col = new JSONObject();
            col.put("column_name",rs.getString("column_name"));
            col.put("DATA_TYPE",rs.getString("DATA_TYPE"));
            col.put("CHARACTER_MAXIMUM_LENGTH",rs.getString("CHARACTER_MAXIMUM_LENGTH"));
            col.put("COLUMN_KEY",rs.getString("COLUMN_KEY"));
            col.put("COLUMN_COMMENT",rs.getString("COLUMN_COMMENT"));
            result.add(col);
        }
        pageDataRepository.save("columns",result);
    }
    @Override
    public String getData(String pagename) throws SQLException {
        log.info("getData");
        String table_name = getTableName(pagename);

        log.info("table_name 가져옴: "+table_name);
        //데이터그리드 컬럼명용 page.put
        getColumns(table_name);
        log.info("datagrid_get_columns 실행완료");
        JSONArray result = new JSONArray();
        String query = "select * from "+table_name+";";
        ResultSet rs = DBService.DB_Ex_query(query);

        JSONArray columns = (JSONArray) pageDataRepository.findAll().get("columns");
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
        pageDataRepository.save("data",result);
        log.info("datagrid_get_data 실행완료");

        return pageDataRepository.findAll().toJSONString();
    }
    @Override
    public String saveData(JSONObject data) throws SQLException {
        log.info("saveData");
        data.put("table_name", getTableName(data.get("pagename").toString()));
        JSONObject result = new JSONObject();
        String query ="";

        List<String> column_names = new ArrayList();
        JSONArray columns = (JSONArray) data.get("columns");
        for (int i = 1; i < columns.size(); i++) {
            JSONObject json_temp = (JSONObject) columns.get(i);
            column_names.add((String) json_temp.get("column_name"));
        }


        HashMap<String, String> formdata = new HashMap();
        JSONArray formarray = (JSONArray) data.get("formdata");
        for (int i = 0; i < formarray.size(); i++) {
            JSONObject json_temp = (JSONObject) formarray.get(i);
            formdata.put((String) json_temp.get("name"), (String) json_temp.get("value"));

        }

        switch (formdata.get("fr_data_status")) {
            case "update":
                query = "update " + data.get("table_name") + " set ";
                for (int i = 0; i < column_names.size(); i++) {
                    query += column_names.get(i) + "='" + formdata.get(column_names.get(i)) + "'";
                    if (i != column_names.size() - 1) {
                        query += ",";
                    }
                }
                query += " where idx='" + formdata.get("idx") + "'";
                break;
            case "new":
                query = "insert into " + data.get("table_name");
                String field = "(";
                String value = " values(";
                for (String col : column_names) {
                    field += col + ",";
                    value += "'"+formdata.get(col) + "',";
                }
                field = field.substring(0, field.length() - 1) + ")";
                value = value.substring(0, value.length() - 1) + ")";
                query = query + field + value;
                break;
        }

        System.out.println(query);
        if(DBService.DB_Ex_query_nr(query)){
            result.put("result","success");
            return result.toJSONString();
        }else{
            result.put("result","fail");
            return result.toJSONString();
        }
    }
}
