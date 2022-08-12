package dbcontrol;

import java.sql.ResultSet;
import java.util.ArrayList;

import dbcontrol.DTO.Table_Keyoption_DTO;

public interface Table_ctrl extends QueryBuilder {
	public String setTableId();
	
	public String getTableId(String tablename);

	public ResultSet getTableList(); // 테이블 목록 조회

	public ResultSet getTableInfo(String table); // 특정 테이블 정보 조회

	public String setTableInfo(ArrayList coldata, Table_Keyoption_DTO keyoption);// 테이블

	public String Create_query(String tablename, String tableexplain, ArrayList coldata, Table_Keyoption_DTO keyoption);
	
	public String Drop_query(String tablename); //테이블 삭제, table_id 가져와서 삭제
	
//	public String Alter_query();
	

}
