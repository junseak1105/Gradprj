package com.gradprj.erp.web.pageApp;


import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.pageApp.Service.*;
import com.gradprj.erp.web.pageApp.DTO.MemoryPageDataRepository;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

@Log4j2
public class PageApp extends BaseApp {
    //빈 가져오기용
    private static final ApplicationContext PageAppConfig = new AnnotationConfigApplicationContext(PageAppConfig.class);

    //Repository
    private static final MemoryPageDataRepository memoryPageDataRepository = PageAppConfig.getBean("memoryPageDataRepository", MemoryPageDataRepository.class);


    public String NavTree() {
        JSONObject navbar = new JSONObject();

        return navbar.toJSONString();
    }

    public String GetPage(String option,String pagename) throws SQLException {
        log.info("GetPage");
        Datagrid_Get_Columns datagrid_get_columns = PageAppConfig.getBean(Datagrid_Get_Columns.class);
        Datagrid_Get_Data datagrid_get_data = PageAppConfig.getBean(Datagrid_Get_Data.class);
        Page_Get_Tablename page_get_tablename = PageAppConfig.getBean(Page_Get_Tablename.class);

        //테이블 명 얻기
        String table_name = page_get_tablename.Execute(pagename);
        log.info("table_name 가져옴: "+table_name);
        //데이터그리드 컬럼명용 page.put
        datagrid_get_columns.Execute(table_name);
        log.info("datagrid_get_columns 실행완료");
        datagrid_get_data.Execute(table_name);
        log.info("datagrid_get_data 실행완료");

        switch (option){
            case "datagrid":
                return memoryPageDataRepository.findAll().get("data").toString();
            default:
                return memoryPageDataRepository.findAll().toJSONString();
        }
    }


    public String SaveData(JSONObject json) throws Exception {
        log.info("SaveData");
        Datagrid_Save_Data save_data = PageAppConfig.getBean(Datagrid_Save_Data.class);
        Page_Get_Tablename page_get_tablename = PageAppConfig.getBean(Page_Get_Tablename.class);
        json.put("table_name",page_get_tablename.Execute(json.get("pagename").toString()));
        return save_data.Execute(json.toJSONString());
    }
}
