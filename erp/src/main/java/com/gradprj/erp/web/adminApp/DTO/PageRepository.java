package com.gradprj.erp.web.adminApp.DTO;

import java.util.ArrayList;

public interface PageRepository {

    void save(Page page);

    ArrayList findAll();

    void deleteAll();
}
