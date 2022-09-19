package com.gradprj.web.web.table.service;

import com.gradprj.web.web.table.Table;
import com.gradprj.web.web.DBService.DB_Service;
import com.gradprj.web.web.table.TableRepository;
import com.gradprj.web.web.table.Table_Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table_ServiceImpi implements Table_Service {

    private final TableRepository tableRepository;

    @Autowired
    public Table_ServiceImpi(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public void newTable(TableRepository tableRepository, DB_Service db_service) {
        //신규테이블 생성일 시 제작

        tableRepository.save(null);
    }

    @Override
    public void oldTable(String table, DB_Service db_service) throws SQLException {
        //이미 존재하는 테이블 조작 시 호출
        ResultSet rs = db_service.DB_Ex_query("desc " + table);
        //rs -> tableRepository.save(rs);
        Long cnt=1L;
        while(rs.next()){
            Table temp = new Table(
                    cnt,
                    table,
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
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
