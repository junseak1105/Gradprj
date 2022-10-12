package com.gradprj.erp.web.pageApp.Service;

import com.gradprj.erp.BaseApp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;

import static com.gradprj.erp.BaseApp.db_service;

public class Datagrid_Delete_Data implements Page_Service {

    @Override
    public String Execute(String json) throws SQLException, Exception {
        String query = "";
        JSONObject data = (JSONObject) org.json.simple.JSONValue.parse(json);
        JSONObject result=new JSONObject();
        HashMap<String, String> formdata = new HashMap();
        JSONArray formarray = (JSONArray) data.get("formdata");
        for (int i = 0; i < formarray.size(); i++) {
            JSONObject json_temp = (JSONObject) formarray.get(i);
            formdata.put((String) json_temp.get("name"), (String) json_temp.get("value"));

        }
        query="delete from "+data.get("table_name")+" where idx='"+formdata.get("idx")+"'";


        if(db_service.DB_Ex_query_nr(query)){
            result.put("result","success");
            return result.toJSONString();
        }else{
            result.put("result","fail");
            return result.toJSONString();
        }
    }
}

