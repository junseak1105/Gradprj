package DAO;

import java.sql.ResultSet;

public interface Executer {
	public ResultSet DB_Ex_query(String query);
	public void DB_Ex_query_nr(String query);
}
