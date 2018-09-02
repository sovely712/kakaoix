<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript">

  $(function(){ 
  
  });
</script>
 
</head> 
<body>
<DIV class='container' >
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' style="padding-bottom: 50px;">
 
<DIV class='title_line'>알림</DIV>
  <DIV class='message'>

      <ul>
        <li class='li_none'>회원 로그인에 실패했습니다.</li>
        <li class='li_none'>ID 또는 패스워드가 일치하지 않습니다.</li>
        <li class='li_none'>
            <br>
            <button type='button' onclick="location.href='./login.do'">다시 시도</button>
            <button type='button' onclick="location.href='../index.jsp'">목록</button>
          </li> 
      </ul>
  </DIV>
 
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
 
</html> 
 