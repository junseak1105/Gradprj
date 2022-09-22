package com.gradprj.erp.web.pageApp.DTO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryPageRepository implements PageRepository {

    private static final ArrayList<Page> page_data = new ArrayList<>();

    @Override
    public void save(Page page) {
        page_data.add(page);
    }

    @Override
    public ArrayList findAll() {
        return page_data;
    }

    @Override
    public void deleteAll() {
        page_data.clear();
    }

}
