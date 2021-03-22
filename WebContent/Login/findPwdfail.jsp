<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/emailjs-com@2/dist/email.min.js"></script>
<script type="text/javascript">
	alert("존재하지 않는 아이디거나 이름, 이메일입니다.");
	var result = Math.floor(Math.random() * 1000000) + 100000;
	if(result > 1000000) {
		result = result - 100000;
	}
	function send_email() {
		emailjs.init("user_GA7H9WgLQegcIcWweaM9m");
    	var templateParams = {
    		name : document.getElementById("txtName").value,
    		email : document.getElementById("txtEmail").value,
    		in_code : result
    	};
    	emailjs.send('service_yg1gt33','template_36fkok7', templateParams)
    	.then(function(response){
    		alert("인증번호 전송 성공!");
    	}, function(error){
    		alert("인증번호 전송 실패!");
    	});
	}
	
	function findpwd(frm) {
		var name = document.getElementById("txtName").value;
		var id = document.getElementById("txtId").value;
		var email = document.getElementById("txtEmail").value;
		var inText = document.getElementById("txtIn").value;
		
		if(name.length == 0) {
			alert("이름을 입력하세요");
			return false;
		} 
		if(id.length == 0) {
			alert("아이디를 입력하세요");
			return false;
		}
		if(email.length == 0) {
			alert("이메일을 입력하세요");
			return false;
		}
		if(!(result == inText)){
			alert("틀린 인증번호입니다.");
			return false;
		}
		frm.submit();
		return true;
	}
</script>
</head>
<body>
	<h2>비밀번호찾기</h2>
	<form action="loginController?type=findPwd_Ok" method="post">
		이름 <input type="text" id="txtName" name="name"><br/>
		아이디 <input type="text" id="txtId" name="id"><br/>
		이메일 <input type="text" id="txtEmail" name="email" value="861037@naver.com"><br/>
		<a href="#" onclick="send_email()">이메일 인증번호 전송</a><br/>
		인증번호 : <input type="text" id="txtIn"/><br/>
		<input type="button" value="비밀번호찾기" onclick="findpwd(this.form)">
	</form>
</body>
</html>