<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<%@include file="header.jsp"%>
</head>
<body>
	<div id = "testoutput"></div>
	<form id="table_data_frm">
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td>테이블 명</td>
							<td colspan="1">
								<input type="text" name="tablename" id="tablename" />
							</td>
							<td><input type="button" value="중복확인" onclick="Table_dup_chk_func()"/></td>

						</tr>
						<tr>
							<td>테이블 설명</td>
							<td colspan="2">
								<input type="text" name="tableexplain" id="" />
							</td>
						</tr>
						<tr>
							<td colspan="3"><input type="hidden" name="colnum" id="colnum"  /></td>
						</tr>
						<tr>
							<td>
								<input type="button" id="btn_addcol" onclick="AddCol()" value="추가" />
							</td>
							<td>
								<input type="button" id="btn_addcol" onclick="ClearCol()" value="전체 삭제" />
							</td>
							<td>
								<input type="button" onclick= "Submit()" value="제출" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td>컬럼번호</td>
							<td>컬럼명</td>
							<td>데이터 타입</td>
							<td>빈값</td>
							<td>삭제</td>
						</tr>

					</table>
					<table id="New_table">
					</table>
				</td>
			</tr>
			</form>
</body>
</html>
<script type="text/javascript">

	var lineCount = 0; //현재 입력된 컬럼 갯수
	
	var Table_dup_chk = false;

	$(document).ready(function() {//시작시 세팅
		$("#colnum").val(lineCount);
	});

	//제출
	function Submit(){
		if(Table_dup_chk){
			var formData = $("#table_data_frm").serialize();
			$.ajax({
      			type : "POST",            // HTTP method type(GET, POST) 형식이다.
      			url : "/CreateNewTable",      // 컨트롤러에서 대기중인 URL 주소이다.
      			data : formData,     // Json 형식의 데이터이다.
      			//dataType:'json',
      			success : function(data){ // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
      				//document.getElementById("testoutput").innerHTML= JSON.stringify(data);
      			},
      			error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로   들어옵니다.
          			alert("통신 실패.")
      			}
  			});
		}else{
			alert("테이블 중복 해결하세요");
		}
	}

	
	function Table_dup_chk_func(){
  		// json 형식으로 데이터 set
  		var params = {
  		  name      : $("#tablename").val()
  		}
        
  		// ajax 통신
  		//get인 경우 데이터 없이 url에 get방식으로 넣어준다.
  		$.ajax({
      		type : "POST",            // HTTP method type(GET, POST) 형식이다.
      		url : "/Table_dup_chk",      // 컨트롤러에서 대기중인 URL 주소이다.
      		data : params,     // Json 형식의 데이터이다.
      		dataType:'json',
      		success : function(data){ // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
      			if(JSON.stringify(data.result)!="\"\""){
					Table_dup_chk = false;
					alert("중복됨");
				}else{
					Table_dup_chk = true;
					alert("사용가능");
				}
      		},
      		error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로   들어옵니다.
          	alert("통신 실패.")
      		}
  		});
	}

	function ClearCol() {
		document.getElementById("New_table").innerHTML = "";
		lineCount = 0;
		$("#colnum").val(lineCount);
	}

	function Delete_row(colnum) {
		document.getElementById("colno_" + colnum).remove();

		for (colnum += 1; colnum <= lineCount; colnum++) {
			// alert("colnum:"+colnum+"linecount:"+lineCount);
			//컬럼번호 앞으로 당기기
			document.getElementById("colno_" + colnum).setAttribute("id",
					"colno_" + (colnum - 1));
			document.getElementById("col_view_no_" + colnum).setAttribute("id",
					"col_view_no_" + (colnum - 1));
			document.getElementById("col_view_no_" + (colnum - 1)).innerHTML = colnum - 1;
			//컬럼삭제버튼 번호에 맞게 수정
			document.getElementById("col_remove_" + colnum).setAttribute("id",
					"col_remove_" + (colnum - 1));
			document.getElementById("col_remove_" + (colnum - 1)).setAttribute(
					"onclick", "Delete_row(" + (colnum - 1) + ")");
			document.getElementById("col_remove_" + (colnum - 1)).setAttribute(
					"value", "삭제" + (colnum - 1));
		}

		lineCount--;
		$("#colnum").val(lineCount);
	}

	function AddCol() { //컬럼 추가 기능

		//namelist: colname_1,type_1,NN_1,PK_1,UQ_1
		lineCount++;
		var table = document.getElementById("New_table");
		let row = document.createElement('tr');
		row.setAttribute("id", "colno_" + lineCount);
		//번호
		let row_td1 = document.createElement('td');
		row_td1.setAttribute("id", "col_view_no_" + lineCount);
		row_td1.innerHTML = lineCount;
		row.appendChild(row_td1);


		//콜롬명 입력칸
		let row_td2 = document.createElement('td');
		var row_td2_input = document.createElement('input');
		row_td2_input.setAttribute("type", "text");
		row_td2_input.setAttribute("placeholder", "컬럼명");
		row_td2_input.name = "colname_" + lineCount;

		row_td2.appendChild(row_td2_input);
		row.appendChild(row_td2);

		//데이터타입
		var typearr = [ "숫자", "문자" ];
		let row_td3 = document.createElement('td');
		var row_td3_select = document.createElement('select');
		row_td3_select.name = "type_" + lineCount;
		for (var i = 0; i < typearr.length; i++) {
			var option = document.createElement("option");
			option.value = typearr[i];
			option.text = typearr[i];
			row_td3_select.appendChild(option);
		}
		row_td3.appendChild(row_td3_select);
		row.appendChild(row_td3);

		//nn
		var typearr = [ "허용", "미허용" ];
		let row_td4 = document.createElement('td');
		var row_td4_select = document.createElement('select');
		row_td4_select.name = "NN_" + lineCount;
		for (var i = 0; i < typearr.length; i++) {
			var option = document.createElement("option");
			option.value = typearr[i];
			option.text = typearr[i];
			row_td4_select.appendChild(option);
		}
		row_td4.appendChild(row_td4_select);
		row.appendChild(row_td4);

		//삭제
		let row_td5 = document.createElement('td');
		var row_td5_input = document.createElement('input');
		row_td5_input.setAttribute("id", "col_remove_" + lineCount);
		row_td5_input.setAttribute("type", "button");
		row_td5_input.setAttribute("value", "삭제");
		row_td5_input.setAttribute("onclick", "Delete_row(" + lineCount + ")");

		row_td5.appendChild(row_td5_input);
		row.appendChild(row_td5);

		//테이블 완성
		table.appendChild(row);
		$("#colnum").val(lineCount);
	}
</script>