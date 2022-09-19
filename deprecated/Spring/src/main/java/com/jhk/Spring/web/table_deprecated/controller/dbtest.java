package com.jhk.Spring.web.table_deprecated.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhk.Spring.web.table_deprecated.DAO.DB_Executer;
import com.jhk.Spring.web.table_deprecated.DAO.Table_function;

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
		Table_function tf = new Table_function();
		String table="users";
		String col[]= {"username"};
		String value[]= {"test3"};
		String option = "where idx = 1";
		
		
		System.out.println("test");
		//System.out.println(tf.Drop_query("testtable"));
		//db.DB_Ex_query_nr(tf.Drop_query("testtable"));
		
		
		//System.out.println(tf.setTableId()); 
		
		/*테이블 추가 사용법*/
//		ArrayList col_d_list = new ArrayList();
//		col_d_list.add(new Table_col_data("test_col1", "int", "Not Null"));
//		col_d_list.add(new Table_col_data("test_col2", "int", "Not Null"));
//		col_d_list.add(new Table_col_data("test_col3", "varchar(30)", "Not Null"));
//		col_d_list.add(new Table_col_data("test_col4", "varchar(30)", "Not Null"));
//
//		String pk[] = {"test_col1","test_col2"};
//		String uq[] = {"test_col1","test_col2","test_col3"};
//		String fk[] = {};
//		Table_Keyoption koption = new Table_Keyoption(pk, uq, fk);
//		String query = tf.Create_query("testtable", "testexplain",col_d_list, null);
//		System.out.println(query);
//		db.DB_Ex_query_nr(query);
		
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
