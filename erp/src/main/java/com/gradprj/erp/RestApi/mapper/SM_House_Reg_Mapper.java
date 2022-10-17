package com.gradprj.erp.RestApi.mapper;

import com.gradprj.erp.RestApi.domain.SM_House_Reg;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SM_House_Reg_Mapper {

    @Select("SELECT * FROM SM_House_Reg")
    List<SM_House_Reg> findAll();

    @Select("SELECT * FROM SM_House_Reg WHERE House_Code = #{House_Code}")
    SM_House_Reg findByHouse_Code(String House_Code);

    @Delete("DELETE FROM SM_House_Reg WHERE House_Code = #{House_Code}")
    void delete(String House_Code);

    @Insert("INSERT INTO SM_House_Reg(" +
            "House_Code," +
            "House_Name," +
            "House_Location_IN," +
            "House_Location_OUT," +
            "House_Explanation," +
            "House_Status" +
            ") values(NewCode('Ho')," +
            "#{House_Name}, " +
            "#{House_Location_IN}, " +
            "#{House_Location_OUT}, " +
            "#{House_Explanation}, " +
            "#{House_Status} )"
    )
    void save(SM_House_Reg sm_house_reg);

    @Update("UPDATE SM_House_Reg SET " +
            "House_Name = #{House_Name}, " +
            "House_Location_IN = #{House_Location_IN}, " +
            "House_Location_OUT = #{House_Location_OUT}, " +
            "House_Explanation = #{House_Explanation}, " +
            "House_Status = #{House_Status} " +
            "WHERE House_Code = #{House_Code}"
    )
    void update(SM_House_Reg sm_house_reg);
}
