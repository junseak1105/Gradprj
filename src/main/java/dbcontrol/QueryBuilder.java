package dbcontrol;

import java.sql.ResultSet;
import java.util.ArrayList;

import dbcontrol.constructer.Table_Keyoption;

public interface QueryBuilder {
	public String Select_query(String db,String col[],String option);//db명,col명,where option
	public String Update_query(String db,String col[], String value[],String option);//db명,col명,업데이트할 값
	public String Delete_query(String db,String option);//db명, where option
	public String Insert_query(String db,String col[],String value[]);//단일 insert:db명,col명,넣을 값
	public String Create_query(String tablename,String tableexplain,ArrayList coldata,Table_Keyoption keyoption);
//	public String Alter_query();
//	public String Drop_query();
	public void Option_builder(String option); //옵션 작성
	
}
