package com.jhk.Spring.web.table.controller;

import com.jhk.Spring.web.table.DAO.DB_Executer;
import com.jhk.Spring.web.table.DAO.Table_function;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "Get_Table_List", value = "/Get_Table_List")
public class Get_Table_List extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DB_Executer db = new DB_Executer();
        Table_function tf = new Table_function();
        request.setCharacterEncoding("utf-8");

        JSONObject json = new JSONObject();
        String query = "";

        ResultSet rs = tf.getTableList();

        try {
			while (rs.next()) {
                JSONObject json_temp = new JSONObject();
				String table_id = rs.getString("table_id");
				String table_name = rs.getString("table_name");
                String table_explain = rs.getString("table_explain");
                json_temp.put("table_name",table_name);
                json_temp.put("table_explain",table_explain);
                json.put(table_id, json_temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        /*
        반환 형태
        {
        "table_1":
            {"table_explain":"testexplain","table_name":"testtable"},
        "table_00":
            {"table_explain":"testtable","table_name":"testtable00"}
        }
         */
        //System.out.println(json.toJSONString());
		response.getWriter().print(json.toJSONString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
