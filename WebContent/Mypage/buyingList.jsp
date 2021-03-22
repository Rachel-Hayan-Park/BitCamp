<%@page import="java.util.List"%>
<%@page import="com.bc.model.vo.PListVO"%>
<%@page import="com.bc.model.SelectDAO"%>
<%@page import="com.bc.model.vo.ReviewVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매내역</title>
<link rel="stylesheet" href="../CSS/common.css" type="text/css">
<link rel="stylesheet" href="../CSS/mypagemenu.css" type="text/css">
<link rel="stylesheet" href="../CSS/buyinglist.css" type="text/css">
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
		<c:if test="${empty sessionScope.loginUser}">
			<jsp:include page="../common/commonloginBeforeHeader.html"/>
		</c:if>
		<c:if test="${not empty sessionScope.loginUser}">
			<jsp:include page="../common/commonloginAfterHeader.html"/>
		</c:if>
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
	    	<div id="purchase">
	    		<h1>구매내역</h1>
	    		<c:if test="${empty list }">
	    			<h3 style="color:gray; opacity : 0.8; padding-left: 300px;padding-top: 180px;">구매내역이 존재하지 않습니다.</h3>
	    		</c:if>
	    		<c:if test="${not empty list }">
	    			<c:forEach var="vo" items="${list }" varStatus="status">
			    		<ul>
			    			<li><img src="${pageContext.request.contextPath}/uplo/${vo.picture01}" alt="${vo.p_title }" style="width : 400px;"></li>
				    		<ul>
							  	<li class="pTitle">${vo.p_title }</li>
				    			<li>${vo.p_regdate }</li>
				    			<li>${vo.price }</li>
							</ul>
			    		</ul>
			    		<div class="btnWraper">
			    			<c:if test="${empty rlist }">
			    				<a href="mypageController?type=reviewWrite&p_no=${vo.p_no }">후기작성</a>
			    			</c:if> 
			    			<c:forEach var="rvo" items="${rlist }" varStatus="status">
				  				<c:if test="${vo.p_no == rvo.p_no}">
				  					<c:if test="${rvo.r_status == vo.p_status}">
				  						<a style="pointer-events: none; cursor: default;" class="notbtn">작성완료</a>
				  					</c:if>
				  					<c:if test="${rvo.r_status != vo.p_status}">
				  						<a href="mypageController?type=reviewWrite&p_no=${vo.p_no }">후기작성</a>
				  					</c:if>
				  				</c:if>
			  				</c:forEach> 
		  				</div>
			    	</c:forEach>
	    		</c:if>
	    	</div>
	    </main>
    </div>
	<jsp:include page="../common/footer.html"/>
</body>
</html>