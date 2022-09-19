package com.gradprj.erp.web.tableApp.service;


import com.gradprj.erp.web.tableApp.data.Table;
import com.gradprj.erp.web.tableApp.data.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static com.gradprj.erp.BaseApp.db_service;
import static com.gradprj.erp.common.CommonUtil.IsNull;

public class Table_Create_Service implements Table_Control {

    private final TableRepository tableRepository;

    @Autowired
    public Table_Create_Service(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public String Execute() {
        String query = "create table " + tableRepository.getTableName() + "(\n";

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

            if (i != rows.size() - 1) query += ",\n";
            else query += "\n";
        }


        /**
         * 키 제작 추가 필요
         */
//        query += "primary key(" + tableRepository.getPrimaryKey() + ")\n";
        query += ")comment '" + tableRepository.getTableComment() + "'";

//        System.out.println(query);

        db_service.DB_Ex_query_nr(query);
        return null;
    }
}
