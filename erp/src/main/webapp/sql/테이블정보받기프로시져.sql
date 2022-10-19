drop procedure get_table_info;

CREATE PROCEDURE `get_table_info`(
    p_table_name varchar(30)
)
BEGIN
    select col.column_name as Column_Name,
           col.DATA_TYPE as Data_Type,
           col.CHARACTER_MAXIMUM_LENGTH as Data_Length,
           col.COLUMN_KEY as Column_Key,
           col.COLUMN_COMMENT as Column_Comment,
           col.IS_NULLABLE as Is_Nullable,
           keyset.REFERENCED_TABLE_NAME as Ref_Table
    from information_schema.columns col
             left join information_schema.KEY_COLUMN_USAGE keyset
                       on col.COLUMN_NAME = keyset.COLUMN_NAME and col.TABLE_NAME = keyset.TABLE_NAME
    where col.TABLE_NAME = p_table_name;
END;

call get_table_info('SM_department_reg');


drop procedure if exists getKeyColumn;
CREATE PROCEDURE `getKeyColumn`(
    p_table_name varchar(30)
)
BEGIN
    select col.column_name as KeyColumn
    from information_schema.columns col
             left join information_schema.KEY_COLUMN_USAGE keyset
                       on col.COLUMN_NAME = keyset.COLUMN_NAME and col.TABLE_NAME = keyset.TABLE_NAME
    where col.TABLE_NAME = p_table_name and COLUMN_KEY = 'PRI';
END;

call getKeyColumn('SM_cri_com_reg');

drop procedure if exists getTableName;
CREATE PROCEDURE `getTableName`(
    p_page_name varchar(30)
)
BEGIN
    select use_table as tablename from sm_cri_page_list where page_name = p_page_name;
END;

call getTableName('Set품 관리');