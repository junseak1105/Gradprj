package com.gradprj.erp.domain;

import lombok.Data;

@Data
public class TableInfo {

    private String Column_Name;
    private String Data_Type;
    private String Data_Length;
    private String Column_Key;
    private String Column_Comment;
    private String Is_Nullable;
    private String Ref_Table;

}
