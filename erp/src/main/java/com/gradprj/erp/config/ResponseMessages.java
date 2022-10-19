package com.gradprj.erp.config;

public class ResponseMessages {

    /**
     * 경로오류
     */

    public static final String BAD_REQUEST = "잘못된 요청입니다.";
    /**
     * 데이터베이스 기본 응답 메세지
     */
    public static final String DB_ERROR = "데이터베이스 에러";

    /**
     * DB 관련 응답 메세지
     */
    public static final String Data_Empty = "해당 데이터가 존재하지 않습니다.";
    public static final String Data_found = "데이터 조회 성공";
    public static final String Data_saved = "데이터 저장 성공";
    public static final String Data_updated = "데이터 수정 성공";
    public static final String Data_deleted = "데이터 삭제 성공";

    public static final String Data_duplicated ="중복된 데이터가 존재합니다.";


}