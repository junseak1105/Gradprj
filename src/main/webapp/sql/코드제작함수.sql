drop function if exists NewCode;
create function NewCode(V varchar(5)) returns varchar(12)
    deterministic
begin
    declare vCode varchar(12);
    declare vCodeLength int;
    declare vCodeNumber int;
    set vCodeLength = LENGTH(V);

    select seq into vCodeNumber
    from sm_cri_codedlist_reg
    where sm_cri_codedlist_reg.Code = V;

    set vCodeNumber = vCodeNumber + 1;
    set vCode = concat(V, lpad(vCodeNumber, 12 - vCodeLength, '0'));
    return vCode;
end;

select NewCode('Pg');


# drop function if exists NewCode;
# create function NewCode(V varchar(5)) returns varchar(12)
#     deterministic
#
# begin
#     declare vCode varchar(12);
#     declare vCodeLength int;
#     declare vCodeNumber int;
#
#     set vCodeLength = LENGTH(V);
#     -- Code 구성:
#     -- Company_Code = 'Co' + 10자리 숫자
#     -- Employee_Code = 'Em' + 10자리 숫자
#     -- Department_Code = 'De' + 10자리 숫자
#     -- Item_Code = 'It' + 10자리 숫자
#     -- Item_Group_Code = 'Ig' + 10자리 숫자
#     -- House_Code = 'Ho' + 10자리 숫자
#     -- Page_Code = 'Pg' + 10자리 숫자
#     -- Reg_Code = 'Ic' + 10자리 숫자
#     -- Location_Code = 'Lo' + 10자리 숫자
#     -- WorkPlace_Code = 'Wp' + 10자리 숫자
#     -- WorkPlace_Code = 'WpO' + 10자리 숫자
#     if V = 'Co' then
#         select substr(max(company_code), vCodeLength + 1, 10) into vCodeNumber from sm_cri_com_reg;
#     elseif V = 'Em' then
#         select substr(max(employee_code), vCodeLength + 1, 10) into vCodeNumber from sm_cri_emp_reg;
#     elseif V = 'De' then
#         select substr(max(department_code), vCodeLength + 1, 10) into vCodeNumber from sm_cri_dep_reg;
#     elseif V = 'It' then
#         select substr(max(item_code), vCodeLength + 1, 10) into vCodeNumber from sm_bim_ite_reg;
#     elseif V = 'Ig' then
#         select substr(max(item_group_code), vCodeLength + 1, 10) into vCodeNumber from sm_bim_ite_gro_reg;
#     elseif V = 'Ho' then
#         select substr(max(house_code), vCodeLength + 1, 10) into vCodeNumber from sm_bim_hou_reg;
#     elseif V = 'Pg' then
#         select substr(max(page_code), vCodeLength + 1, 10) into vCodeNumber from sm_cri_page_list;
#     elseif V = 'Ic' then
#         select substr(max(Reg_Code), vCodeLength + 1, 10) into vCodeNumber from SM_out_itm_cus_in;
#     elseif V = 'Lo' then
#         select substr(max(location_code), vCodeLength + 1, 10) into vCodeNumber from SM_tmp_place_Reg;
#     elseif V = 'Wp' then
#         select substr(max(workplace_code), vCodeLength + 1, 10) into vCodeNumber from sm_process_workplace_reg;
#     elseif V = 'WpO' then
#         select substr(max(workplace_code), vCodeLength + 1, 10) into vCodeNumber from SM_Outsource_Workplace_Reg;
#     end if;
#     if vCodeNumber is null then
#         set vCodeNumber = 0;
#     end if;
#     set vCodeNumber = vCodeNumber + 1;
#     set vCode = concat(V, lpad(vCodeNumber, 12 - vCodeLength, '0'));
#
#     return vCode;
# end;
#
#
# # select ifnull(selectData('sm_cri_com_reg','company_code','Co'),0);

