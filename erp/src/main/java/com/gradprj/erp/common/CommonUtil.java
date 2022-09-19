package com.gradprj.erp.common;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil  {

    /**
     * 문자열 숫자여부 확인
     * @param value
     * @return boolean
     */
	public static boolean Valueisdigit(String value) {
		boolean output = true;
		for (int i = 0; i < value.length(); i++) { // 입력받은 문자열인 input의 길이만큼 반복문 진행(배열이 아닌 문자열의 길이기 때문에 length가 아닌
													// length()를 사용해야한다.)
			char tmp = value.charAt(i); // 한글자씩 검사하기 위해서 char형 변수인 tmp에 임시저장
			if (Character.isDigit(tmp) == false) { // 문자열이 숫자가 아닐 경우
				output = false; // output의 값을 false로 바꿈
			}
		}
		return output;
	}


    /**
     * 파라미터 null 체크 후 반환
     * 사용법 String str = CommonUtil.Param_IsNull(request,"파라미터명");
     * @param input
     * @return "" or input
     */
	public static String IsNull(String input) {
		if (input != null) {
			return input;
		}
		return "";
	}

    /**
     * json 파라미터 key value 출력
     * @param json
     */
	public static void JsonPrint(JSONObject json) {
        json.keySet().forEach(keyStr ->
        {
            Object keyvalue = json.get(keyStr);
            System.out.println("key: " + keyStr + " value: " + keyvalue);
        });
    }

}
