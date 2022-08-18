package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import Beans.DTO.Table_Keyoption_DTO;

public interface QueryBuilder {
	public String Select_query(String db,String col[],String option);//db명,col명,where option
	public String Update_query(String db,String col[], String value[],String option);//db명,col명,업데이트할 값
	public String Delete_query(String db,String option);//db명, where option
	public String Insert_query(String db,String col[],String value[]);//단일 insert:db명,col명,넣을 값
	
	
	public void Option_builder(String option); //옵션 작성
	
	
}
