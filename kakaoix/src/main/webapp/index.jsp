<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<% 
String root = request.getContextPath();
%>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<title>KAKAO FRIENDS</title>
<meta charset="UTF-8">
  
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
  
</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
 
 
 
<jsp:include page="/menu/bottom.jsp" flush='false' /> 
</DIV> <!-- content END -->
</DIV> <!-- container END -->
 
</body>
</html>