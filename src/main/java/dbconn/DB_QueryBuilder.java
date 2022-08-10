package dbconn;

import java.sql.ResultSet;

public interface DB_QueryBuilder {
	public ResultSet Select_query(String db,String col[],String option);//db명,col명,where option
	public void Update_query(String db,String col[], String value[],String option);//db명,col명,업데이트할 값
	public void Delete_query(String db,String option);//db명, where option
	public void Insert_query(String db,String col[],String value[]);//단일 insert:db명,col명,넣을 값
//	public String Create_query();
//	public String Alter_query();
//	public String Drop_query();
	public void Option_builder(String option); //옵션 작성
	
}
