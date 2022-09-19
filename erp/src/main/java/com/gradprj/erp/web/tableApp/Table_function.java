package com.gradprj.erp.web.tableApp;

import com.gradprj.erp.web.tableApp.data.Table;
import com.gradprj.erp.web.tableApp.data.TableRepository;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.gradprj.erp.common.CommonUtil.IsNull;

public class Table_function {

    /**
     * TableRepository를 JSON으로 변환
     * @param tableRepository
     * @return json
     */
    public static JSONObject convertTableRepositorytoJSON(TableRepository tableRepository){
        JSONObject json = new JSONObject();
        ArrayList rows = tableRepository.getRownames();

        for(int i=0;i<rows.size();i++){
            Table row = tableRepository.findByField(rows.get(i).toString());
            HashMap<String,String> rowMap = new HashMap<>();
            rowMap.put("field",row.getField());
            rowMap.put("type",row.getType());
            rowMap.put("null",row.getNull());
            rowMap.put("default",row.getDefault());
            rowMap.put("extra",row.getExtra());
            rowMap.put("comment",row.getComment());
            json.put(row.getField(),rowMap);
        }
        return json;
    }

    /**
     * JSON 데이터를 받아서 Table 객체로 변환
     * @param json
     * @param i(컬럼 번호)
     * @return Table
     * extra 미구현
     */
    public static Table convertJSONtoTable(JSONObject json,int i){
        String name = IsNull(json.get("name").toString());
        String field = IsNull(json.get("field_" + i).toString());
        String type = IsNull(json.get("type_" + i).toString());
        //varchar or char 일때만 길이 받음
        if (type.equals("varchar") || type.equals("char")) {
            type = type + "(" + json.get("length_" + i).toString() + ")";
        }
        String nullable = IsNull(json.get("null_" + i).toString());
        String key = IsNull(json.get("key_" + i).toString());
        //키의 대괄호 제거
        if (key.length() > 0) {
            key = key.substring(1, key.length() - 1);
        }
        String default_val = IsNull(json.get("default_" + i).toString());
        String extra = "";//IsNull(json.get("extra_" + i).toString());
        String comment = IsNull(json.get("comment_" + i).toString());
        Table table = new Table(
                name,
                field,
                type,
                nullable,
                key,
                default_val,
                extra,
                comment
        );
        return table;
    }

    public Table_function() {
    }
}
