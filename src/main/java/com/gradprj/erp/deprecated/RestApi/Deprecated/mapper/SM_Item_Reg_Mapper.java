package com.gradprj.erp.deprecated.RestApi.Deprecated.mapper;


import com.gradprj.erp.deprecated.RestApi.Deprecated.domain.SM_Item_Reg;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SM_Item_Reg_Mapper {

    @Select("SELECT * FROM SM_Item_Reg")
    List<SM_Item_Reg> findAll();

    @Select("SELECT * FROM SM_Item_Reg WHERE Item_Code = #{Item_Code}")
    SM_Item_Reg findByItemCode(String Item_Code);

    @Delete("DELETE FROM SM_Item_Reg WHERE Item_Code = #{Item_Code}")
    void delete(String Item_Code);

    @Insert("INSERT INTO SM_Item_Reg " + "(" +
            "Item_Code,Item_Name,Standard,Inventory_Unit,Whether_LOT,SET_Item,Inspection_Status,Use_Status,LOT_Quantity" +
            "Drawing_Number, Hs_Code, Width ,Length, Height, Cost, Item_Group_Code, Daily_production, Notes" +
            ") " +
            "VALUES (" +
            "NewCode('It'), " +
            "#{Item_Name}, " +
            "#{Standard}, " +
            "#{Inventory_Unit}, " +
            "#{Whether_LOT}, " +
            "#{SET_Item}, " +
            "#{Inspection_Status}, " +
            "#{Use_Status}, " +
            "#{LOT_Quantity}, " +
            "#{Drawing_Number}, " +
            "#{Hs_Code}, " +
            "#{Width}, " +
            "#{Length}, " +
            "#{Height}, " +
            "#{Cost}, " +
            "#{Item_Group_Code}, " +
            "#{Daily_production}, " +
            "#{Notes}" +
            ")"
    )
    void save(SM_Item_Reg sm_item_reg);

    @Update(
            "UPDATE SM_ITEM_REG SET " +
            "Item_Name = #{Item_Name}, " +
            "Standard = #{Standard}, " +
            "Inventory_Unit = #{Inventory_Unit}, " +
            "Whether_LOT = #{Whether_LOT}, " +
            "SET_Item = #{SET_Item}, " +
            "Inspection_Status = #{Inspection_Status}, " +
            "Use_Status = #{Use_Status}, " +
            "LOT_Quantity = #{LOT_Quantity}, " +
            "Drawing_Number = #{Drawing_Number}, " +
            "Hs_Code = #{Hs_Code}, " +
            "Width = #{Width}, " +
            "Length = #{Length}, " +
            "Height = #{Height}, " +
            "Cost = #{Cost}, " +
            "Item_Group_Code = #{Item_Group_Code}, " +
            "Daily_production = #{Daily_production}, " +
            "Notes = #{Notes} " +
            "WHERE Item_Code = #{Item_Code}"
    )
    void update(SM_Item_Reg sm_item_reg);


}
