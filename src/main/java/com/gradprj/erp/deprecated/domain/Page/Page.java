package com.gradprj.erp.deprecated.domain.Page;

import lombok.Data;

@Data
public class Page {
    private final int idx; // Page Index
    private final String page_name; // Page Title
    private final String page_desc; // Page Description
    private final String page_table; // Page Table for datagrid
    private final String page_category; // Page Category

}
