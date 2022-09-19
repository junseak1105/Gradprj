package com.jhk.Spring;

import com.jhk.Spring.web.table.*;
import com.jhk.Spring.web.table.service.Table_Create_Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.Map;

public class TableApp extends BaseApp {

    private static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TableAppConfig.class);
    //TableRepository 생성
    private static Table_Service table_service = applicationContext.getBean("table_service", Table_Service.class);
    private static TableRepository tableRepository = applicationContext.getBean("tableRepository", TableRepository.class);


    public String table_create(TableRepository tableRepository) throws SQLException {
        //테이블 생성 시
        table_service.newTable(tableRepository, db_service);
        //테이블 생성
        Table_Control table_create = applicationContext.getBean("table_create_service", Table_Create_Service.class);
        table_create.Execute(table_service.getTableRepository(), db_service);
        return null;
    }
    public TableApp() throws SQLException {

    }

    public static void main(String[] args) throws SQLException {

        /*
        테이블 생성 혹은 수정, 삭제 여부에 따른 fetch

        - row에 따른 출력법
        Map temp = tableRepository.findAll();
        for(int i=1;i<=temp.size();i++){
            System.out.println(temp.get((long)i));
        }
         */


        //테이블 수정&삭제 시
        String tablename = "datatable_list";
        table_service.oldTable(tablename,db_service);


        /*
        기능 생성
        applicationContext에서 받아옴
         */

        //테이블 삭제
//        Table_Control table_drop = applicationContext.getBean("table_drop_service", Table_Create_Service.class);
        //테이블 수정
//        Table_Control table_alter = applicationContext.getBean("table_alter_service", Table_Alter_Service.class);

        /*
        기능 실행(마이그레이션 중)
         */
//        table_create.Execute(table_service.getTableRepository(), db_service);
//        table_drop.Execute(table_service.getTableRepository(), db_service);
    }
}
