package com.gradprj.erp.RestApi.domain;

import lombok.Data;

@Data
public class TableInfo {

    private String Column_Name;
    private String Data_Type;
    private String Data_Length;
    private String Column_Key;
    private String Column_Comment;
    private String Ref_Table;

}
