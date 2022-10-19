package com.gradprj.erp.deprecated.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_conn {
	public static Connection conn; // DB 커넥션 연결 객체
	private static final String USERNAME = "june1105";// DBMS접속 시 아이디
	private static final String PASSWORD = "(rla)wogns0249";// DBMS접속 시 비밀번호
	private static final String URL = "jdbc:mysql://61.98.217.46:3306/gradprj";// DBMS접속할 db명

	public JDBC_conn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DBMS 접속 실패");
			e.printStackTrace();
		}
	}

}
