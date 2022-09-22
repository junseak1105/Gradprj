package com.gradprj.erp.web.pageApp;


import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.pageApp.DAO.*;
import com.gradprj.erp.web.pageApp.DTO.DatagridRepository;
import com.gradprj.erp.web.pageApp.DTO.Page;
import com.gradprj.erp.web.pageApp.DTO.PageRepository;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class PageApp extends BaseApp {

    //Application Context 생성
    private static final ApplicationContext PageAppConfig = new AnnotationConfigApplicationContext(PageAppConfig.class);

    //Repository 생성
    private static final PageRepository pageRepository = PageAppConfig.getBean("PageRepository", PageRepository.class);
    private static final DatagridRepository datagridRepository = PageAppConfig.getBean("DatagridRepository", DatagridRepository.class);

    /**
     * Datagrid 데이터 fetch
     */
    public JSONObject datagrid_get_data(String viewname) throws SQLException {

        Page_Datagrid datagrid_get = PageAppConfig.getBean("page_get_datagrid_service", Page_Get_Datagrid_Service.class);

        datagrid_get.GetDatagrid(viewname);

        return null;
    }

    /**
     * 페이지명 중복체크
     * @param json
     * @return json
     * @throws SQLException
     */
    public JSONObject Page_name_dupchk(JSONObject json) throws SQLException {

        //테이블 명 중복체크 빈 가져오기
        Page_Service page_name_dupchk = PageAppConfig.getBean("page_name_dupchk_service", Page_Name_Dupchk_Service.class);

        //중복체크할 테이블 명 저장
//        page_name_dupchk.getTableRepository().save(new Table((String)json.get("name"),"table_info",null,null,null,null,null,(String)json.get("tablecomment")));

        //반환을 위해 json 비우기
        json.clear();

        //중복체크 후 결과값 반환
        json.put("result", page_name_dupchk.Execute());
        return json;
    }

    public ArrayList page_get_list() throws SQLException {
        pageRepository.deleteAll();
        Page_Service page_get_list = PageAppConfig.getBean("page_get_list_service", Page_Get_List_Service.class);
        page_get_list.Execute();
        return pageRepository.findAll();
    }

    public ArrayList page_get_category() throws SQLException {
        pageRepository.deleteAll();
        Page_Service page_get_category = PageAppConfig.getBean("page_get_category_service", Page_Get_Category_Service.class);
        page_get_category.Execute();
        return pageRepository.findAll();
    }

    public JSONObject Page_create(JSONObject json) throws SQLException {
        pageRepository.deleteAll();
        Page_Service page_create = PageAppConfig.getBean("page_create_service", Page_Create_Service.class);
        pageRepository.save(new Page(Integer.parseInt((String)json.get("idx")),(String) json.get("page_name"),(String)json.get("page_desc"),(String)json.get("page_url"),(String)json.get("page_table"),(String)json.get("page_category")));
        String result = page_create.Execute();
        json.clear();
        json.put("result",result);
        return json;
    }
}
