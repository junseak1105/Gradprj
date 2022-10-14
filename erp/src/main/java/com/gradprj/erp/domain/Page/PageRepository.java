package com.gradprj.erp.domain.Page;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface PageRepository {

    void save(Page page);

    ArrayList findAll();

    void deleteAll();
}
