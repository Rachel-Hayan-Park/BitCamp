<%@page import="com.bc.model.SelectDAO"%>
<%@page import="com.bc.model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberVO vo = (MemberVO)session.getAttribute("vo");
	System.out.println("프로필오케이 페이지 내 mvo : " + vo);
	String id = vo.getId();
	MemberVO fvo = SelectDAO.finalProfile(id);
	System.out.println("fvo.comm_name: " + fvo.getCommon_name());
	session.setAttribute("loginUser", fvo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 확인</title>
<link rel="stylesheet" href="../CSS/common.css" type="text/css">
<link rel="stylesheet" href="../CSS/profile_ok.css" type="text/css">
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
		
		var title = $('.pTitle').html();
		if(title.legth > 10){
			title = title.substr(0,10) + "<br>" + title.substr(10, title.length);
			$('.pTitle').html(title);
		}
	});
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
		    	<h2>프로필정보</h2>
				<form action="../main/bitmarket" method="post">
					<table border>
							<tbody>
								<tr>
									<td style="font-weight: bold; padding: 5px;">사진 </td>
									<td style="text-align:left;"><img src="${pageContext.request.contextPath }/uplo/${vo.getProfile_img() }" width=200px height=200px alt="프로필사진" onerror="this.src='${pageContext.request.contextPath }/uplo/profile_icon.png'"></td>
									<input type="hidden" name="filename" value="${loginUser.getProfile_img() }">
								</tr>
								<tr>
									<td style="font-weight: bold; padding: 5px;">아이디</td>
									<td><input type="text" value="${loginUser.id}" name="id" disabled></td>
								</tr>
								<tr>
									<td style="font-weight: bold; padding: 5px;">비밀번호</td>
									<td><input type="password" value="${loginUser.getPassword()}" name="pwd" disabled></td>
								</tr>
								<tr>
									<td style="font-weight: bold; padding: 5px;">이름</td>
									<td><input type="text" value="${loginUser.name }" name="name" disabled ></td>
								</tr>
								<tr>
									<td style="font-weight: bold; padding: 5px;">생년월일</td>
									<td style="text-align:left;">
										<input type="text" value="${loginUser.getYear() }" name="year" style="width: 8%;" disabled><span style="font-size : 13px;">년</span>
										<input type="text" value="${loginUser.getMonth() }" name="month" style="width: 5%;" disabled><span style="font-size : 13px;">월</span>
										<input type="text" value="${loginUser.getDay() }" name="day" style="width: 5%;" disabled><span style="font-size : 13px;">일</span>
									</td>
								</tr>
								<tr>
									<td style="font-weight: bold; padding: 5px;">주소</td>
									<td><input type="text" value="${loginUser.getCommon_name()}" name="adress" disabled></td><br>
								</tr>
								<tr>
									<td style="font-weight: bold; padding: 5px;">핸드폰번호</td>
									<td><input type="text" value="${loginUser.getPhonnum() }" name="phone" disabled></td>
								</tr>
								<tr>
									<td style="font-weight: bold; padding: 5px;">이메일</td>
									<td><input type="text" value="${loginUser.getEmail() }" name="email" disabled></td>
								</tr>
							</tbody>
						</table>
						<input type="submit" value="확인">
					</form>
		    </main>
		</div>
		<jsp:include page="../common/footer.html"/>
	</body>
</html>