<%@page import="com.bc.model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script>

	//목록 누르면 list로 이동
	function list_go() {
		location.href = "list.jsp";
	}
	
	function sendData(frm) {
		var b_title = document.getElementById("txtTitle").value;
		var b_contents = document.getElementById("txtContents").value;
		var password = document.getElementById("pwd").value;
		
		if(b_title.length == 0) {
			alert("게시글 제목이 입력되지 않았습니다.");
			document.getElementById("txtTitle").focus();
			return false;
		}
		if(b_contents.length == 0) {
			alert("게시글 내용이 입력되지 않았습니다.");
			document.getElementById("txtContents").focus();
			return false;
		}
		if(password == 0) {
			alert("비밀번호가 입력되지 않았습니다.");
			document.getElementById("pwd").focus();
			return false;
		}
		frm.action="../board/write_ok.jsp";
		frm.submit();
	}
</script>

</head>
<body>

<div id="write">
<form method="post" enctype="multipart/form-data">
	<table>
		<caption>문의 게시판 글쓰기</caption>
		<tbody>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" id="txtTitle" name="b_title" size="45" title="제목">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" value="<%=mvo.getId() %>" name="writer_id" size="12" title="작성자">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="b_contents" id="txtContents" rows="8" cols="50" title="내용"></textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="img_name">
				</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" name="b_password" id="pwd" size="12" title="패스워드">
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<input type="button" value="저장" onclick="sendData(this.form)">
					<!-- <input type="hidden" value="${mvo.id }" name="id">  -->
					<input type="reset" value="다시작성">
					<input type="button" value="목록" onclick="list_go()">
				</td>
			</tr>
		</tfoot>
	</table>
</form>


</div>


</body>
</html>