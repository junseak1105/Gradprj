package com.gradprj.erp.deprecated.RestApi.Deprecated.mapper;


import com.gradprj.erp.deprecated.RestApi.Deprecated.domain.SM_Department_Reg;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SM_Department_Reg_Mapper {

    @Select("SELECT * FROM SM_Department_Reg")
    List<SM_Department_Reg> findAll();

    @Select("SELECT * FROM SM_Department_Reg WHERE Department_Code = #{Department_Code}")
    SM_Department_Reg findByDepartment_Code(String Department_Code);

    @Delete("DELETE FROM SM_Department_Reg WHERE Department_Code = #{Department_Code}")
    void delete(String Department_Code);

    @Insert("")
    void save(SM_Department_Reg sm_department_reg);

    @Update("")
    void update(SM_Department_Reg sm_department_reg);

}
