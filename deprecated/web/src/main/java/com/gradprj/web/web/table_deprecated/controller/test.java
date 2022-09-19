package com.gradprj.web.web.table_deprecated.controller;

import com.gradprj.web.web.table_deprecated.DAO.DB_Executer;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "test", value = "/test")
public class test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DB_Executer db = new DB_Executer();
        String query = "select * from mysqllogin;";
        ResultSet rs = db.DB_Ex_query(query);
        JSONObject json = new JSONObject();
        int i=1;
        try {
            while (rs.next()) {
                JSONObject json_temp = new JSONObject(); //col 이름 모음
                String userid = rs.getString("userid");
                String passwd = rs.getString("passwd");
                String email = rs.getString("email");

                json_temp.put("userid", userid);
                json_temp.put("passwd", passwd);
                json_temp.put("email", email);

                json.put(i,json_temp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(json.toJSONString());
        response.getWriter().print(json.toJSONString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
