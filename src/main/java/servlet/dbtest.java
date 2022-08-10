package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconn.DB_Executer;
import dbconn.JDBC_conn;

/**
 * Servlet implementation class dbtest
 */
@WebServlet("/dbtest")
public class dbtest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public dbtest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DB_Executer db = new DB_Executer();
		String table="users";
		String col[]= {"username"};
		String value[]= {"test3"};
		String option = "where idx = 1";
		db.Insert_query(table,col,value);
//		ResultSet rs = db.Update_query(table,col,value,option);
//		
//		response.setContentType("text/html;charaset=utf-8");
//		PrintWriter out = response.getWriter();
//		try {
//			while (rs.next()) {
//				String idx = rs.getString("idx");
//				String username = rs.getString("username");
//				out.println("idx:"+idx+" username:"+username);
//				
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
