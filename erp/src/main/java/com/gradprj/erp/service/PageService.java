package com.gradprj.erp.service;

import com.gradprj.erp.domain.Page;
import com.gradprj.erp.domain.PageRepository;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Component
@Log4j2
public class PageService {

    @Autowired
    PageRepository pageRepository;

    public String addPage(JSONObject json) {
        log.info("addPage");
        pageRepository.deleteAll();
        pageRepository.save(new Page(Integer.parseInt((String)json.get("idx")),(String) json.get("page_name"),(String)json.get("page_desc"),(String)json.get("page_table"),(String)json.get("page_category")));
        Page page = (Page) pageRepository.findAll().get(0);
        String query = "INSERT INTO page_list(page_name,page_desc,page_table,page_category)" +
                "VALUES('" + page.getPage_name() + "','" + page.getPage_desc() + "','" + page.getPage_table() + "','" + page.getPage_category() + "')";
        log.info(query);
        json.clear();
        if(DBService.DB_Ex_query_nr(query)){
            json.put("result","success");
        }else{
            json.put("result","fail");
        }
        return json.toJSONString();
    }

    public String deletePage(String idx) {
        log.info("deletePage");
        String query = "DELETE FROM page_list where idx = "+idx;
        log.info(query);
        if(DBService.DB_Ex_query_nr(query)){
            return "success";
        }else{
            return "fail";
        }
    }

    public String deletePageCategory(String idx) {
        log.info("deletePage");
        String query = "DELETE FROM page_category where idx = "+idx;
        log.info(query);
        if(DBService.DB_Ex_query_nr(query)){
            return "success";
        }else{
            return "fail";
        }
    }

    public String getPageCategory() throws SQLException {
        log.info("getPageCategory");
        JSONArray json = new JSONArray();
        ResultSet rs = DBService.DB_Ex_query("select * from page_category");
        while (rs.next()) {
            JSONObject page_json = new JSONObject();
            page_json.put("idx", Integer.parseInt(rs.getString("idx")));
            page_json.put("page_category", rs.getString("page_category"));
            json.put(page_json);
        }
        return json.toString();
    }

    public String getPageList() throws SQLException {
        log.info("getPageList");
        pageRepository.deleteAll();

        ResultSet rs = DBService.DB_Ex_query("select page_list.idx,page_name,page_desc, page_table,pc.page_category from page_list left join page_category pc on pc.page_category = page_list.page_category;");
        while (rs.next()) {
            Page page = new Page(
                    Integer.parseInt(rs.getString("idx")),
                    rs.getString("page_name"),
                    rs.getString("page_desc"),
                    rs.getString("page_table"),
                    rs.getString("page_category")
            );
            pageRepository.save(page);
        }

        JSONArray json = new JSONArray();
        ArrayList temp = pageRepository.findAll();

        for (int i = 0; i < temp.size(); i++) {
            Page page = (Page) temp.get(i);
            JSONObject page_json = new JSONObject();
            page_json.put("idx", page.getIdx());
            page_json.put("page_name", page.getPage_name());
            page_json.put("page_desc", page.getPage_desc());
            page_json.put("page_table", page.getPage_table());
            page_json.put("page_category", page.getPage_category());

            json.put(page_json);
        }

        return json.toString();
    }

    public String updatePage() {
        log.info("updatePage");
        return null;
    }


}
