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