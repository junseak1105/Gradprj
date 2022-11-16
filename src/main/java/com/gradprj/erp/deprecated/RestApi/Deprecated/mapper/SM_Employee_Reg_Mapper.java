package com.gradprj.erp.deprecated.RestApi.Deprecated.mapper;

import com.gradprj.erp.deprecated.RestApi.Deprecated.domain.SM_Employee_Reg;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SM_Employee_Reg_Mapper {

    @Select("SELECT * FROM SM_Employee_Reg")
    List<SM_Employee_Reg> findAll();

    @Select("SELECT * FROM SM_Employee_Reg WHERE Employee_Code = #{Employee_Code}")
    SM_Employee_Reg findByEmployee_Code(String Employee_Code);

    @Delete("DELETE FROM SM_Employee_Reg WHERE Employee_Code = #{Employee_Code}")
    void delete(String Employee_Code);

    @Insert("INSERT INTO SM_Employee_Reg " +
            "(Employee_Code, Employee_Name, Department_Code,Department_Name,JoinDate,Resignation_Date,Emergency_Contact_Network) " +
            "VALUES " +
            "(NewCode('Em'), #{Employee_Name}, #{Department_Code},#{Department_Name},#{JoinDate},#{Resignation_Date},#{Emergency_Contact_Network})"
    )
    void save(SM_Employee_Reg sm_employee_reg);

    @Update("UPDATE SET " +
            "Employee_Name = #{Employee_Name}, " +
            "Department_Code = #{Department_Code}, " +
            "Department_Name = #{Department_Name}, " +
            "JoinDate = #{JoinDate}, " +
            "Resignation_Date = #{Resignation_Date}, " +
            "Emergency_Contact_Network = #{Emergency_Contact_Network} " +
            "WHERE Employee_Code = #{Employee_Code}")
    void update(SM_Employee_Reg sm_employee_reg);
}
