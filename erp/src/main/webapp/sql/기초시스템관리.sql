drop table if exists SM_ZipCode;
create table SM_ZipCode
(
    ZipCode      varchar(12) not null primary key,
    Road_Address varchar(60) not null,
    Lot_Address  varchar(60) null
) comment '우편번호';

drop table if exists SM_Company_Reg;
create table erp.SM_Company_Reg
(
    Company_Code                  varchar(12) not null comment '회사 코드',
    Company_Name                  varchar(50) not null comment '회사명',
    Sortation                     varchar(10) not null comment '구분',
    Fiscal_Year_Number            varchar(3)  null comment '회계년도_기수',
    Fiscal_Year_Date              date        not null comment '회계년도_날짜',
    Company_Registration_Number   varchar(13) not null comment '사업자등록번호',
    Corporate_Registration_Number varchar(15) null comment '법인등록번호',
    Representatives_Name          varchar(20) not null comment '대표자성명',
    Resident_Registration_Number  varchar(15) null comment '주민등록번호',
    Main_Store_Postal_Code        varchar(5)  not null comment '본점우편번호',
    Main_Store_Address            varchar(60) not null comment '본점주소',
    Main_Branch_Number            varchar(60) null comment '본점번지',
    Main_Store_Tel                varchar(13) null comment '본점전화번호',
    Upstate                       varchar(20) not null comment '업태',
    Industry                      varchar(20) not null comment '종목',
    Date_OF_Establishment         date        null comment '설립연월일',
    Whether_To_Use_Status         boolean     null default true comment '사용여부',
    constraint SM_CRI_Com_Reg_pk
        primary key (Company_Code),
    constraint SM_CRI_Com_Reg_uq1
        unique (Company_Name),
    CONSTRAINT SM_CRI_COM_Reg_uq2
        UNIQUE (Company_Registration_Number),
    CONSTRAINT SM_CRI_COM_Reg_uq3
        UNIQUE (Corporate_Registration_Number),
    CONSTRAINT SM_CRI_COM_Reg_uq4
        UNIQUE (Representatives_Name),
    CONSTRAINT SM_CRI_COM_REG_fk1
        FOREIGN KEY (Main_Store_Postal_Code)
            REFERENCES erp.sm_zipcode (ZipCode)
) comment '회사 등록';

drop table if exists SM_Workplace_Reg;
create table SM_Workplace_Reg
(
    Workplace_Code              varchar(12) not null primary key comment '사업장 코드',
    Workplace_Name              varchar(50) not null unique key comment '사업장 명',
    Company_Registration_Number varchar(13) not null unique key comment '사업자 등록 번호',
    Corporate_Registration      varchar(15) null comment '법인 등록 번호',
    Representatives_Name        varchar(50) not null comment '대표자 명',
    Workplace_Zipcode           varchar(5)  null comment '사업장 우편번호',
    Workplace_Address           varchar(60) not null comment '사업장 주소',
    Workplace_Number            varchar(60) null comment '사업장 번지',
    Workplace_Tel               varchar(13) null comment '사업장 전화번호',
    Upstate                     varchar(20) not null comment '업태',
    Industry                    varchar(20) not null comment '종목',
    Main_Store_Status           boolean     null comment '본점 여부',
    CONSTRAINT SM_CRI_Wor_Reg_fk1
        FOREIGN KEY (Workplace_Zipcode)
            REFERENCES erp.sm_zipcode (ZipCode)
) comment '사업장 등록';

drop table if exists SM_Sector_Reg;
create table SM_Sector_Reg
(
    Section_Code varchar(12) not null primary key comment '부문 코드',
    Section_Name varchar(50) not null unique key comment '부문 명',
    UseDate      date        not null comment '사용기간 시작'
) comment '부문 등록';

drop table if exists SM_Department_Reg;
create table SM_Department_Reg
(
    Department_Code varchar(12) not null primary key comment '부서 코드',
    Department_Name varchar(50) not null unique key comment '부서 명',
    Workplace_Code  varchar(12) not null comment '사업장 코드',
    Workplace_Name  varchar(50) not null comment '사업장 명',
    Sector_Code     varchar(12) not null comment '부문 코드',
    Sector_Name     varchar(50) not null comment '부문 명',
    UseDate         date        not null comment '사용기간 시작',
    CONSTRAINT SM_CRI_Dep_Reg_fk1
        FOREIGN KEY (Workplace_Code)
            REFERENCES erp.sm_workplace_reg (Workplace_Code),
    CONSTRAINT SM_CRI_Dep_Reg_fk2
        FOREIGN KEY (Sector_Code)
            REFERENCES erp.sm_sector_reg (Section_Code)
) comment '부서 등록';

drop table if exists SM_Employee_Reg;
create table SM_Employee_Reg
(
    Employee_Code             varchar(12) not null primary key comment '사원 코드',
    Employee_Name             varchar(50) not null comment '사원 명',
    Department_Code           varchar(12) not null comment '부서 코드',
    Department_Name           varchar(50) not null comment '부서 명',
    JoinDate                  date        not null comment '입사일',
    Resignation_Date          date        null comment '퇴사일',
    Emergency_Contact_Network int(13)     null comment '비상연락망',
    CONSTRAINT SM_CRI_Emp_Reg_fk1
        FOREIGN KEY (Department_Code)
            REFERENCES erp.sm_department_reg (Department_Code)
) comment '사원 등록';


drop table if exists SM_Sys_Env_set;
create table SM_Sys_Env_set
(
    Division                 varchar(10)   null comment '구분',
    Code                     varchar(12)   not null primary key comment '코드',
    Environment_Element_Name varchar(50)   not null comment '환경요소명',
    Type_Classification      varchar(30)   not null comment '유형구분',
    Type_Settings            varchar(2)    not null comment '유형설정',
    Selection_Range          varchar(100)  not null comment '선택범위',
    Note                     varchar(1000) null comment '비고'
) comment '시스템 환경설정';

drop table if exists SM_GeneralCustomer_Reg;
create table SM_GeneralCustomer_Reg
(
    General_Customer_Code          varchar(12) not null primary key,
    General_Customer_Name          varchar(50) not null unique,
    General_Customer_Division      varchar(10) not null,
    Company_Registration_Number    varchar(13) not null unique,
    Resident_Registration_Number   varchar(15),
    Representativs_Name            varchar(50) not null,
    Upstate                        varchar(20),
    Industry                       varchar(20),
    ZipCode                        varchar(12),
    Workplace_Address              varchar(60) not null,
    General_Customer_Tel           varchar(13),
    Wholesale_Retail_Business_Code varchar(12),
    CONSTRAINT SM_BIM_Gen_Cus_Reg_fk1
        FOREIGN KEY (ZipCode)
            REFERENCES erp.sm_zipcode (ZipCode)
) comment '일반거래처 등록';

drop table if exists SM_Item_Reg;
create table SM_Item_Reg
(
    Item_Code         varchar(12) not null primary key comment '품목 코드',
    Item_Name         varchar(50) not null unique key comment '품목 명',
    Standard          varchar(20) comment '규격',
    Inventory_Unit    varchar(5) default 'EA' comment '재고단위',
    Whether_LOT       boolean    default false comment 'LOT 여부',
    SET_Item          int(10) comment 'SET 품목',
    Inspection_Status boolean    default false comment '검사여부',
    Use_Status        boolean    default false comment '사용여부',
    LOT_Quantity      int(5) comment 'LOT 수량',
    Drawing_Number    varchar(5) comment '도면번호',
    Hs_Code           varchar(12) comment 'HS코드',
    Width             varchar(100) comment '폭',
    Length            varchar(100) comment '길이',
    Height            varchar(100) comment '높이',
    Cost              int(15) comment '단가',
    Item_Group_Code   varchar(12) comment '품목 그룹 코드',
    Daily_production  int(5)      not null comment '일생산량',
    Notes             varchar(1000) comment '비고',
    CONSTRAINT SM_BIM_Ite_Reg_fk1
        FOREIGN KEY (Item_Group_Code)
            REFERENCES erp.SM_ItemGroup_Reg (Item_Group_Code)
) comment '품목등록';


drop table if exists SM_ItemGroup_Reg;
create table SM_ItemGroup_Reg
(
    Item_Group_Code varchar(12) not null primary key comment '품목 그룹 코드',
    Item_Group_Name varchar(50) not null comment '품목 그룹 명',
    Use_Status      boolean default true comment '사용여부',
    Explanation     varchar(200) comment '설명'
) comment '품목군 등록';


drop table if exists SM_House_Reg;
create table SM_House_Reg
(
    House_Code         varchar(12) not null primary key comment '창고 코드',
    House_Name         varchar(50) not null unique comment '창고 명',
    House_Location_IN  varchar(50) comment '입고기본위치',
    House_Location_OUT varchar(50) comment '출고기본위치',
    House_Explanation  varchar(200) comment '창고설명',
    House_Status       boolean default true comment '사용여부'
) comment '창고등록';
