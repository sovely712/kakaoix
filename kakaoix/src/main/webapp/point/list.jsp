<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script type="text/javascript">


</script>
 
 
</head> 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

    <label style='padding: 10px; font-size: 20px;'>현재 적립금 : ${sum }원 </label>
 
<TABLE class='table_basic1'>
  <colgroup>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 10%;'/>
  </colgroup>
 
  <thead> 
  <TR>
    <TH style='text-align: center ;'>적립날짜</TH>
    <TH style='text-align: center ;'>주문넘버</TH>
    <TH style='text-align: center ;'>적립 적립금</TH>
    <TH style='text-align: center ;'>사용 적립금</TH>
    <TH style='text-align: center ;'>상태값</TH>
  </TR>
  </thead>
  

    <c:forEach var="PointVO" items="${list }" varStatus="status">
    <TR>
      <TD style='text-align: center;' class="td_list_basic">${PointVO.pdate }</TD>
      <TD style='text-align: center;' class="td_list_basic">${PointVO.orderno }</TD>
      <TD style='text-align: center;' class="td_list_basic">${PointVO.s_point }</TD>
      <TD style='text-align: center;' class="td_list_basic">${PointVO.u_point }</TD>
      <TD style='text-align: center;' class="td_list_basic">${PointVO.pstatus }</TD>
    </TR>
    </c:forEach>
</TABLE>

</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
</body>
 
</html> 