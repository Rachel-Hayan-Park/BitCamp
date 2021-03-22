<%@page import="com.bc.model.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지</title>
<link rel="stylesheet" href="../CSS/common.css" type="text/css">
<link rel="stylesheet" href="../CSS/enroll.css" type="text/css">
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
   %>

   });
</script>
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
                    document.querySelector(".imguplo").appendChild(img);
                 };
                 
                 reader.readAsDataURL(event.target.files[0]);
                 cnt++;
              }
         }
      
      function enrollment() {
         var title = document.getElementById("title").value;
         if(title.length == 0 || title == "제목"){
              alert("제목을 입력해주세요.");
              return false;
           }
         var image = document.getElementById("picture01").value;
         if(image.length == 0){
              alert("사진 한장은 필수 등록입니다.");
              return false;
           }
         var cate = document.getElementById("category").value;
         if(cate == "none")
         {
            alert("카테고리를 선택해주세요.");
            return false;
         }
         var loca = document.getElementById("location").value;
         if(loca == "none")
         {
            alert("지역을 선택해주세요.");
            return false;
         }
         var price = document.getElementById("price").value;
         var pattern1 = /^[0-9]*$/;
         if(price.length == 0 || price == "가격"){
              alert("가격을 입력해주세요.");
              return false;
           }
         if(!pattern1.test(price)){
            alert("가격엔 숫자만 입력 가능합니다.");
            return false;
         }
         var txt = document.getElementById("contents").value;
         if(txt.length == 0 || txt == "구매자를 위해 상품내용을 입력해주세요!(❁´◡`❁)"){
              alert("내용을 입력해주세요.");
              return false;
           }
         if(txt.length < 10){
              alert("내용은 최소 10글자 이상 입력해주세요.");
              return false;
           }
         
         
         alert("등록 완료");
         return true;
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
          <h2>중고거래 상품등록</h2>
          <form action="PListController?type=goenroll" method="post" enctype="multipart/form-data"  onsubmit="return enrollment();"  class="form">
             <div class="imguplo"></div>
             <input type="file" name="f_name" id="picture01" accept="image/*" onchange = "addimage(event);"><br/>
            <input type="text" id="title" name="title" value="제목" onfocus="this.value='';return true;"/><br/>
            <select id="category" name="cate">
               <option value="none" selected disabled>카테고리</option>
               <option value = "PC01">의류</option>
               <option value = "PC02">패션잡화</option>
               <option value = "PC03">디지털/가전</option>
               <option value = "PC04">도서</option>
               <option value = "PC05">뷰티/미용</option>
               <option value = "PC06">기타</option>
            </select>
            <select id="location" name="location">
               <option value="none" selected disabled>지역</option>
               <option value = "L01">서울</option>
               <option value = "L02">경기</option>
               <option value = "L03">충청</option>
               <option value = "L04">전라</option>
               <option value = "L05">강원</option>
               <option value = "L06">경상</option>
            </select>
            <br/>   
            <input type="text" id="price" name="price" value="가격" onfocus="this.value='';return true;"/><br/>
            <textarea rows="20" cols="54" id="contents" onfocus="this.value='';return true;">구매자를 위해 상품내용을 입력해주세요!(❁´◡`❁)</textarea><br/>
            <input type="submit" value="등록" style="height: 30px; margin-top: 20px;">
         </form>
       </main>
   </div>
   <jsp:include page="../common/footer.html"/>
</body>
</html>