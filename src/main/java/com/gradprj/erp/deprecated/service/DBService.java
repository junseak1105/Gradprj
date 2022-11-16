package com.gradprj.erp.deprecated.service;

import com.gradprj.erp.deprecated.common.JDBC_conn;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DBService extends JDBC_conn{
	private static PreparedStatement pstmt;
	private static ResultSet rs = null;

	// rs 반환
	public static ResultSet DB_Ex_query(String query) {
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	// rs미반환
	public static boolean DB_Ex_query_nr(String query) {
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


}
