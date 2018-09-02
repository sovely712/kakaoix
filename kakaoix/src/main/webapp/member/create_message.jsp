<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>알림</title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript">

$(function(){ 
  
  });
</script>
 
</head> 
<body>
<DIV class='container'>
<c:import url="/menu/top.jsp" />
<DIV class='content' style="padding-bottom: 50px;">
 
<DIV class='title_line'>알림</DIV>
 
<DIV class='message'>

    <UL>
      <c:choose>
        <c:when test="${param.sw == 'mid'}">
          <li class='li_none'>회원 ID가 중복 되었습니다.</li>
          <li class='li_none'>다시한번 시도해주세요.</li>
          <li class='li_none'>
            <br>
            <button type='button' onclick='history.back()'>다시 시도</button>
            <button type='button' onclick="location.href='${pageContext.request.contextPath}'">취소</button>
          </li>
        </c:when>
        
        <c:when test="${param.sw == 'email'}">
          <li class='li_none'>회원 Email이 중복 되었습니다.</li>
          <li class='li_none'>다시한번 시도해주세요.</li>
          <li class='li_none'>
            <br>
            <button type='button' onclick='history.back()'>다시 시도</button>
            <button type='button' onclick="location.href='${pageContext.request.contextPath}'">취소</button>
          </li>
        </c:when>
        
        <c:when test="${param.sw == 'create'}">
          <c:choose>
          
            <c:when test="${param.count == 0}">
              <li class='li_none'>회원 가입에 실패했습니다.</li>
              <li class='li_none'>다시한번 시도해주세요.</li>
              <li class='li_none'>
                <br>
                <button type='button' onclick='history.back()'>다시 시도</button>
                <button type='button' onclick="location.href='${pageContext.request.contextPath}'">취소</button>
              </li>
            </c:when>
            
            <c:when test="${param.count == 1 }">
              <li class='li_none'>회원 가입에 성공했습니다.</li>
              <li class='li_none'>
                <br>
                <button type='button' onclick="location.href='${pageContext.request.contextPath}'">확인</button>
              </li>          

            </c:when>
          </c:choose>
        
        </c:when>
      </c:choose>    
 
    </UL>
</DIV>

<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV><!-- content END -->
</DIV> <!-- container END -->
</body>
 
</html> 
 