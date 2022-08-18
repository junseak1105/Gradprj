package controller;

import DAO.DB_Executer;
import DAO.Table_function;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

@WebServlet(name = "Drop_table", value = "/Drop_table")
public class DropTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DB_Executer db = new DB_Executer();
        Table_function tf = new Table_function();
        String query = tf.Drop_query(request.getParameter("tablename"));

        StringTokenizer token = new StringTokenizer(query,";");
        while(token.hasMoreTokens()){
            db.DB_Ex_query_nr(token.nextToken()+";");
        }
        JSONObject json = new JSONObject();
        json.put("result",query);
        response.getWriter().print(json.toJSONString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
