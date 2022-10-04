package com.gradprj.erp.web.dbApp;

import java.sql.ResultSet;

public interface Executer {
	ResultSet DB_Ex_query(String query);
	boolean DB_Ex_query_nr(String query);
}
