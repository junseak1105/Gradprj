package com.jhk.Spring.web.table;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Data
public class Table {
    private final Long Row;
    private final String Name;
    private final String Field;
    private final String Type;
    private final String Null;
    private final String Key;
    private final String Default;
    private final String Extra;

}

