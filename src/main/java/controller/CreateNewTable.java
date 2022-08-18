package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Beans.DTO.Table_Keyoption_DTO;
import Beans.DTO.Table_col_DTO;
import DAO.DB_Executer;
import DAO.Table_function;

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
		
		DB_Executer db = new DB_Executer();
		Table_function tf = new Table_function();
		
		request.setCharacterEncoding("utf-8");
		/*테이블 추가 사용법*/
		ArrayList col_d_list = new ArrayList();
		
		JSONObject json = new JSONObject();
		

		
		for(int i = 1; i<=Integer.parseInt(request.getParameter("colnum"));i++) {
			String colname=request.getParameter("colname_"+i);
			String type=request.getParameter("type_"+i);
			String NN=request.getParameter("NN_"+i);
			if(type.equals("숫자")) {
				type = "int";
			}else if(type.equals("문자")) {
				type = "text";
			}
			if(NN.equals("허용")) {
				NN = "";
			}else if(NN.equals("미허용")) {
				NN = "Not Null";
			}
			col_d_list.add(new Table_col_DTO(colname,type,NN));
			
		}


		String pk[] = {};
		String uq[] = {};
		String fk[] = {};
		Table_Keyoption_DTO koption = new Table_Keyoption_DTO(pk, uq, fk);
		String query = tf.Create_query(request.getParameter("tablename"), request.getParameter("tableexplain"),col_d_list, null);
		System.out.println(query);

//		json.put("query", query);
//		System.out.println(json.toJSONString());
//		response.getWriter().print(json.toJSONString());
		db.DB_Ex_query_nr(query);
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
