<%@page import="com.bc.model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO vo = (MemberVO)session.getAttribute("loginUser");	
	System.out.println("확인확인확인확인 : " + vo.toString());
	//String comm_name = (String)session.getAttribute("comm_name"); 

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 수정</title>
<link rel="stylesheet" href="../CSS/common.css" type="text/css">
<link rel="stylesheet" href="../CSS/profile.css" type="text/css">
<link rel="stylesheet" href="../CSS/footer.css" type="text/css">
<style>
	@import url('https://fonts.googleapis.com/css2?family=Open+Sans&display=swap');
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){		
		let $one = $('nav>ul>li:eq(0)');
		let $two = $('nav>ul>li:eq(1)');
		let $three = $('nav>ul>li:eq(2)');
		
		let $loc = $('#secondLocalNav');
		let $cate = $('#secondCateNav');
		let $cs = $('#secondCSNav');
		
		$one.click(function(){
			$cate.hide();
			$cs.hide();
			$loc.stop().show();
		});
		
		
		$two.click(function (){
			$loc.hide();
			$cs.hide();
			$cate.stop().show();
		});
		
		
		$three.click(function(){
			$cate.hide();
			$loc.hide();
			$cs.stop().show();
		});
		
		let category = ${idx} + "";
		let str = "${str}";
		
		switch (category) {
			case "1":
				$one[0].click();
				findByStr(str, "Local");
				break;
			case "2":
				$two[0].click();
				findByStr(str, "Cate");
				break;
			case "3":
				$three[0].click();
				findByStr(str, "CS");
				break;
		}
		
		function findByStr(str, cate) {
			let aArr = document.querySelectorAll("#second"+cate+"Nav > ul > li > a");
			for (let a of aArr) {
			    if (a.innerText === str) {
			        a.style = "color: red", "font-weight: bold";
			    }
			}
		}
		
	});
</script>
<script>
	function updateProfileOk(frm) {
		var email = document.getElementById("txtEmail").value;
		var phonnum = document.getElementById("txtPhone").value;
		
		if(email.length < 1 ) {
			alert("이메일이 입력되지 않았습니다.");
			return false;
		}
		if(phonnum.length < 1) {
			alert("전화번호가 입력되지 않았습니다.");
			return false;
		}
		
		frm.action="mypageController?type=updateProfile_Ok"	
		frm.submit();
	}
</script>
</head>
<body>
	<div class="wrap">
		<jsp:include page="../common/commonloginAfterHeader.html"/>
	    <nav>
	        <form class="searchWrap" name="frm" action="../main/bitmarket" >
                <input type="text" style="height: 27px; width: 200px; color:gray;" value="상품명 검색" name="str">
                <input type="hidden" name="idx" value="0">
                <input  TYPE="image" src="../img/Magnifying Glass.png" name="Submit" value="Submit"  align="absmiddle"
                 class="searchBtn">
            </form>
	        <ul>
	            <li><a href="#">지역</a></li>
	            <li><a href="#">카테고리</a></li>
	            <li><a href="#">고객센터</a></li>
	        </ul>
	    </nav>
	    <jsp:include page="../common/commonsecondNav.html"/>
		<main>
			<div class="txt">
				<h2>프로필 수정</h2>
				<h4 style="color:#82B57F;">(*)는 수정불가</h4>
			</div>
			<form method="post" enctype="multipart/form-data" class="profile">
				<table border>
						<tr>
							<td style="text-align:center;"  class="imguplo" >프로필 이미지<br/>
								<img src="${pageContext.request.contextPath }/uplo/<%=vo.getProfile_img()%>" width=250px height=250px alt="프로필사진" 
									 onerror="this.src='${pageContext.request.contextPath }/uplo/profile_icon.png'">
							</td>
							<td>사진수정 <input type="file" id="txtimg" name="profile_img1"></td>					
						</tr>
						<tr>
							<td><b>*</b>아이디</td>
							<td><%=vo.getId() %></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" id="password" name="password" style="width: 99%;"></td>
						</tr>
						<tr>
							<td><b>*</b>이름</td>
							<td><%=vo.getName() %></td>
						</tr>
						<tr>
							<td><b>*</b>생년월일</td>
							<td><%=vo.getYear()+vo.getMonth()+vo.getDay() %></td>
						</tr>
						<tr>
							<td>주소</td>
							<td><select id="optLocation" name="location" style="width: 100%;">
								<option value="L01" selected>서울</option>
								<option value="L02">경기</option>
								<option value="L03">충청</option>
								<option value="L04">전라</option>
								<option value="L05">강원</option>
								<option value="L06">경상</option>
								</select></td><br>
						</tr>
						<tr>
							<td>핸드폰번호</td>
							<td><input type="text" id="txtPhone" name="phonnum" style="width: 99%;"></td>
						</tr>
						<tr>      
							<td>이메일</td>
							<td><input type="email" id="txtEmail" name="email" style="width: 99%;"></td>
						</tr>
					</tbody>
				</table>
				<input type="button" value="확인" onclick="updateProfileOk(this.form)">
				<input type="reset" value="초기화">
		 	
			</form>
		</main>
	</div>
	<jsp:include page="../common/footer.html"/>
</body>
</html>