package dbcontrol;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import dbcontrol.constructer.Table_Keyoption;
import dbcontrol.constructer.Table_col_data;
import commonfunc.*;

public class Table_function implements Table_ctrl {
	CommonFunction cf = new CommonFunction();
	DB_Executer db = new DB_Executer();

	// 옵션 빈값 확인
	public static String Option_chk(String query, String option) {
		if (!option.isEmpty()) {
			query = query + " " + option;
		}
		return query;
	}

	@Override
	public ResultSet getTableInfo(String table) {
		String query = "select * from datatable_info where table_id='" + table + "';";
		return db.DB_Ex_query(query);
	}

	@Override
	public ResultSet getTableList() {
		String query = "select * from datatable_list;";
		return db.DB_Ex_query(query);
	}

	@Override
	public String setTableId() {
		String query = "select table_id from datatable_list order by table_id desc limit 1 ;";
		ResultSet rs = db.DB_Ex_query(query);
		String table_id = "";
		try {
			while (rs.next()) {
				table_id = rs.getString("table_id");
				StringTokenizer token = new StringTokenizer(table_id, "_");
				table_id = (String)token.nextToken() + "_" + (Integer.parseInt((String)token.nextToken()) + 1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return table_id;
	}

	@Override
	public String setTableInfo(ArrayList coldata, Table_Keyoption keyoption) {
		String tableinfo = "";
		String[] fk_list = keyoption.fk;  //fk 조건 미작성
		
		for (int i = 0; i < coldata.size(); i++) {
			Table_col_data temp = (Table_col_data) coldata.get(i); //name type option
			tableinfo += (temp.name);
			if(temp.option.contains("not null")) {
				tableinfo+=",1";
			}else {
				tableinfo+=",0";
			}
			if(i!=coldata.size()-1) {
				tableinfo+="/";
			}
		}
		
		return tableinfo;
	}

	@Override
	public void Option_builder(String option) {
		// TODO Auto-generated method stub

	}

	@Override
	public String Select_query(String db, String[] col, String option) {
		String query = "SELECT ";
		for (int i = 0; i < col.length; i++) {
			query += col[i];
			if (i != (col.length - 1)) {
				query += ",";
			}
		}
		query += " from " + db;
		query = Option_chk(query, option);

		return query;
	}

	@Override
	public String Update_query(String db, String[] col, String[] value, String option) {
		String query = "UPDATE " + db + " set ";
		for (int i = 0; i < col.length; i++) {
			query = " " + query + col[i] + "=";
			if (cf.Valueisdigit(col[i])) {
				query = query + "" + value;
			} else {
				query = query + "'" + value + "'";
			}
			if (i != (col.length - 1)) {
				query += ",";
			}
		}

		query = Option_chk(query, option);
		// System.out.println(query);
		return query;
	}

	@Override
	public String Delete_query(String db, String option) {
		String query = "DELETE FROM " + db;
		query = Option_chk(query, option);

		return query;
	}

	@Override
	public String Insert_query(String table, String[] col, String[] value) {
		String query = "INSERT INTO " + table + "(";
		for (int i = 0; i < col.length; i++) {
			query = query + (String)col[i];
			if (i != (col.length - 1)) {
				query += ",";
			}
		}
		query += ") values(";
		for (int i = 0; i < value.length; i++) {
			if (cf.Valueisdigit((String)col[i])) {
				query = query + "" + value[i];
			} else {
				query = query + "'" + value[i] + "'";
			}
			if (i != (value.length - 1)) {
				query += ",";
			}
		}
		query += ");";
		return query;
	}

	@Override
	public String Create_query(String tablename, String tableexplain, ArrayList coldata, Table_Keyoption keyoption) {
		String query = "CREATE TABLE `gradprj`.`" + tablename + "` (\n";
		// col명 추가
		for (int i = 0; i < coldata.size(); i++) {
			Table_col_data temp = (Table_col_data) coldata.get(i);
			query += "`" + temp.name + "`";
			query += " " + temp.type;
			query += " " + temp.option + ",\n";
		}

		// Key들 추가
		Table_Keyoption k_option = (Table_Keyoption) keyoption;

		if (k_option.pk.length > 0) {
			query += "PRIMARY KEY(";
			// pk추가
			for (int i = 0; i < k_option.pk.length; i++) {
				query += "`" + k_option.pk[i];
				if (i == (k_option.pk.length - 1)) {
					query += "`),\n";
				} else {
					query += "`,";
				}
			}
		}
		// uq추가
		for (int i = 0; i < k_option.pk.length; i++) {
			query += "UNIQUE INDEX `" + k_option.pk[i] + "_UNIQUE` (`" + k_option.pk[i] + "` ASC) VISIBLE";
			if (i == (k_option.pk.length - 1)) {
				query += ");\n";
			} else {
				query += ",\n";
			}
		}
		// fk추가(미작성)
//		for (int i = 0; i < k_option.pk.length; i++) {
//
//		}
		String table_id = setTableId();
		String col[] = {"table_id","table_name","table_explain"};
		String val[] = {table_id,tablename,tableexplain};
		db.DB_Ex_query_nr(Insert_query("datatable_list", col, val));
		String col2[] = {"table_id","table_content"};
		String val2[] = {table_id,setTableInfo(coldata, keyoption)};
		db.DB_Ex_query_nr(Insert_query("datatable_info", col2, val2));
		return query;
	}

}
