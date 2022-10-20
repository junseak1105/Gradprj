package com.gradprj.erp.deprecated.RestApi.Deprecated.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SM_Zipcode {
    @ApiModelProperty(name = "우편번호", example = "12345", required = true)
    private String ZipCode;
    @ApiModelProperty(name = "도로명주소", example = "강남대로", required = true)
    private String Road_Address;
    @ApiModelProperty(name = "지번주소", example = "강남구", required = false)
    private String Lot_Address;
}
