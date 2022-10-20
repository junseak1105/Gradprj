package com.gradprj.erp.deprecated.RestApi.Deprecated.mapper;

import com.gradprj.erp.deprecated.RestApi.Deprecated.domain.SM_Zipcode;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SM_Zipcode_Mapper {

    @Select("SELECT * FROM SM_Zipcode")
    List<SM_Zipcode> findAll();

    @Select("SELECT * FROM SM_Zipcode WHERE ZipCode = #{ZipCode}")
    SM_Zipcode findByZipCode(String ZipCode);

    @Insert("INSERT INTO SM_Zipcode (ZipCode, Road_Address, Lot_Address) VALUES (#{ZipCode}, #{Road_Address}, #{Lot_Address})")
    void save(SM_Zipcode sm_zipcode);

    @Update("UPDATE SM_Zipcode SET Road_Address = #{Road_Address}, Lot_Address = #{Lot_Address} WHERE ZipCode = #{ZipCode}")
    void update(SM_Zipcode sm_zipcode);

    @Delete("DELETE FROM SM_Zipcode WHERE ZipCode = #{ZipCode}")
    void delete(String ZipCode);
}
