<%@page import="com.bc.model.vo.MemberVO"%>
<%@page import="com.bc.model.vo.PListVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.bc.model.SelectDAO"%>
<%@page import="com.bc.model.vo.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%

	System.out.println("메인페이지 loginUser : " + (MemberVO)request.getSession().getAttribute("loginUser"));
	String idx = (String) request.getAttribute("idx");
	String str = (String) request.getAttribute("str");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BIT MARKET</title>
<link rel="stylesheet" href="../CSS/main.css" type="text/css">
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
		%>
		
		$('.noClick').click(function(){
			alert("해당상품은 판매완료되었어요!");
			return false;
		})
		
	});
</script>
</head>
<body>
	<div class="wrap">
	<c:if test="${empty sessionScope.loginUser}">
		<jsp:include page="loginBeforeHeader.html"/>
	</c:if>
	<c:if test="${not empty sessionScope.loginUser}">
		<jsp:include page="loginAfterHeader.html"/>
	</c:if>
        <nav>
            <form class="searchWrap" name="frm" action="bitmarket">
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
        <jsp:include page="secondNav.html"/>
        <main>
        	
	        <ul id="product_list">
	        <c:if test="${empty list }">
	        	<h2>현재 등록된 게시글이 없습니다.</h2>
	        </c:if>
	        <c:if test="${not empty list }">
	            <c:forEach var="vo" items="${list }">
			             <li>
		                 	<!-- 판매완료시 판매완료 상태변경  -->
		                 	<c:set var="p_status" value="${vo.p_status }"/>
	                 		<c:if test="${f:contains(p_status, 'PS01') }">
	                 			<a href="../PList/PListController?type=viPrise&p_no=${vo.p_no }">
		                 			<img src="${pageContext.request.contextPath}/uplo/${vo.picture01}" alt="${vo.p_title }">
		                 		</a>
		                 	</c:if>
		                 	<c:if test="${f:contains(p_status, 'PS02') }">
		                 		<a class="noClick">
		                 			<img src="${pageContext.request.contextPath}/uplo/soldout.jpg" alt="${vo.p_title }">
		                 		</a>
		                 	</c:if>
		                 	<c:if test="${f:contains(p_status, 'PS03') }">
	                 			<a href="../PList/PListController?type=viPrise&p_no=${vo.p_no }">
		                 			<img src="${pageContext.request.contextPath}/uplo/${vo.picture01}" alt="${vo.p_title }">
		                 		</a>
		                 	</c:if>
		                     <c:choose>
		                     	<c:when test="${vo.p_title.length() > 9 }">
		                     		<h3>${vo.p_title.substring(0,9) }...</h3>
		                     	</c:when>
		                     	<c:otherwise>
		                     		<h3>${vo.p_title }</h3>
		                     	</c:otherwise>
		                     </c:choose>
		                     <p>${vo.price }</p>
		                     <p style="font-size:12px; padding-top:5px;">${vo.common_name }</p>
			             </li>
	        	</c:forEach> 
	        </c:if>
	        </ul>
        </main>
        <ol class="paging">
					<c:choose>
						<c:when test="${pvo.beginPage == 1 }">
							<li class="disable">이전으로</li>
						</c:when>
						<c:otherwise>
							<li>
								<a href="bitmarket?cPage=${pvo.beginPage-1 }&idx=${idx}&str=${str}">
									이전으로
								</a>
							</li>
						</c:otherwise>
					</c:choose>

					<c:forEach var="pageNo" begin="${pvo.beginPage }" end="${pvo.endPage }">
						
						<c:if test="${pageNo == pvo.nowPage }">
							<li>${pageNo }</li>
						</c:if>
						
						<c:if test="${pageNo != pvo.nowPage }">
							<li>
								<a href="bitmarket?cPage=${pageNo }&idx=${idx}&str=${str}">${pageNo }</a>
							</li>
						</c:if>
						
					</c:forEach>

						 <c:if test="${pvo.endPage < pvo.totalPage }">
						 	<li><a href="bitmarket?cPage=${pvo.endPage +1 }&idx=${idx}&str=${str}">다음으로</a></li>
						 </c:if>
						 <c:if test="${pvo.endPage >= pvo.totalPage }">
						 	<li class="disable">다음으로</li>
						 </c:if>
			</ol>
    </div>
    <jsp:include page="../common/footer.html"/>
</body>
</html>