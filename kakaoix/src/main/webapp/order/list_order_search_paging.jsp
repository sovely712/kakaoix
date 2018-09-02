<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>주문 조회</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<script type="text/JavaScript" src="../js/tool.js"></script>        
    <script type="text/javascript">
    
    $(window).load(function () {
        $(".first").each(function () {
            var rows = $(".first:contains('" + $(this).text() + "')");
            if (rows.length > 1) {
                rows.eq(0).attr("rowspan", rows.length);
                rows.not(":eq(0)").remove(); 
            } 
        });
    });
    
    </script>

</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

<ASIDE style='float: left;'>
    <A href='../category/list.do'>카테고리 목록</A>
</ASIDE>    

<form name='frm' id='frm' method="get" action="./list_order_search_paging.do">
<input type='hidden' name='memberno' id='memberno' value='${memberVO.memberno }'>
<aside style="margin-left: 80%; vertical-align: middle;">
<c:choose>
      <c:when test="${param.status != '' }">
        <select id="status" name="status" style='width: 60%; border: 2px solid #cccccc;'>
        <option value="">주문처리상태</option>
        <option value="1">결제 완료</option>
        <option value="2">결제 취소</option>
        </select>
      </c:when>
      <c:otherwise>
        <select id="status" name="status" style='width: 60%; border: 2px solid #cccccc;'>
        <option value="">주문처리상태</option>
        <option value="1">결제 완료</option>
        <option value="2">결제 취소</option>
        </select>
      </c:otherwise>
    </c:choose>
        <button type='submit' style="right: 20px;">조회</button>
 </aside>   
</form>

<form name='frm' id='frm'>

<TABLE class='table_basic1'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
  </colgroup>
 
  <thead>  
  <TR style="border-bottom: 1px solid #cccccc; ">
    <TH style='text-align: center ;' rowspan='${cnt}'>주문일자<BR>[주문번호]<br><br></TH>
    <TH style='text-align: center ;'>이미지<br><br></TH>
    <TH style='text-align: center ;'>상품정보<br><br></TH>
    <TH style='text-align: center ;'>수량<br><br></TH> 
    <TH style='text-align: center ;'>상품구매금액<br><br></TH>
    <TH style='text-align: center ;'>주문처리상태<br><br></TH>
  </TR>
  </thead>

  <c:forEach var="Order_JoinVO" items="${list }" varStatus="status">
  <TR>
    <TD style='text-align: center; border-bottom: 1px solid #cccccc;' class='first'>
    <c:choose>
    <c:when test="${sessionScope.mid!=null}">
      <a href='./lookup.do?ordernum=${Order_JoinVO.ordernum }&status=${Order_JoinVO.status}&odate=${Order_JoinVO.odate.substring(0, 10)}&memberno=${Order_JoinVO.memberno}'>
      ${Order_JoinVO.odate.substring(0, 10)}<BR>[${Order_JoinVO.ordernum }]</a>
    </c:when>
    </c:choose>  
    </TD>
    <TD style='text-align: center; border-left: 1px solid #cccccc;'  class='td_list_basic1'>
            <c:choose>
              <c:when test="${Order_JoinVO.thumbs != ''}">
                <IMG id='thumbs' src='../contents/storage/${Order_JoinVO.thumbs }' style='width: 30%'> <!-- 이미지 파일명 출력 -->
              </c:when>
              <c:otherwise>
                <!-- 파일이 존재하지 않는 경우 -->
                <IMG src='../contents/images/none3.png' style='width: 120px; height: 80px;'>
              </c:otherwise>
            </c:choose>
    </TD>
    <TD style='text-align: center;'  class='td_list_basic1'>${Order_JoinVO.title}</TD>
    <TD style='text-align: center;'  class='td_list_basic1'>${Order_JoinVO.b_num }</TD>
    <TD style='text-align: center;'  class='td_list_basic1'>${Order_JoinVO.n_price }</TD>
    <TD style='text-align: center;'  class='td_list_basic1'>
    <c:set var="if_status" value="${Order_JoinVO.status }"/>
       <c:choose>
       <c:when test="${if_status eq '1'}">결제 완료
       </c:when>
       <c:when test="${if_status eq '2'}">결제 취소
       </c:when>
       </c:choose>
    </TD>
  </TR>
  </c:forEach>
</TABLE>
<DIV class='bottom_menu'>${paging }</DIV> <!--  페이지 목록 출력 -->
</form>
 
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- content END -->
</DIV> <!-- container END -->
 
</body>
</html>