<%@page import="com.bc.model.vo.MemberVO"%>
<%@page import="com.bc.model.SelectDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MemberVO vo = (MemberVO) session.getAttribute("loginUser");
	
	System.out.println("MyPage vo: " + vo);
	String img = vo.getProfile_img();
	//System.out.println("img: " + img);
	
	String location = vo.getLocation();
	//System.out.println("location: " + location);
	
	String id = vo.getId();
	
	//마이페이지 - 지역 확인용
	MemberVO mvo = SelectDAO.myPageLocation(id, location);
	String common_name = mvo.getCommon_name();
	//System.out.println("common_name: " + common_name);
	session.setAttribute("common_name", common_name);
	
	 int count = SelectDAO.checkSoldList(id);
	 System.out.println("count: " + count);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="../CSS/mypage.css" type="text/css">
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
			        a.style = "color: #82B57F; font-weight:bold";
			    }
			}
		}
		
	});
</script>
</head>
<body>
	<div class="wrap">
		<jsp:include page="../common/commonloginAfterHeader.html"/>
		<nav>
            <form class="searchWrap" name="frm" action="../main/bitmarket">
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
			<jsp:include page="mypageNav.html"/>
			<div>
				<div id="cut">
					<h1>마이페이지</h1>
					<img src="../img/profile_wrap.png" id="profile_wrap"> 
				</div>
				<div id="pf">
					<img src="${pageContext.request.contextPath }/uplo/<%=vo.getProfile_img()%>" onerror="this.src='${pageContext.request.contextPath }/uplo/profile_icon.png'" 
				id="profile"/>
				</div>
				<div id="txt">
					<p>반갑습니다 </p><br/>
					<p><b style="color:#82B57F; font-size:20px;"><%=mvo.getCommon_name() %></b>에 거주하시는 <b style="font-size:20px;"><%=vo.getName() %></b>고객님</p><br/>
					<p>이번 달은 <b>환경돕기에  <%=count %>번 동참</b>해주셨어요!</p>
				</div>
			</div>
        </main>
	</div>
	<jsp:include page="../common/footer.html"/>
</body>
</html>