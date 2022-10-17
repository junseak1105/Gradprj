package com.gradprj.erp.RestApi.domain;

import lombok.Data;

@Data
public class SM_Item_Reg {
    private String Item_Code;
    private String Item_Name;
    private String Standard;
    private String Inventory_Unit;
    private boolean Whether_LOT;
    private int SET_Item;
    private String Inspection_Status;
    private String Use_Status;
    private int LOT_Quantity;
    private String Drawing_Number;
    private String Hs_Code;
    private String Width;
    private String Length;
    private String Height;
    private int Cost;
    private String Item_Group_Code;
    private int Daily_production;
    private String Notes;
}
