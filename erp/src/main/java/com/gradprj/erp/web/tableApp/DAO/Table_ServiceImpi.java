package com.gradprj.erp.web.tableApp.DAO;

import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.tableApp.DTO.Table;
import com.gradprj.erp.web.tableApp.DTO.TableRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.gradprj.erp.web.tableApp.Table_function.convertJSONtoTable;

public class Table_ServiceImpi extends BaseApp implements Table_Service {

    private final TableRepository tableRepository;

    @Autowired
    public Table_ServiceImpi(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    /**
     * 테이블 신규 등록 시 repository에 저장
     *
     * @param json
     * @param db
     * @return void
     */
    @Override
    public void newTable(JSONObject json) {

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

    @Override
    public void oldTable(String table) throws SQLException {
        //이미 존재하는 테이블 조작 시 호출
        ResultSet rs = db_service.DB_Ex_query("desc " + table);
        //rs -> tableRepository.save(rs);
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
    public TableRepository getTableRepository() {
        return tableRepository;
    }
}
