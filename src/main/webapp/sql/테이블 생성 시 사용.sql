drop table if exists sm_cri_print_list;
create table sm_cri_print_list
(
    print_name varchar(50) not null comment '서식 명' primary key ,
    print_head varchar(50) not null comment '상부 서식' ,
    print_foot varchar(100) not null comment '하부 서식'
)comment '출력서식 등록';

select * from sm_cri_print_list;
call get_table_desc('sm_cri_print_list');
call get_table_info('sm_cri_print_list');

insert into sm_cri_print_list values('테스트1','상부 서식','하부 서식');
# select * from sm_cri_print_list;
# select * from sm_cri_page_list;
insert into sm_cri_table_list value ('sm_cri_print_list','출력서식 등록');
insert into sm_cri_page_list value('출력서식 등록','sm_cri_print_list','기초관리',1,NewCode('Pg'),'출력서식생성기','테스트1');

#코드 있으면 사용
# select * from sm_cri_codedlist_reg;
insert into sm_cri_codedlist_reg value('PgA','Addi_Page_Code','sm_cri_additional_page_list');

select * from SM_CRI_ADDI_PAGE_LIST;