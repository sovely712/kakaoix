<%@page import="dev.mvc.tool.Tool"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>상품 정보</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">

//장바구니 등록 컨트롤러로 보내기
function basket() {
    var frm = $('#frm');
    var frm_create = $('#frm_create');
    var contentsno = ${contentsVO.contentsno };
    var b_num = $('#b_num', frm).val();
    
   $('#b_num', frm_create).val(b_num);
   $('#contentsno', frm_create).val(contentsno);
       
   var panel = '';
   panel += "<DIV id='panel' class='popup1' style='height: 150px; line-height: 30px; position: fixed; right: 20px;'>";
   panel += "  장바구니에 담으시겠습니까?<br>";
   panel += "  <button type='button' onclick='frm_create.submit();' class='popup_button'> 확인 </button>";
   panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 취소 </button>";
   panel += "</DIV>";
   
   $('#main_panel').html(panel);
   $('#main_panel').show();
     
   }

function basket_Y() {
    
    alert("재고가 없습니다.");
}

function order_Y() {
    
    alert("재고가 없습니다.");
}

//주문 등록 컨트롤러로 보내기
function order1() {
  var frm = $('#frm');
  var b_num = $('#b_num', frm).val();
  var param1='contentsno='+${contentsVO.contentsno};
  var param2='&b_num='+b_num;
 
  location.href="../order/contents_read.do?"+param1+param2;
  
}


</script>
</head>

<body>
<DIV class='container' >
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>   

  <DIV id='main_panel'></DIV>
  
  <DIV id='panel_create'  style='display: none; '>
    <FORM name='frm_create' id='frm_create' method='POST' action='../basket/create.do'>
      <input type='number' name='b_num'  id='b_num'  value='' >
      <input type='text' name='visible'  id='visible'  value='Y' >
      <input type='number' name='contentsno'  id='contentsno'  value='' >
    </FORM>
  </DIV>
       
  <ASIDE style='float: left;'>
    <A href='./list.do?categoryno=${contentsVO.categoryno }'>카테고리 목록</A><span class='top_menu2'> | </span>
    <A href='./list.do?categoryno=${contentsVO.categoryno }'>상품정보 목록</A>
  </ASIDE>
  
<FORM name='frm' id='frm' method="get" action='./read.do'>
    <input type="hidden" name="contentsno" value="${contentsVO.contentsno}">
    <input type="hidden" name="categoryno" value="${contentsVO.categoryno}">
    
    <TABLE class='table_basic' style="vertical-align: middle; width: 80%;" >
    <colgroup>
    <col style='width: 50%;'/>
    <col style='width: 50%;'/>
    </colgroup>
    
  <thead> 
  <TR>
    <TH style='text-align: center ;' class="th_basic">PRODUCT</TH>
    <TH style='text-align: center ;' class="th_basic">INFO</TH>
  </TR>
  </thead>
       <tr>
              <td style="text-align: center; 
                    vertical-align: middle; 
                    border: 1px dashed #35221a;
                    border-left: none;
                    border-top: none;
                    border-bottom: none;" >
              <c:set var='files' value="${fn:toLowerCase(contentsVO.files)}"  />
              <c:choose>
                <c:when test="${fn:endsWith(files, '.jpg')}">
                  <IMG id='files' src='./storage/${contentsVO.files}'  style='width: 380px; height: 480px; box-shadow:10px 10px 10px silver;'  >
                </c:when>
                <c:when test="${fn:endsWith(files, '.gif')}">
                  <IMG id='files'  src='./storage/${contentsVO.files}'  style='width: 380px; height: 480px; box-shadow:10px 10px 10px silver;' >
                </c:when>
                <c:when test="${fn:endsWith(files, '.png')}">
                  <IMG id='files'  src='./storage/${contentsVO.files}'  style='width: 380px; height: 480px; box-shadow:10px 10px 10px silver;' >
                </c:when>
                <c:otherwise>
                    <IMG src='./images/none3.png' style='width: 500px; height: 480px; ' >
                 </c:otherwise>
              </c:choose>
              </td>
              
          <td style="padding: 40px; vertical-align: middle; padding-top: 70px; padding-left: 59px;">
          <div style="padding-bottom: 3px;"><b>상품 이름 : </b>${contentsVO.title}</div>
          <div style="padding-bottom: 3px;"><b>가격  : </b>${contentsVO.price }</div>
          <div style="padding-bottom: 3px;"><b>수량  : </b>
          <input type='number' name='b_num' id='b_num' value='1' placeholder="${b_num}" min="1" max="100" step="1" style="width: 40px;">
          </div>
          <div style="padding-bottom: 3px;"><b>카테고리 : </b>${categoryVO.title}</div>
          <div><b>재고량 : </b>
          <c:choose>
            <c:when test="${contentsVO.num != 0 }">
            ${contentsVO.num}권
            </c:when>
            <c:otherwise>
            <img src="./images/soldout.png" title="재고 없음" border='0' style="width: 25px; height: 25px; "/>
            </c:otherwise>
          </c:choose>
          </div><br>
          
          <div><b>등록일 : </b>${contentsVO.cdate.substring(0, 16)}<b style="margin-left: 50px;">조회 : </b>${contentsVO.cnt}</div>
          <div><br>
          <fieldset style="border: 1px solid #35221a; padding: 10px;">
          <legend style="text-align: center;"><b>&nbsp;내용&nbsp;</b></legend>
           <span>${contentsVO.content}</span>
          </fieldset>
          </div><br><br>
          
          <div style="text-align:center">
          <c:choose>  
               <%--로그인 안했을 경우 로그인 페이지로 이동  --%>
               <c:when test="${sessionScope.mid == null }">
                  <A href='../member/login_need.jsp'>
                  <button type="button" onclick="javascript:order1()" id="order">바로구매</button>
                  <button type="button" onclick="javascript:basket()" id="btn_basket">장바구니</button><br><br>
                  </A>
               </c:when>
               <%--로그인 성공,재고량이 0이 아닐경우->주문 --%>
               <c:when test="${sessionScope.mid != null && contentsVO.num != 0 }">
                  <button type="button" onclick="javascript:order1()" id="order">바로구매</button>
                  <button type="button" onclick="javascript:basket()" id="btn_basket">장바구니</button><br><br>
               </c:when>
               <%--로그인 성공,재고량이 0일 경우->알림 띄우기 --%>
               <c:when test="${sessionScope.mid != null && contentsVO.num == 0 }">
                  <button type="button" onclick="javascript:order_Y()" id="order">바로구매</button>
                  <button type="button" onclick="javascript:basket_Y()" id="btn_basket">장바구니</button><br><br>
               </c:when>
           </c:choose>    
          <div>
          </td>
      </tr>
  </table>
</FORM>
      

<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
</html>

