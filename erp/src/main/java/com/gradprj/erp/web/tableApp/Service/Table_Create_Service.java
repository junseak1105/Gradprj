package com.gradprj.erp.web.tableApp.Service;


import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.tableApp.DTO.Table;
import com.gradprj.erp.web.tableApp.DTO.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static com.gradprj.erp.common.CommonUtil.IsNull;

public class Table_Create_Service extends BaseApp implements Table_Control {

    private final TableRepository tableRepository;

    @Autowired
    public Table_Create_Service(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public String Execute() {
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

        db_service.DB_Ex_query_nr(query);
        return null;
    }
}
