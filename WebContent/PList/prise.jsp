<%@page import="com.bc.model.vo.MemberVO"%>
<%@page import="com.bc.model.vo.PListVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.bc.model.SelectDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	System.out.println("세션이 존재? : " + session.getAttribute("loginUser"));
	PListVO pvo = (PListVO)request.getAttribute("pvo");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BIT MARKET</title>
<link rel="stylesheet" href="../CSS/common.css" type="text/css">
<link rel="stylesheet" href="../CSS/prise.css" type="text/css">
<link rel="stylesheet" href="../CSS/footer.css" type="text/css">
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
		
		<%
		MemberVO vo = (MemberVO) session.getAttribute("loginUser");
		if(vo == null){
	%>
		$('#mainMenu a:last').click(function(){
			alert("로그인 후 상품등록을 할 수 있어요〒▽〒");
			return false;
		})
	<%
		}
		
		if(vo == null){
			%>
			$('.btn').click(function(e){
				alert("로그인 후 구매 할 수 있어요〒▽〒");
				location.reload();
				return false;
			})
			<%
		}
	%>

	});
	
	 function buying() {
	      location.href="PListController?type=buyingOk"
     }
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
        <main>
            	<jsp:include page="../common/commonsecondNav.html"/>
            	<div id="content">
	            	<ul>
	            		<img src = "${pageContext.request.contextPath}/uplo/${pvo.picture01}">
	            		<div class="txt">
	            			<li id="title"><p>${pvo.p_title }</p></li>
	            			<li><p>${pvo.price }</p></li>
		            		<c:choose>
			            			<c:when test = "${pvo.location eq 'L01' }">
			            				<li><p>서울</p></li>
			            			</c:when>
			            			<c:when test = "${pvo.location eq 'L02' }">
			            				<li><p>경기</p></li>
			            			</c:when>
			            			<c:when test = "${pvo.location eq 'L03' }">
			            				<li><p>충청</p></li>
			            			</c:when>
			            			<c:when test = "${pvo.location eq 'L04' }">
			            				<li><p>전라</p></li>  
			            			</c:when>
			            			<c:when test = "${pvo.location eq 'L05' }">
			            				<li><p>강원</p></li>
			            			</c:when>
			            			<c:when test = "${pvo.location eq 'L06' }">
			            				<li><p>경상</p></li>
			            			</c:when>
		            		</c:choose>
		            		<li><p>${pvo.sale_id }</p></li>
		            		<li><p>${pvo.p_regdate }</p></li>
	            		</div>
	            		
	            		<div class="salestatus">
		            		<c:choose>
		            			<c:when test = "${pvo.p_status == 'PS01' }">
		            				<li><p>판매중</p></li>
		            			</c:when>
		            			<c:when test = "${pvo.p_status == 'PS03' }">
		            				<li><p>거래중</p></li>
		            			</c:when>
		            			<c:otherwise>
		            				<li><p>판매완료</p></li>
		            			</c:otherwise>
		            		</c:choose>
	            		</div>
	            		
	            		<div class="cate">
	            			<c:choose>
		            			<c:when test = "${pvo.p_category eq 'PC01' }">
		            				<li><p>의류</p></li>
		            			</c:when>
		            			<c:when test = "${pvo.p_category eq 'PC02' }">
		            				<li><p>패션잡화</p></li>
		            			</c:when>
		            			<c:when test = "${pvo.p_category eq 'PC03' }">
		            				<li><p>디지털/가전</p></li>
		            			</c:when>
		            			<c:when test = "${pvo.p_category eq 'PC04' }">
		            				<li><p>도서</p></li>
		            			</c:when>
		            			<c:when test = "${pvo.p_category eq 'PC05' }">
		            				<li><p>뷰티/미용</p></li>
		            			</c:when>
		            			<c:when test = "${pvo.p_category eq 'PC06' }">
		            				<li><p>기타</p></li>
		            			</c:when>
		            			<c:otherwise>
		            			</c:otherwise>
		            		</c:choose>
	            		</div>
	            		<input type="IMAGE" src="../img/buying.png" name="Submit" value="Submit"  align="absmiddle"
                          class="btn" onclick="buying()">
	            		<div class="content">
	            			<h3>상품내용</h3>
	            			<li><p>${pvo.p_contents }</p></li>
	            		</div>
	            		
	            	</ul>
            	</div>
            </main>
    </div>
    <jsp:include page="../common/footer.html"/>
</body>
</html>