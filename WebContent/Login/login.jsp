<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="../CSS/common.css" type="text/css">
<link rel="stylesheet" href="../CSS/login.css" type="text/css">
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
<script type="text/javascript">
	function start() {
		var id = document.getElementById("txtId").value;
		var pwd = document.getElementById("txtPwd").value;
		
		if(id.length == 0) {
			alert("아이디가 입력되지 않았습니다.");
			return false;
		}
		if(pwd.length == 0) {
			alert("비밀번호가 입력되지 않았습니다.");
			return false;
		}
		
		return true;
	}
	function join(frm) {
		frm.action="loginController?type=join"
		frm.submit();
	}
	function findId(frm) {
		frm.action="loginController?type=findId"
		frm.submit();
	}
	function findPwd(frm) {
		frm.action="loginController?type=findPwd"
		frm.submit();
	}
</script>
</head>
<body>
	<div class="wrap">
		<jsp:include page="../common/commonloginBeforeHeader.html"/>
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
        	<div class="form">
        		<h2>로그인</h2>
        		<form action="loginProc.jsp" method="post" onsubmit="return start();">
        			<table>
						<tr>
							<th style="font-size:20px; padding: 10px;">아이디</th>
							<td><input type="text" id="txtId" name="id" style="padding: 3px;"></td>
							<th rowspan="2">
								<input type="submit" value="로그인" class="loginbtn">
							</th>
						</tr>
						<tr>
							<th style="font-size:20px; padding: 10px;">비밀번호</th>
							<td><input type="password" id="txtPwd" name="password" style="padding: 3px;"></td>
						</tr>
					</table>
        		</form>
				<form method="post">
					<input type="button" value="회원가입" onclick="join(this.form)" style="padding: 5px 20px; margin: 10px 10px 10px 15px;">
					<input type="button" value="아이디찾기" onclick="findId(this.form)" style="padding: 5px 20px; margin-right: 10px;">
					<input type="button" value="비밀번호찾기" onclick="findPwd(this.form)" style="padding: 5px 20px; margin-right: 10px;">
				</form>
			</div>
        </main>
	</div>
	<jsp:include page="../common/footer.html"/>
</body>
</html>