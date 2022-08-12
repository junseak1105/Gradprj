package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbcontrol.DB_Executer_DAO;
import dbcontrol.Table_function;
import dbcontrol.DTO.Table_Keyoption_DTO;
import dbcontrol.DTO.Table_col_DTO;

/**
 * Servlet implementation class CreateNewTable
 */
@WebServlet("/CreateNewTable")
public class CreateNewTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateNewTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap map = new HashMap();
		map.putAll(request.getParameterMap());
		DB_Executer_DAO db = new DB_Executer_DAO();
		Table_function tf = new Table_function();
		/*테이블 추가 사용법*/
		ArrayList col_d_list = new ArrayList();
//		col_d_list.add(new Table_col_data("test_col1", "int", "Not Null"));
//		col_d_list.add(new Table_col_data("test_col2", "int", "Not Null"));
//		col_d_list.add(new Table_col_data("test_col3", "varchar(30)", "Not Null"));
//		col_d_list.add(new Table_col_data("test_col4", "varchar(30)", "Not Null"));

//		String pk[] = {"test_col1","test_col2"};
//		String uq[] = {"test_col1","test_col2","test_col3"};
//		String fk[] = {};
//		Table_Keyoption_DTO koption = new Table_Keyoption_DTO(pk, uq, fk);
//		String query = tf.Create_query("testtable", "testexplain",col_d_list, null);
//		System.out.println(query);
//		db.DB_Ex_query_nr(query);
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
