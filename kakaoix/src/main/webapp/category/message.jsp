<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>    
    
<script type="text/javascript">
  $(function(){ 
  
  });
</script>

</head> 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

<DIV class='title_line'>알림</DIV>

<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:forEach var="msg" items="${msgs }">
        <li class='li_none'>${msg}</li>
      </c:forEach>

      <li class='li_none'>
        <br>
        <c:forEach var="link" items="${links }">
          ${link}
        </c:forEach>
      </li>
    </UL>
  </fieldset>

</DIV>

<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>

</html> 
 
  
  
  