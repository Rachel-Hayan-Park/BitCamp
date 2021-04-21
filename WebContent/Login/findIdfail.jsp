<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디찾기</title>
<script>
	alert("존재하지 않는 아이디거나 전화번호입니다.");
	function checkInfo() {
		var name = document.getElementById("txtName").value;
		var phonnum = document.getElementById("txtPhone").value;
		if (name.length < 1) {
			alert("이름을 입력해주세요");
			document.getElementById("txtName").focus();
			return false;
		}
		if (phonnum.length < 11) {
			alert("핸드폰번호를 정확히 입력해주세요");
			document.getElementById("txtPhone").focus();
			return false;
		}
		frm.submit();
	}
</script>
</head>
<body>
	<h2>아이디찾기</h2>
	<form action="loginController?type=findId_Ok" method="post" name="frm">
		이름 <input type="text" id="txtName" name="name"/><br/>
		전화번호 <input type="text" id="txtPhone" value="01012345678" name="phonnum"/> <br/>
		<input type="button" value="아이디찾기" onclick="checkInfo()">
	</form>
</body>
</html>