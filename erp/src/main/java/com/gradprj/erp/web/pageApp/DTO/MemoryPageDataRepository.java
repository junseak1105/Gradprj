package com.gradprj.erp.web.pageApp.DTO;

import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Data
public class MemoryPageDataRepository implements PageDataRepository {
    private final JSONObject page = new JSONObject();

    @Override
    public JSONObject findAll() {
        return page;
    }

    @Override
    public void save(String key, JSONArray value) {
        page.put(key, value);
    }
}
