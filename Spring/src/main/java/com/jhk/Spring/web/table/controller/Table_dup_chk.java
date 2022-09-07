package com.jhk.Spring.web.table.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhk.Spring.web.table.DAO.Table_function;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class Table_dup_chk
 */
@WebServlet("/Table_dup_chk")
public class Table_dup_chk extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Table_dup_chk() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Table_function tf = new Table_function();
		String tablename = request.getParameter("name");
		tablename = tf.getTableId(tablename);
	
		JSONObject json = new JSONObject();
		json.put("result", tablename);
		response.setCharacterEncoding("utf-8");
		System.out.println(json.toJSONString());
		response.getWriter().print(json.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
