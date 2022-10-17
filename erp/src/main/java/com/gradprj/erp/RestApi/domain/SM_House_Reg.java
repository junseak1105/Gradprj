package com.gradprj.erp.RestApi.domain;

import lombok.Data;

@Data
public class SM_House_Reg {
    private String House_Code;
    private String House_Name;
    private String House_Location_IN;
    private String House_Location_OUT;
    private String House_Explanation;
    private boolean House_Status;
}
