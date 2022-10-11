package com.gradprj.erp.web.pageApp.Service;

import com.gradprj.erp.web.dbApp.DB_Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.gradprj.erp.BaseApp.db_service;

public class Datagrid_Save_Data implements Page_Service {
    @Override
    public String Execute(String json) throws Exception {
        JSONObject result = new JSONObject();
        JSONObject data = (JSONObject) org.json.simple.JSONValue.parse(json);
//        System.out.println(data.get("formdata").getClass());
//        System.out.println(data.get("columns"));

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


        String query = "insert into " + data.get("table_name");
        String field = "(";
        String value = " values(";
        for (String col : column_names) {
            field += col + ",";
            value += "'"+formdata.get(col) + "',";
        }
        field = field.substring(0, field.length() - 1) + ")";
        value = value.substring(0, value.length() - 1) + ")";
        query = query + field + value;
        System.out.println(query);
        if(db_service.DB_Ex_query_nr(query)){
            result.put("result","success");
            return result.toJSONString();
        }else{
            result.put("result","fail");
            return result.toJSONString();
        }
    }

}
