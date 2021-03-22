<%@page import="com.bc.model.vo.PListVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PListVO pvo = (PListVO)request.getAttribute("pvo");
	System.out.println("요청 : " + pvo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지</title>
<script type="text/javascript">
		var cnt = 0;
		var limit = 2; //최대 몇장?
     	function addimage(event) {
			var reader = new FileReader();
        		if(cnt >= limit)
        		{
        			alert("이미지는 최대 " + limit + "장 제한입니다.");
            		return false;
        		}
        		else
        		{
        			reader.onload = function(event){
        				var img = document.createElement("img");
        				img.setAttribute("src", event.target.result);
        				document.querySelector("div#image_container").appendChild(img);
        			};
        			
        			reader.readAsDataURL(event.target.files[0]);
        			cnt++;
        		}
      	}
		
		function enrollment() {
			var image = document.getElementById("picture01").value;
			var title = document.getElementById("title").value;
			var cate = document.getElementById("category").value;
			var loca = document.getElementById("location").value;
			var prise = document.getElementById("price").value;
			var txt = document.getElementById("contents").value;
			
			var pattern1 = /^[0-9]*$/;
			
			if(title.length == 0){
	        	alert("제목을 입력해주세요.");
	        	return false;
	        }
			if(prise.length == 0){
	        	alert("가격을 입력해주세요.");
	        	return false;
	        }
			if(!pattern1.test(prise)){
				alert("가격엔 숫자만 입력 가능합니다.");
				return false;
			}
			if(txt.length == 0){
	        	alert("내용을 입력해주세요.");
	        	return false;
	        }
			if(txt.length < 10){
	        	alert("내용은 최소 10글자 이상 입력해주세요.");
	        	return false;
	        }
			if(image.length == 0){
	        	alert("사진 한장은 필수 등록입니다.");
	        	return false;
	        }
			
			alert("수정 완료");
			return true;
		}
</script>
</head>
<body>
	<form action="mypageController?type=updatePriseGO&p_no=${pvo.p_no }" method="post" enctype="multipart/form-data" onsubmit="return enrollment();">
		제목 <input type="text" id="title" value="<%=pvo.getP_title() %>" name="title"/><br/>
		카테고리<select id="category" name="cate">
			<option value = "PC01">의류</option>
			<option value = "PC02">패션잡화</option>
			<option value = "PC03">디지털/가전</option>
			<option value = "PC04">도서</option>
			<option value = "PC05">뷰티/미용</option>
			<option value = "PC06">기타</option>
		</select>
		지역<select id="location" name="location">
			<option value = "L01">서울</option>
			<option value = "L02">경기</option>
			<option value = "L03">충청</option>
			<option value = "L04">전라</option>
			<option value = "L05">강원</option>
			<option value = "L06">경상</option>
		</select>	
		가격 <input type="text" id="price" value="<%=pvo.getPrice() %>" name="price"/><br/>
		내용 <input type="text" id="contents" value="<%=pvo.getP_contents() %>" name="contents"/><br/>
		사진등록 <input type="file" name="f_name" id="picture01" value="<%=pvo.getPicture01() %>" accept="image/*" onchange = "addimage(event);"><br/>
		<div id="image_container"></div>
		<input type="submit" value="수정">
	</form>
</body>
</html>