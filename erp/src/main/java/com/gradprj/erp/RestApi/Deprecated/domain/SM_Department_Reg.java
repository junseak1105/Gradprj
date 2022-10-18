package com.gradprj.erp.RestApi.Deprecated.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

@Data
public class SM_Department_Reg {
    private String Department_Code;
    private String Department_Name;
    private String Workplace_Code;
    private String Workplace_Name;
    private String Sector_Code;
    private String Sector_Name;
    private Date UseDate;
}
