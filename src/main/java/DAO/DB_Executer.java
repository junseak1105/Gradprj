package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Beans.DTO.Table_Keyoption_DTO;
import Beans.DTO.Table_col_DTO;
import common.JDBC_conn;

public class DB_Executer extends JDBC_conn implements Executer {
	private static PreparedStatement pstmt;
	private static ResultSet rs = null;

	// rs 반환
	@Override
	public ResultSet DB_Ex_query(String query) {
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
	@Override
	public void DB_Ex_query_nr(String query) {
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
