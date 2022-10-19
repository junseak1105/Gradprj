package com.gradprj.erp.domain;

import lombok.Data;

@Data
public class PageCategory {
    private String category_name;
    private String category_explain;
    private String category_sort;
    private boolean previlege;
}
