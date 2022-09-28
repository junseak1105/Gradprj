package com.gradprj.erp.web.adminApp;


import com.gradprj.erp.BaseApp;
import com.gradprj.erp.web.adminApp.DAO.*;
import com.gradprj.erp.web.adminApp.DTO.DatagridRepository;
import com.gradprj.erp.web.adminApp.DTO.Page;
import com.gradprj.erp.web.adminApp.DTO.PageRepository;
import com.gradprj.erp.web.adminApp.DTO.SortcodeRepository;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminApp extends BaseApp {

    //Application Context 생성
    private static final ApplicationContext PageAppConfig = new AnnotationConfigApplicationContext(AdminAppConfig.class);

    //Repository 생성
    private static final PageRepository pageRepository = PageAppConfig.getBean("PageRepository", PageRepository.class);
    private static final SortcodeRepository sortcodeRepository = PageAppConfig.getBean("SortcodeRepository", SortcodeRepository.class);


    /**
     * 페이지명 중복체크
     * @param json
     * @return json
     * @throws SQLException
     */
    public String Page_name_dupchk(JSONObject json) throws SQLException {

        //테이블 명 중복체크 빈 가져오기
        Page_Service page_name_dupchk = PageAppConfig.getBean("page_name_dupchk_service", Page_Name_Dupchk_Service.class);

        //중복체크할 테이블 명 저장
//        page_name_dupchk.getTableRepository().save(new Table((String)json.get("name"),"table_info",null,null,null,null,null,(String)json.get("tablecomment")));

        json.clear();

        //중복체크 후 결과값 반환
        json.put("result", page_name_dupchk.Execute(""));

        return json.toJSONString();
    }

    public String page_get_list() throws SQLException {
        pageRepository.deleteAll();
        Page_Service page_get_list = PageAppConfig.getBean("page_get_list_service", Page_Get_List_Service.class);
        page_get_list.Execute("");

        JSONArray json = new JSONArray();
        ArrayList temp = pageRepository.findAll();

        for (int i = 0; i < temp.size(); i++) {
            Page page = (Page) temp.get(i);
            JSONObject page_json = new JSONObject();
            page_json.put("idx", page.getIdx());
            page_json.put("page_name", page.getPage_name());
            page_json.put("page_desc", page.getPage_desc());
            page_json.put("page_url", page.getPage_url());
            page_json.put("page_table", page.getPage_table());
            page_json.put("page_category", page.getPage_category());

            json.put(page_json);
        }

        return json.toString();
    }

    public String page_get_category() throws SQLException {
        Page_Service page_get_category = PageAppConfig.getBean("page_get_category_service", Page_Get_Category_Service.class);
        return page_get_category.Execute("");
    }

    public String page_get_sort_lv1() throws SQLException {
        Page_Service page_get_sort_lv1 = PageAppConfig.getBean("page_get_sort_lv1_service", Page_Get_Sort_lv1_Service.class);
        String result = page_get_sort_lv1.Execute("");
        return result;
    }
    public String page_get_sort_lv2(String sort_lv1) throws SQLException {
        Page_Service page_get_sort_lv2 = PageAppConfig.getBean("page_get_sort_lv2_service", Page_Get_Sort_lv2_Service.class);
        String result = page_get_sort_lv2.Execute("where sort_lv1 = '" + sort_lv1+"'");
        return result;
    }
    public String page_get_sort_code(String sort_lv1, String sort_lv2) throws SQLException {
        Page_Service page_get_sort_code = PageAppConfig.getBean("page_get_sort_code_service", Page_Get_Sort_code_Service.class);
        String result = page_get_sort_code.Execute("where sort_lv1 = '" + sort_lv1+"' and sort_lv2 = '" + sort_lv2+"'");
        return result;
    }
    public String page_delete_sort(String code) throws SQLException {
        Page_Service page_delete_sort = PageAppConfig.getBean("page_delete_sort_service", Page_Delete_Sort_Service.class);
        String result = page_delete_sort.Execute("delete from sort_lv1 where sort_lv1 = '"+code+"'");
        return result;
    }


    public String page_delete_sort(String sort_lv1,String code) throws SQLException {
        Page_Service page_delete_sort = PageAppConfig.getBean("page_delete_sort_service", Page_Delete_Sort_Service.class);
        String result = page_delete_sort.Execute("delete from sort_lv2 where sort_lv1 = '"+sort_lv1+"' and sort_lv2 = '"+code+"'");
        return result;
    }

    public String page_delete_sort(String sort_lv1,String sort_lv2,String code) throws SQLException {
        Page_Service page_delete_sort = PageAppConfig.getBean("page_delete_sort_service", Page_Delete_Sort_Service.class);
        String result = page_delete_sort.Execute("delete from sort_code where sort_lv1 = '"+sort_lv1+"' and sort_lv2 = '"+sort_lv2+"' and sort_code = '"+code+"'");
        return result;
    }

    public String Page_create(JSONObject json) throws SQLException {
        pageRepository.deleteAll();
        Page_Service page_create = PageAppConfig.getBean("page_create_service", Page_Create_Service.class);
        pageRepository.save(new Page(Integer.parseInt((String)json.get("idx")),(String) json.get("page_name"),(String)json.get("page_desc"),(String)json.get("page_url"),(String)json.get("page_table"),(String)json.get("page_category")));
        String result = page_create.Execute("");
        json.clear();
        json.put("result",result);
        return json.toJSONString();
    }
}
