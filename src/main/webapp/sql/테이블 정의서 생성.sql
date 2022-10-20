
drop procedure if exists create_table_desc;
create procedure `get_table_desc`
(
    in table_name varchar(100)
)
begin
select
    x.ORDINAL_POSITION 'COLUMN_ID',
        x.COLUMN_NAME 'Name',
        x.COLUMN_TYPE 'Type(Length)',
        IF(x.COLUMN_KEY = 'PRI', 'PK',
            if(x.COLUMN_KEY ='UNI','UQ',
               IF(x.COLUMN_KEY ='MUL','FK','')
                )
            )'Index',
        x.IS_NULLABLE 'NULLABLE',
        x.COLUMN_DEFAULT 'DATA_DEFAULT',
        x.COLUMN_COMMENT '설명'
from information_schema.COLUMNS x
         left join information_schema.STATISTICS y
                   on x.TABLE_NAME = y.TABLE_NAME and x.COLUMN_NAME = y.COLUMN_NAME
where x.TABLE_SCHEMA = 'erp'
  and x.TABLE_NAME = table_name
order by x.TABLE_NAME, x.ORDINAL_POSITION;
end

call get_table_desc('sm_company_reg');