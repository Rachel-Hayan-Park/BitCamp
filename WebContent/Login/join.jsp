<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/emailjs-com@2/dist/email.min.js"></script>
<script type="text/javascript">
    var pw_passed = true;
	var result = Math.floor(Math.random() * 1000000)+100000;
	if(result>1000000){
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
    
    function idcheckopen() {
    	if(document.fr.txtId.value < 1) {
    		alert("아이디가 입력되지 않았습니다.");
    	} else {
    	window.open("joinIdCheck.jsp?id="+document.fr.txtId.value,"","width=500 height=300");
    	}
    }

    function fn_pw_check() {
        var pw = document.getElementById("txtPassword1").value;
        var pw2 = document.getElementById("txtPassword2").value;
        var id = document.getElementById("txtId").value;
        var name = document.getElementById("txtName").value;
        var year = document.getElementById("bYear").value;
        var month = document.getElementById("bMonth").value;
        var day = document.getElementById("bDay").value;
        var phone = document.getElementById("txtPhone").value;
        var location = document.getElementById("optLocation").value;
        var email = document.getElementById("txtEmail").value;
        var inText = document.getElementById("txtIn").value;
        var profileimg = document.getElementById("txtimg").value;
        
        pw_passed = true;
        
        var today = new Date();
        var yearNow = today.getFullYear();
		
        var pattern1 = /[0-9]/;
        var pattern2 = /[a-zA-Z]/;
        var pattern3 = /[~!@\#$%<>^&*]/;
        var pattern4 = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
        var pattern5 = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        
        var pw_msg = "";
        if(id.length == 0) {
               alert("아이디를 입력해주세요");
               return false;
         } else {
         }
        if(pw.length == 0) {
               alert("비밀번호를 입력해주세요");
               return false;
         } else {
                if(pw != pw2) {
                      alert("비밀번호가 일치하지 않습니다.");
                      return false;
                 }
         }
        if(name.length == 0){
        	alert("이름을 입력해주세요.");
        	return false;
        }
        if(year.length == 0 || month.length == 0 || day.length == 0){
        	alert("생년월일을 모두 기입해주세요.");
        	return false;
        }
        if(phone.length == 0){
        	alert("전화번호를 입력해주세요.");
        	return false;
        }
        if(location.length == 0){
        	alert("주소를 입력해주세요.");
        	return false;
        }
        if(email.length == 0){
        	alert("이메일을 입력해주세요.");
        	return false;
        }
        if(!pattern1.test(pw)||!pattern2.test(pw)||!pattern3.test(pw)||pw.length<8||pw.length>50){
            alert("비밀번호엔 영문과 숫자, 특수문자를 모두 입력해야 합니다.");
            return false;
        }          
        if(pw.indexOf(id) > -1) {
            alert("비밀번호에 ID를 포함할 수 없습니다.");
            return false;
        }
        
        var SamePass_0 = 0;
        var SamePass_1 = 0;
        var SamePass_2 = 0;

        for(var i=0; i < pw.length; i++) {
             var chr_pass_0;
             var chr_pass_1;
             var chr_pass_2;

             if(i >= 2) {
                 chr_pass_0 = pw.charCodeAt(i-2);
                 chr_pass_1 = pw.charCodeAt(i-1);
                 chr_pass_2 = pw.charCodeAt(i);

                 if((chr_pass_0 == chr_pass_1) && (chr_pass_1 == chr_pass_2)) {
                    SamePass_0++;
                  } 
                  else {
                   SamePass_0 = 0;
                   }
                 if(chr_pass_0 - chr_pass_1 == 1 && chr_pass_1 - chr_pass_2 == 1) {
                     SamePass_1++;
                  }
                  else {
                   SamePass_1 = 0;
                  }
                 if(chr_pass_0 - chr_pass_1 == -1 && chr_pass_1 - chr_pass_2 == -1) {
                     SamePass_2++;
                  }
                  else {
                   SamePass_2 = 0;
                  }  
             }     
            if(SamePass_0 > 0) {
               alert("동일문자를 3자 이상 연속 입력할 수 없습니다.");
               pw_passed=false;
             }
            if(SamePass_1 > 0 || SamePass_2 > 0 ) {
               alert("영문, 숫자는 3자 이상 연속 입력할 수 없습니다.");
               pw_passed=false;
             } 
             if(!pw_passed) {             
                  return false;
                  break;
            }
        }
		if (year.length+month.length+day.length <=8) {
        	if(1900 > year || year > yearNow){
        		alert("정확한 년도를 입력해 주세요.");
        		return false;
        	}else if(month < 1 || month > 12){
        		alert("정확한 월수를 입력해 주세요.");
        		return false;
        	}else if(day < 1 || day > 31){
        		alert("정확한 일수를 입력해 주세요.");
        		return false;
        	}
        }else{
        	alert("생년월일을 정확히 기입해주세요. YYYY/MM/DD");
        	return false;
        }

		if(!pattern4.test(phone)){
			alert("정확한 전화번호를 입력해주세요. -제거");
			return false;
		}
		if(!pattern5.test(email)){
			alert("정확한 이메일을 입력해주세요.");
			return false;
		}
		if(!(result == inText)){
			alert("틀린 인증번호입니다.");
			return false;
		}
		
   		alert("회원가입이 완료되었습니다");
        return true;
    } 
	
</script>
<style>
	input {
		color: gray;
	}
	input:focus {
		color: black;
	}
</style>
</head>
<body>
	<h2>회원가입</h2>
	<form action="loginController?type=joinCheck" method="post" name="fr" enctype="multipart/form-data" onsubmit = "return fn_pw_check();">
		
		사진등록<br> <input type="file" id="txtimg" name="profile_img"><br/>
		아이디<br>  <input type="text" id="txtId" name="id" />
		<input type="button" value="중복확인" onclick="idcheckopen()"><br/>
		비밀번호 <br> <input type="password" id="txtPassword1" name="password"/><br/>
		비밀번호확인<br> <input type="password" id="txtPassword2" /><br/>
		이름<br> <input type="text" id="txtName" name="name"/><br/>
		생년월일<br> <input type="text" id="bYear" value="1990" onfocus="this.value='';return true;" name="year"/>
		년<input type="text" id="bMonth" value="01" onfocus="this.value='';return true;" name="month"/>
		월 <input type="text" id="bDay" value="01" onfocus="this.value='';return true;" name="day"/> 일 <br/>
		전화번호<br> <input type="text" id="txtPhone" value="01012345678" onfocus="this.value='';return true;" name="phonnum"/> <br/>
		주소<br> <select id="optLocation" name="location">
			<option value="L01" selected>서울</option>
			<option value="L02">경기</option>
			<option value="L03">충청</option>
			<option value="L04">전라</option>
			<option value="L05">강원</option>
			<option value="L06">경상</option>
			 </select><br>
		이메일<br> <input type="text" id="txtEmail" value="861037@naver.com" onfocus="this.value='';return true;" name="email"/>
		<input type="button" value="인증번호 전송" onclick="send_email()"><br/>
		인증번호<br> <input type="text" id="txtIn"/><br/>
	    <input type="submit" value ="확인">
    </form>
</body>
</html>