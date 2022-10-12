package com.gradprj.erp.web.pageApp.Service;

import java.sql.SQLException;

public interface Page_Service {
    String Execute(String condition) throws SQLException, Exception;
}
