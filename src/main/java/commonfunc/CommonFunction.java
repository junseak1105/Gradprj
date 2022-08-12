package commonfunc;

public class CommonFunction implements commonfunc {
	@Override
	public boolean Valueisdigit(String value) {
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
}
