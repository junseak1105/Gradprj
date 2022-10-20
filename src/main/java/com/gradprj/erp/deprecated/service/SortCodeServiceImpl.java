package com.gradprj.erp.deprecated.service;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Log4j2
public class SortCodeServiceImpl implements SortCodeService {

    @Override
    public String getSortCode(String sortlv1, String sortlv2) throws SQLException {
        log.info("getSortCode");
        String condition ="";
        if(sortlv2.equals("")) {
            condition="";
        }else{
            condition = "where sort_lv1 = '" + sortlv1+"' and sort_lv2 = '" + sortlv2+"'";
        }
        ResultSet rs = DBService.DB_Ex_query("select * from sort_code " + condition);
        JSONArray result = new JSONArray();
        while (rs.next()) {
            JSONObject jsonObject = new JSONObject();
            String sort_lv1 = rs.getString("sort_lv1");
            String sort_lv2 = rs.getString("sort_lv2");
            String sort_code = rs.getString("sort_code");
            String sort_code_desc = rs.getString("sort_code_desc");
            jsonObject.put("sort_lv1", sort_lv1);
            jsonObject.put("sort_lv2", sort_lv2);
            jsonObject.put("sort_code", sort_code);
            jsonObject.put("sort_code_desc", sort_code_desc);
            result.add(jsonObject);
        }
        return result.toJSONString();
    }
    @Override
    public String getSortLv1() throws SQLException {
        log.info("getSortLv1");
        ResultSet rs = DBService.DB_Ex_query("select * from sort_lv1");
        JSONArray result = new JSONArray();
        while (rs.next()) {
            JSONObject jsonObject = new JSONObject();
            String sort_lv1 =rs.getString("sort_lv1");
            String sort_lv1_desc = rs.getString("sort_lv1_desc");
            jsonObject.put("sort_lv1",sort_lv1);
            jsonObject.put("sort_lv1_desc",sort_lv1_desc);
            result.add(jsonObject);
        }
        return result.toJSONString();
    }
    @Override
    public String getSortLv2(String sortlv1) throws SQLException {
        log.info("getSortLv2");
        ResultSet rs = DBService.DB_Ex_query("select * from sort_lv2 where sort_lv1 = '" + sortlv1+"'");
        JSONArray result = new JSONArray();
        while (rs.next()) {
            JSONObject jsonObject = new JSONObject();
            String sort_lv1 =rs.getString("sort_lv1");
            String sort_lv2 =rs.getString("sort_lv2");
            String sort_lv2_desc =rs.getString("sort_lv2_desc");
            jsonObject.put("sort_lv1",sort_lv1);
            jsonObject.put("sort_lv2",sort_lv2);
            jsonObject.put("sort_lv2_desc",sort_lv2_desc);
            result.add(jsonObject);
        }
        return result.toJSONString();
    }
    @Override
    public String deleteSortLv1(String sortlv1) {
        log.info("deleteSortLv1");
        String query = "delete from sort_lv1 where sort_lv1 = '" + sortlv1+"'";
        DBService.DB_Ex_query_nr(query);
        return "success";
    }
    @Override
    public String deleteSortLv2(String sortlv1, String sortlv2) {
        log.info("deleteSortLv2");
        String query = "delete from sort_lv2 where sort_lv1 = '" + sortlv1+"' and sort_lv2 = '" + sortlv2+"'";
        DBService.DB_Ex_query_nr(query);
        return "success";
    }
    @Override
    public String deleteSortCode(String sortlv1, String sortlv2, String sortcode) {
        log.info("deleteSortCode");
        String query = "delete from sort_code where sort_lv1 = '" + sortlv1+"' and sort_lv2 = '" + sortlv2+"' and sort_code = '" + sortcode+"'";
        DBService.DB_Ex_query_nr(query);
        return "success";
    }
    @Override
    public String saveSortCode() {
        log.info("saveSortCode");
        return "success";
    }
}
