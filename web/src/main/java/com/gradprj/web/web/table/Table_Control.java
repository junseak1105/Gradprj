package com.gradprj.web.web.table;

import com.gradprj.web.web.DBService.DB_Service;

public interface Table_Control {
    public String Execute(TableRepository table, DB_Service db);
}
