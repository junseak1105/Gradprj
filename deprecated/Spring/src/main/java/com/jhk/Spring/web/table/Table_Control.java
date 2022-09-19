package com.jhk.Spring.web.table;

import com.jhk.Spring.web.DBService.DB_Service;

public interface Table_Control {
    public String Execute(TableRepository table, DB_Service db);
}
