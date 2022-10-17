drop function if exists NewCode;
create function NewCode(V varchar(2)) returns varchar(12) deterministic

begin
    declare vCode varchar(12);
    declare vCodeNumber int;
    -- Code 구성:
    -- CompanyCode = 'Co' + 10자리 숫자
    -- EmployeeCode = 'Em' + 10자리 숫자
    -- DepartmentCode = 'De' + 10자리 숫자
    -- ItemCode = 'It' + 10자리 숫자
    -- ItemGroup = 'Ig' + 10자리 숫자
    -- HouseCode = 'Ho' + 10자리 숫자
    if V = 'Co' then
        select substr(max(company_code),3,10) into vCodeNumber from sm_company_reg;
    elseif V = 'Em' then
        select substr(max(employee_code),3,10) into vCodeNumber from sm_employee_reg;
    elseif V = 'De' then
        select substr(max(department_code),3,10) into vCodeNumber from sm_department_reg;
    elseif V = 'It' then
        select substr(max(item_code),3,10) into vCodeNumber from sm_item_reg;
    elseif V = 'Ig' then
        select substr(max(item_group_code),3,10) into vCodeNumber from sm_itemgroup_reg;
    elseif V = 'Ho' then
        select substr(max(house_code),3,10) into vCodeNumber from sm_house_reg;
    end if;
    if vCodeNumber is null then
        set vCodeNumber = 0;
    end if;

    set vCodeNumber = vCodeNumber + 1;
    set vCode = concat(V,lpad(vCodeNumber,10,'0'));

    return vCode;
end;

select NewCode('It') as NewCode;