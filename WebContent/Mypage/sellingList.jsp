<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.bc.model.vo.PListVO"%>
<%@page import="java.util.List"%>
<%@page import="com.bc.model.SelectDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<PListVO> plist = (List<PListVO>)request.getAttribute("plist");
	System.out.println("plist: " + plist);
	pageContext.setAttribute("list", plist);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매내역</title>
<link rel="stylesheet" href="../CSS/common.css" type="text/css">
<link rel="stylesheet" href="../CSS/mypagemenu.css" type="text/css">
<link rel="stylesheet" href="../CSS/soldlist.css" type="text/css">
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
<script>
	$(document).ready(function(){
		
		$('ul.tabs li').click(function(){
			var tab_id = $(this).attr('data-tab');
	
			$('ul.tabs li').removeClass('current');
			$('.tab-content').removeClass('current');
	
			$(this).addClass('current');
			$("#"+tab_id).addClass('current');
		})
	})
	
	function changeStatus(frm) {
		frm.action="mypageController?type=changeStatusToSold"
		frm.submit();
	}
	
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
			<div class="container">
				<ul class="tabs">
					<li class="tab-link current" data-tab="tab-1"><b>판매중</b></li>
					<li class="tab-link" data-tab="tab-2"><a href="../Mypage/mypageController?type=inProgressList">거래진행중</a></li>
					<li class="tab-link" data-tab="tab-3"><a href="../Mypage/mypageController?type=soldList">판매완료</a></li>
				</ul>
					
				<form method="post">
				<div id="tab-1" class="tab-content current">
					<c:if test="${empty plist }">
						<h2 style="color:gray; text-align: center; padding: 200px;">판매중인 상품이 없습니다.</h2>
					</c:if>
					<c:if test="${not empty plist }">
							<c:forEach var="pvo" items="${plist }">
								<fieldset>
								<table>
									<tbody>
										<tr>
											<td rowspan="3"><img src="${pageContext.request.contextPath }/uplo/${pvo.picture01 }" width=100px height=100px alt="상품사진"></td>
											<td><b style="font-size:20px;">${pvo.p_title }</b><br><br>${pvo.p_regdate }<br><br>${pvo.price }</td>
										</tr>
									</tbody>
								</table>		
							<input type="button" value="수정" onclick="location.href = 'mypageController?type=updatePrise&p_no=${pvo.p_no }';">
							<input type="button" value="삭제" onclick="location.href = 'mypageController?type=deletePlist&p_no=${pvo.p_no }';">			</fieldset>	
							</c:forEach>
						</c:if>
					</div>
					</form>
			</div>
	    </main>
</div>
<jsp:include page="../common/footer.html"/>
</body>