package com.gradprj.erp.web.pageApp.DTO;

import java.util.ArrayList;
import java.util.Map;

public interface PageRepository {

    void save(Page page);

    ArrayList findAll();

    void deleteAll();
}
