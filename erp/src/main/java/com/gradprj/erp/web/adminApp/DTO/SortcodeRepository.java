package com.gradprj.erp.web.adminApp.DTO;

import java.util.ArrayList;

public interface SortcodeRepository {

    void save(Sortcode sortcode);

    ArrayList findAll();

    void deleteAll();
}
