package dbconn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Executer extends JDBC_conn implements DB_QueryBuilder {
	private static PreparedStatement pstmt;
	private static ResultSet rs = null;
	
	
	//옵션 빈값 확인
	public static String Option_chk(String query,String option) {
		if(!option.isEmpty()) {
			query = query+" "+option;
		}
		return query;
	}
	//문자열 숫자인지 아닌지
	public static String Valueisdigit(String query,String value) {
		boolean output =true;
		for(int i = 0 ; i < query.length() ; i++) {	//입력받은 문자열인 input의 길이만큼 반복문 진행(배열이 아닌 문자열의 길이기 때문에 length가 아닌 length()를 사용해야한다.)
			char tmp = query.charAt(i);	//한글자씩 검사하기 위해서 char형 변수인 tmp에 임시저장
			if(Character.isDigit(tmp) == false) {	//문자열이 숫자가 아닐 경우
				output = false;	//output의 값을 false로 바꿈
			}
		}
		if(output) {
			query = query+""+value;
		}else {
			query = query+"'"+value+"'";
		}
		
		return query;
	}
	
	//rs 반환
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
	
	//rs미반환
	public void DB_Ex_query_nr(String query) {
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void Option_builder(String option) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ResultSet Select_query(String db, String[] col, String option) {
		String query="SELECT ";
		for (int i = 0; i < col.length; i++) {
			query += col[i];
			if(i!=(col.length-1)) {
				query+=",";
			}
		}
		query+=" from "+db;
		query = Option_chk(query,option);
		
		return DB_Ex_query(query);
	}
	
	@Override
	public void Update_query(String db, String[] col, String[] value,String option) {
		String query="UPDATE "+db+" set ";
		for (int i = 0; i < col.length; i++) {
			query = " "+query+col[i]+"=";
			query = Valueisdigit(query,value[i]);
			if(i!=(col.length-1)) {
				query+=",";
			}
		}
		
		query = Option_chk(query,option);
		System.out.println(query);
		DB_Ex_query_nr(query);
	}
	
	@Override
	public void Delete_query(String db, String option) {
		String query="DELETE FROM "+db;
		query = Option_chk(query,option);
		
		DB_Ex_query_nr(query);
	}
	
	@Override
	public void Insert_query(String db, String[] col, String[] value) {
		String query="INSERT INTO "+db+"(";
		for (int i = 0; i < col.length; i++) {
			query = query+col[i];
			if(i!=(col.length-1)) {
				query+=",";
			}
		}
		query +=") values(";
		for (int i = 0; i < value.length; i++) {
			query = Valueisdigit(query,value[i]);
			if(i!=(value.length-1)) {
				query+=",";
			}
		}
		query +=")";
		DB_Ex_query_nr(query);
	}

	
	
//	public ResultSet DB_run_query(String query) {
//		try {
//			pstmt = conn.prepareStatement(query);
//			rs = pstmt.executeQuery();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return rs;
//	}
//	
//	@Override
//	public String Select_query(String db, String arr[], String sort) {
//		String query="SELECT ";
//		//select 할 column 구문에 추가
//		for (int i = 0; i < arr.length; i++) {
//			query += arr[i];
//			if(i==(arr.length-1)) {
//				query+=" from "+db;
//			}else {
//				query+=",";
//			}
//		}
//		if(!sort.isEmpty()) {
//			query = query+" "+sort;
//		}
//		
//		return query;
//	}
//
//	@Override
//	public ResultSet DB_select(String query) {
//		PreparedStatement pstmt;
//		ResultSet rs = null;
//		try {
//			pstmt = conn.prepareStatement(query);
//			rs = pstmt.executeQuery();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return rs;
//	}
//
//	@Override
//	public ResultSet DB_update() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ResultSet DB_delete() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ResultSet DB_insert() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
