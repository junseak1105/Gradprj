package com.gradprj.erp.RestApi.mapper;

import com.gradprj.erp.RestApi.domain.SM_Company_Reg;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SM_Company_Reg_Mapper {

    @Select("SELECT * FROM SM_CRI_Com_Reg")
    List<SM_Company_Reg> findAll();

    @Select("SELECT * FROM SM_CRI_Com_Reg WHERE Com_Reg_Num = #{Company_Code}")
    SM_Company_Reg findByCom_Reg_Num(String Company_Code);

    @Delete("DELETE FROM SM_CRI_Com_Reg WHERE Com_Reg_Num = #{Company_Code}")
    void delete(String Company_Code);

    @Insert("INSERT INTO SM_CRI_Com_Reg(" +
            "Company_Code," +
            "Company_Name," +
            "Sortation," +
            "Fiscal_Year_Number," +
            "Fiscal_Year_Date," +
            "Company_Registration_Number," +
            "Corporate_Registration_Number," +
            "Representatives_Name," +
            "Resident_Registration_Number," +
            "Main_Store_Postal_Code," +
            "Main_Store_Address," +
            "Main_Branch_Number," +
            "Main_Store_Tel," +
            "Upstate," +
            "Industry," +
            "Date_OF_Establishment," +
            "Whether_To_Use_Status" +
            ") values(NewCode()," +
            "#{Company_Name}, " +
            "#{Sortation}, " +
            "#{Fiscal_Year_Number}, " +
            "#{Fiscal_Year_Date}, " +
            "#{Company_Registration_Number}, " +
            "#{Corporate_Registration_Number}, " +
            "#{Representatives_Name}, " +
            "#{Resident_Registration_Number}, " +
            "#{Main_Store_Postal_Code}, " +
            "#{Main_Store_Address}, " +
            "#{Main_Branch_Number}, " +
            "#{Main_Store_Tel}, " +
            "#{Upstate}, " +
            "#{Industry}, " +
            "#{Date_OF_Establishment}, " +
            "#{Whether_To_Use_Status} )"
    )
    void save(SM_Company_Reg sm_company_reg);

    @Update("UPDATE SM_CRI_Com_Reg SET " +
            "Company_Name = #{Company_Name}, " +
            "Sortation = #{Sortation}, " +
            "Fiscal_Year_Number = #{Fiscal_Year_Number}, " +
            "Fiscal_Year_Date = #{Fiscal_Year_Date}, " +
            "Company_Registration_Number = #{Company_Registration_Number}, " +
            "Corporate_Registration_Number = #{Corporate_Registration_Number}, " +
            "Representatives_Name = #{Representatives_Name}, " +
            "Resident_Registration_Number = #{Resident_Registration_Number}, " +
            "Main_Store_Postal_Code = #{Main_Store_Postal_Code}, " +
            "Main_Store_Address = #{Main_Store_Address}, " +
            "Main_Branch_Number = #{Main_Branch_Number}, " +
            "Main_Store_Tel = #{Main_Store_Tel}, " +
            "Upstate = #{Upstate}, " +
            "Industry = #{Industry}, " +
            "Date_OF_Establishment = #{Date_OF_Establishment}, " +
            "Whether_To_Use_Status = #{Whether_To_Use_Status} " +
            "WHERE Company_Code = #{Company_Code}"
    )
    void update(SM_Company_Reg sm_company_reg);

}
