<%@page import="dev.mvc.tool.Tool"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
  String root = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript"  src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript">  
</script>

</head>
<body>

<!-- 화면 상단 메뉴 --> 

<DIV style='text-align: center;'>

 <A href ='/kakaoix/'>    
    <IMG src='<%=root%>/menu/images/top_menu.PNG' style='width: 20%; '>
 </A>

<NAV class='top_menu_list'>
    <c:choose>
         <c:when test="${sessionScope.mid == null}" >
              <A href='${pageContext.request.contextPath}/category/list.do'>카테고리</A><span class='top_menu2'> | </span>
              <A href='${pageContext.request.contextPath}/contents/list.do?categoryno=1'>상품정보</A><span class='top_menu2'> | </span>
              <A href='${pageContext.request.contextPath}/member/login_need.jsp'>주문내역</A><span class='top_menu2'> | </span>
              <A href='${pageContext.request.contextPath}/member/login_need.jsp'>장바구니</A><span class='top_menu2'> | </span>
              <A href='${pageContext.request.contextPath}/member/login_need.jsp'>적립금</A>
              
         </c:when>
         <c:when test="${sessionScope.mid != null}" >
              <A href='${pageContext.request.contextPath}/category/list.do'>카테고리</A><span class='top_menu2'> | </span>
              <A href='${pageContext.request.contextPath}/contents/list.do?categoryno=1'>상품정보</A><span class='top_menu2'> | </span>
              <A href='${pageContext.request.contextPath}/order/list_order_search_paging.do'>주문내역</A><span class='top_menu2'> | </span>
              <A href='${pageContext.request.contextPath}/basket/list.do'>장바구니</A><span class='top_menu2'> | </span>
              <A href='${pageContext.request.contextPath}/point/list.do'>적립금</A>
         </c:when>
    </c:choose>
</NAV>
<DIV style='text-align: right; padding:0px 150px '>
<c:choose>
         <c:when test="${sessionScope.mid == null}" >
              <A href='${pageContext.request.contextPath}/member/login.do' >
              <IMG src='<%=root%>/menu/images/login.png' >
              </A>
         </c:when>
         <c:when test="${sessionScope.mid != null}" >
              <A href='${pageContext.request.contextPath}/member/logout.do'>
              <IMG src='<%=root%>/menu/images/logout.png'>
              </A>
         </c:when>
</c:choose>
</DIV>

<IMG src='<%=root%>/menu/images/1.PNG' style='width: 80%; '>
</DIV>


</body>
</html>