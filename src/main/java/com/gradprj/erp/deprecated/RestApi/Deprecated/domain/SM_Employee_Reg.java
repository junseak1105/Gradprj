package com.gradprj.erp.deprecated.RestApi.Deprecated.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class SM_Employee_Reg {
    private String Employee_Code;
    private String Employee_Name;
    private String Department_Code;
    private String Department_Name;
    private Date JoinDate;
    private Date Resignation_Date;
    private int Emergency_Contact_Network;

}
