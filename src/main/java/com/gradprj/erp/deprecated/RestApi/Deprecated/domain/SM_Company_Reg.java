package com.gradprj.erp.deprecated.RestApi.Deprecated.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

@Data
public class SM_Company_Reg {
    @ApiModelProperty(name = "회사코드", example = "1234567890", required = true)
    private String Company_Code;
    @ApiModelProperty(name = "회사명", example = "회사명", required = true)
    private String Company_Name;
    @ApiModelProperty(name = "구분", example = "구분", required = true)
    private String Sortation;
    @ApiModelProperty(name = "회계년도_기수", example = "21", required = false)
    private String Fiscal_Year_Number;
    @ApiModelProperty(name = "회계년도_날짜", example = "2022-12-12", required = true)
    private Date Fiscal_Year_Date;
    @ApiModelProperty(name = "사업자등록번호", example = "1111111111111", required = true)
    private String Company_Registration_Number;
    @ApiModelProperty(name = "법인등록번호", example = "111111111111111", required = false)
    private String Corporate_Registration_Number;
    @ApiModelProperty(name = "대표자명", example = "홍길동", required = true)
    private String Representatives_Name;
    @ApiModelProperty(name = "주민등록번호", example = "950505-1111111", required = false)
    private String Resident_Registration_Number;
    @ApiModelProperty(name = "본점 우편번호", example = "51111", required = true)
    private String Main_Store_Postal_Code;
    @ApiModelProperty(name = "본점 주소", example = "강남대로 강남강남", required = true)
    private String Main_Store_Address;
    @ApiModelProperty(name = "본점 상세주소", example = "000동 000호", required = false)
    private String Main_Branch_Number;
    @ApiModelProperty(name = "본점 전화번호", example = "02-111-1111", required = false)
    private String Main_Store_Tel;
    @ApiModelProperty(name = "업태", example = "", required = true)
    private String Upstate;
    @ApiModelProperty(name = "종목", example = "생산", required = true)
    private String Industry;
    @ApiModelProperty(name = "설립연월일", example = "2022-02-22", required = false)
    private Date Date_OF_Establishment;
    @ApiModelProperty(name = "사용여부", example = "true", required = false)
    private boolean Whether_To_Use_Status;
}
