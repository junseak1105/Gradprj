package com.gradprj.erp.service;

import com.gradprj.erp.domain.Table.Table;
import com.gradprj.erp.domain.Table.TableRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.gradprj.erp.common.CommonUtil.IsNull;

@Component
public class TableServiceImpl implements TableService {

    @Autowired
    TableRepository tableRepository;

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
        System.out.println(json);
        String name = IsNull(json.get("name").toString());
        String field = IsNull(json.get("field_" + i).toString());
        String type = IsNull(json.get("type_" + i).toString());
        //varchar or char 일때만 길이 받음
        if (type.equals("varchar") || type.equals("char")) {
            type = type + "(" + json.get("length_" + i).toString() + ")";
        }else{
            type = type;
        }
        String nullable = IsNull(json.get("null_" + i).toString());
        String key = "";//IsNull(json.get("key").toString());

        String default_val = IsNull(json.get("default_" + i).toString());
        String extra = IsNull(json.get("extra_" + i).toString());
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

    public void newTable(JSONObject json){
        //테이블 컬럼 갯수 구하기
        int colnumcnt = Integer.parseInt((String) json.get("colnumcnt"));
        //테이블 정보 저장
        tableRepository.save(new Table((String)json.get("name"),"table_info",null,null,null,null,null,(String)json.get("tablecomment")));
        //테이블 컬럼 저장
        for (int i = 1; i <= colnumcnt; i++) {
            tableRepository.save(convertJSONtoTable(json, i));
        }
//        System.out.println(tableRepository.findAll().toString());
    }

    public void oldTable(String table) throws SQLException {
        //이미 존재하는 테이블 조작 시 호출
        ResultSet rs = DBService.DB_Ex_query("desc " + table);
        Long cnt = 1L;
        while (rs.next()) {
            Table temp = new Table(
                    table,
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
            );
            tableRepository.save(temp);
            cnt++;
        }
    }

    @Override
    public void createTable(JSONObject json) {
        System.out.println("createTable");
        tableRepository.deleteAll();
        newTable(json);
        String query = "create table " + tableRepository.getTableName() + "(\n" +
                "idx int not null auto_increment primary key,\n";

        //row명 fetch
        ArrayList<String> rows = tableRepository.getRownames();

        /**
         * query문 생성
         * 0번째줄은 테이블 정보이므로 제외
         */
        for (int i = 1; i < rows.size(); i++) {
            Table row = tableRepository.findByField(rows.get(i));
            query += row.getField()
                    + " " + row.getType()
                    + " " + row.getNull();

            if (row.getType().equals("int") || row.getType().equals("double")) {
                if (IsNull(row.getDefault()).equals(""))
                    query += " default 0";
                else
                    query += " default " + row.getDefault();
            } else {
                query += " default '" + row.getDefault() + "'";
            }

            query += " " + row.getExtra();

            query += " comment '" + row.getComment()+"'";

            if (i != rows.size() - 1) query += ",\n";
            else query += "\n";
        }


        /**
         * 키 제작 추가 필요
         */
//        String key = tableRepository.findByField(rows.get(1)).getKey();
//        if (!key.equals("none")) {
        query += ",sort_lv1 varchar(20) not null comment '대분류'\n";
        query += ",sort_lv2 varchar(20) not null comment '중분류'\n";
        query += ",sort_code varchar(20) not null comment '분류코드'\n";
        query += ",foreign key(sort_lv1) references sort_lv1(sort_lv1)\n";
        query += ",foreign key(sort_lv2) references sort_lv2(sort_lv2)\n";
        query += ",foreign key(sort_code) references sort_code(sort_code)\n";
//        }


        query += ")comment '" + tableRepository.getTableComment() + "'";

        System.out.println(query);

        DBService.DB_Ex_query_nr(query);
    }
    @Override
    public void dropTable() {
        System.out.println("dropTable");
    }
    @Override
    public String getTableList() throws SQLException {
        System.out.println("getTableList");
        JSONArray json = new JSONArray();

        //테이블 목록 가져오기
        String[] extable = {"sort_code","sort_lv1","sort_lv2","page_category","page_list"};
        String query ="select * from information_schema.tables where table_schema = 'gradprj'";
        for (String s : extable) {
            query += " and table_name != '" + s + "'";
        }

        ResultSet rs = DBService.DB_Ex_query(query);

        while (rs.next()) {
//            System.out.println("table name : " + rs.getString("TABLE_NAME"));
            Table table = new Table(
                    rs.getString("TABLE_NAME"),
                    rs.getString("TABLE_NAME"),
                    null,
                    null,
                    null,
                    null,
                    null,
                    rs.getString("TABLE_COMMENT")
            );
            tableRepository.save(table);
        }

        //tableRepository에 저장된 테이블 목록을 json으로 변환
        ArrayList rows = tableRepository.getRownames();

        for(int i=0;i<rows.size();i++){
            Table row = tableRepository.findByField(rows.get(i).toString());
            HashMap<String,String> rowMap = new HashMap<>();
            rowMap.put("table_name",row.getField());
            rowMap.put("table_comment",row.getComment());
            json.add(rowMap);
        }

        return json.toJSONString();
    }
    @Override
    public String getTableInfo() throws SQLException {
        System.out.println("getTableInfo");
        JSONObject json;

        //테이블 정보 가져오기 빈 가져오기
        String name = tableRepository.getTableName();
        //가져올 테이블 명 전달용
        tableRepository.save(new Table(name,"table_info",null,null,null,null,null,null));

        //테이블 정보 가져오기
        ResultSet rs = DBService.DB_Ex_query("select * from information_schema.columns where TABLE_NAME='" + name + "'");
        while (rs.next()) {
            Table table = new Table(
                    name,
                    rs.getString("COLUMN_NAME"),
                    rs.getString("COLUMN_TYPE"),
                    rs.getString("IS_NULLABLE"),
                    rs.getString("COLUMN_KEY"),
                    rs.getString("COLUMN_DEFAULT"),
                    rs.getString("EXTRA"),
                    rs.getString("COLUMN_COMMENT")
            );
            tableRepository.save(table);
        }

        json = convertTableRepositorytoJSON(tableRepository);
//        System.out.println(json);
        return json.toJSONString();
    }

    @Override
    public void getTableColumns() {
        System.out.println("getTableColumns");
    }
    @Override
    public String nameDupchk(JSONObject json) throws SQLException {

        //중복체크할 테이블 명 저장
        tableRepository.save(new Table((String)json.get("name"),"table_info",null,null,null,null,null,(String)json.get("tablecomment")));

        //반환을 위해 json 비우기
        json.clear();

        String name = tableRepository.getTableName();
        System.out.println(name);
        ResultSet rs = DBService.DB_Ex_query("select count(*) cnt from information_schema.tables where TABLE_NAME='" + name + "'");
        while (rs.next()) {
            String result = rs.getString("cnt");
            if (result.equals("0")) {
                json.put("result", true);
            } else {
                json.put("result", false);
            }
        }

        return json.toJSONString();
    }
}
