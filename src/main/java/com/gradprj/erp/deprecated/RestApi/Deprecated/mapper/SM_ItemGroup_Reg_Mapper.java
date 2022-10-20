package com.gradprj.erp.deprecated.RestApi.Deprecated.mapper;


import com.gradprj.erp.deprecated.RestApi.Deprecated.domain.SM_ItemGroup_Reg;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SM_ItemGroup_Reg_Mapper {

    @Select("SELECT * FROM SM_ItemGroup_Reg")
    List<SM_ItemGroup_Reg> findAll();

    @Select("SELECT * FROM SM_ItemGroup_Reg WHERE ItemGroup_Code = #{ItemGroup_Code}")
    SM_ItemGroup_Reg findByItemGroup_Code(String ItemGroup_Code);

    @Delete("DELETE FROM SM_ItemGroup_Reg WHERE ItemGroup_Code = #{ItemGroup_Code}")
    void delete(String ItemGroup_Code);

    @Insert("INSERT INTO SM_ItemGroup_Reg " +
            "(ItemGroup_Code, ItemGroup_Name, Use_Status,Explanation) " +
            "VALUES " +
            "(NewCode('Ig'), #{ItemGroup_Name}, #{Use_Status},#{Explanation})"
    )
    void save(SM_ItemGroup_Reg sm_itemgroup_reg);

    @Update("UPDATE SET " +
            "ItemGroup_Name = #{ItemGroup_Name}, " +
            "Use_Status = #{Use_Status}, " +
            "Explanation = #{Explanation} " +
            "WHERE ItemGroup_Code = #{ItemGroup_Code}"
    )
    void update(SM_ItemGroup_Reg sm_itemgroup_reg);
}
