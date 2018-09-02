<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
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
<DIV class='content'>
  <DIV class='message'>
    <H3>로그인이 필요한 페이지입니다.</H3>
     <button type='button' onclick="location.href='../member/login.do'">로그인</button>
     <button type='button' onclick="location.href='../member/create.do'">회원가입</button>
  </DIV>
 
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 
        