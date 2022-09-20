package com.gradprj.erp.web.tableApp.DTO;

import lombok.Data;

@Data
public class Table {
    private final String Name; // Table Name
    private final String Field; // Field Name
    private final String Type;  // Field Type
    private final String Null; // Null
    private final String Key;   // Key
    private final String Default; // Default
    private final String Extra;// Extra
    private final String Comment; // Comment

}

