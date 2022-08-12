package dbcontrol;

import java.sql.ResultSet;
import java.util.ArrayList;

import dbcontrol.constructer.Table_Keyoption;

public interface Table_ctrl extends QueryBuilder {
	public String setTableId();

	public ResultSet getTableList(); // 테이블 목록 조회

	public ResultSet getTableInfo(String table); // 특정 테이블 정보 조회

	public String setTableInfo(ArrayList coldata, Table_Keyoption keyoption);// 테이블
																													// 정보
																													// 제작

}
