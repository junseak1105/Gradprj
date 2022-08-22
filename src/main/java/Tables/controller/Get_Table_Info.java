package Tables.controller;

import Tables.DAO.DB_Executer;
import Tables.DAO.Table_function;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

@WebServlet(name = "Get_Table_Info", value = "/Get_Table_Info")
public class Get_Table_Info extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DB_Executer db = new DB_Executer();
        Table_function tf = new Table_function();
        request.setCharacterEncoding("utf-8");

        JSONObject json = new JSONObject();
        String query = "";

        ResultSet rs = tf.getTableInfo(request.getParameter("tablename"));

        try {
            while (rs.next()) {
                JSONObject json_col = new JSONObject(); //col 이름 모음
                String table_id = rs.getString("table_id");
                String table_content = rs.getString("table_content");
                String table_name = rs.getString("table_name");

                json.put("table_id", table_id);
                json.put("table_name", table_name);
                StringTokenizer token = new StringTokenizer(table_content, "/");
                int i = 0;
                while (token.hasMoreTokens()) {
                    i++;
                    String temp = token.nextToken();
                    temp = temp.substring(0, temp.length() - 2);
                    json_col.put("col_" + i, temp);
                }
                json.put("colcnt", i);
                json.put("columns", json_col);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
        반환 형태
        {
        "columns":
            {"col_2":"col2","col_1":"col1"},
        "colcnt":2,
        "table_id":"table_1",
        "table_name":"test"
        }
         */
        //System.out.println(json.toJSONString());
        response.getWriter().print(json.toJSONString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
