package com.gradprj.erp.web.tableApp;


import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.tableApp.DTO.Table;
import com.gradprj.erp.web.tableApp.DTO.TableRepository;
import com.gradprj.erp.web.tableApp.Service.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.gradprj.erp.web.tableApp.Table_function.convertTableRepositorytoJSON;



public class TableApp extends BaseApp {

    //ApplicationContext 생성
    private static final ApplicationContext TableAppConfig = new AnnotationConfigApplicationContext(TableAppConfig.class);
    //TableRepository 생성
    private static final Table_Service table_service = TableAppConfig.getBean("table_service", Table_Service.class);
    private static final TableRepository tableRepository = TableAppConfig.getBean("tableRepository", TableRepository.class);




    /**
     * 테이블 신규 생성
     * @param json
     * @return result
     * @throws SQLException
     */
    public String table_create(JSONObject json) throws SQLException {
        tableRepository.deleteAll();
        //테이블 생성 빈 가져오기
        Table_Control table_create = TableAppConfig.getBean("table_create_service", Table_Create_Service.class);

        //테이블 생성 시 필요한 정보 talbeRepository에 저장
        table_service.newTable(json);

        //테이블 생성 후 결과값 반환
        return table_create.Execute();
    }

    /**
     * 테이블 명 중복체크
     * @param json
     * @return json
     * @throws SQLException
     */
    public JSONObject table_name_dupchk(JSONObject json) throws SQLException {

        //테이블 명 중복체크 빈 가져오기
        Table_Control table_name_dupchk = TableAppConfig.getBean("table_name_dupchk_service", Table_Name_Dupchk_Service.class);

        //중복체크할 테이블 명 저장
        table_service.getTableRepository().save(new Table((String)json.get("name"),"table_info",null,null,null,null,null,(String)json.get("tablecomment")));

        //반환을 위해 json 비우기
        json.clear();

        //중복체크 후 결과값 반환
        json.put("result", table_name_dupchk.Execute());
        return json;
    }

    /**
     * 테이블 정보 가져오기
     * @param json
     * @return json
     * @throws SQLException
     */
    public JSONObject table_get_info(String name) throws SQLException {
        JSONObject json;

        //테이블 정보 가져오기 빈 가져오기
        Table_Control table_info_get = TableAppConfig.getBean("table_get_info_service", Table_Get_info_Service.class);

        //가져올 테이블 명 전달용
        table_service.getTableRepository().save(new Table(name,"table_info",null,null,null,null,null,null));

        //테이블 정보 가져오기
        table_info_get.Execute();
        json = convertTableRepositorytoJSON(tableRepository);
//        System.out.println(json);
        return json;
    }

    /**
     * 테이블 목록 가져오기
     * @param json
     * @return json
     * @throws SQLException
     */

    public JSONArray table_get_list() throws SQLException {
        JSONArray json = new JSONArray();

        //테이블 목록 가져오기 빈 가져오기
        Table_Control table_list_get = TableAppConfig.getBean("table_get_list_service", Table_Get_list_Service.class);

        //테이블 목록 가져오기
        table_list_get.Execute();

        //tableRepository에 저장된 테이블 목록을 json으로 변환
        ArrayList rows = tableRepository.getRownames();

        for(int i=0;i<rows.size();i++){
            Table row = tableRepository.findByField(rows.get(i).toString());
            HashMap<String,String> rowMap = new HashMap<>();
            rowMap.put("table_name",row.getField());
            rowMap.put("table_comment",row.getComment());
            json.add(rowMap);
        }

        return json;
    }

    public TableApp() throws SQLException {

    }

    public static void main(String[] args) throws SQLException {
    }
}
