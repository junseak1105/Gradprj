package com.gradprj.erp.web.adminApp.DTO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MemorySortcodeRepository implements SortcodeRepository {

    private static final ArrayList<Sortcode> sortcode_data = new ArrayList<>();

    @Override
    public void save(Sortcode sortcode) {
        sortcode_data.add(sortcode);
    }

    @Override
    public ArrayList findAll() {
        return sortcode_data;
    }

    @Override
    public void deleteAll() {
        sortcode_data.clear();
    }

}
