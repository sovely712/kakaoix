<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>컨텐츠 목록</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>

<script type="text/javascript">

$(function() {
	  
	  $('#check_all').click(function(){
	    check_all();
	 });
	});

	//체크박스 전체선택
	function check_all(){
	  
	  if($('#check_all').is(":checked") == true){
	    $("input[name='check']").attr("checked", true);
	    $("input[name='check_all']").attr("checked", true);
	  }else{
	    $("input[name='check']").attr("checked", false);
	    $("input[name='check_all']").attr("checked", false);
	  }
	  
	}

	function check() {
	     var checkboxValues = "";
	     var frm_many_create = $('#frm_many_create');
	     
	      $("input[name='check']").each(function(i) {
	        if($(this).is(":checked") == true){
	          checkboxValues += ($(this).val() + "/");
	        }

	      });
	          
	    
	    $('#checkboxValues', frm_many_create).val(checkboxValues);
	    
	    var panel = '';
	    
	    if(checkboxValues==''){
	      panel += "<DIV id='panel' class='popup1' style='height: 150px; line-height: 30px; position: fixed; right: 20px;'>";
	      panel += "  장바구니에 넣을 상품을 선택하세요<br>";
	      panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 취소 </button>";
	      panel += "</DIV>";
	      
	    }else{
	      panel += "<DIV id='panel' class='popup1' style='height: 150px; line-height: 30px; position: fixed; right: 20px;'>";
	      panel += "  장바구니에 담으시겠습니까?<br>";
	      panel += "  <button type='button' onclick='frm_many_create.submit();' class='popup_button'> 확인 </button>";
	      panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 취소 </button>";
	      panel += "</DIV>";
	      
	    }
	    
	    $('#main_panel').html(panel);
	    $('#main_panel').show();
	  } 

	//basket 등록 controller로 보내기 
	function basket(contentsno) {
	  var frm_create = $('#frm_create');

	 $('#b_num', frm_create).val(1);
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
	function basket_X(contentsno) {
	    
	    alert("재고가 없습니다.");
	       
	} 

  function order_X(contentsno) {
    
     alert("재고가 없습니다.");
     
   }

  //주문서 작성 contents_read controller로 보내기 
  function order2(contentsno) {
    
      var frm_order_used = $('#frm_order_used');
      var b_num = $('#b_num',frm_order_used).val();
      
      var param1='contentsno='+contentsno;
      var param2='&b_num='+b_num;
     
      location.href="../order/contents_read.do?"+param1+param2;
   }

</script>

<script type="text/javascript">
</script>
</head>

<body>
<DIV class='container' >
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content' >

  <DIV id='main_panel'></DIV>

  <DIV id='panel_many_create'  style='display: none;'>
    <FORM name='frm_many_create' id='frm_many_create' method='POST' action='../basket/many_create.do'>
      <input type='text' name='checkboxValues'  id='checkboxValues'  value=''  >

    </FORM>
  </DIV>
  
  <!-- 장바구니 주문 -->
  <DIV id='panel_create'  style='display: none; '>
    <FORM name='frm_create' id='frm_create' method='POST' action='../basket/create.do'>
      <input type='number' name='b_num'  id='b_num'  value='' >
      <input type='number' name='contentsno'  id='contentsno'  value=''  >
      <input type='text' name='visible'  id='visible'  value='Y'  >
    </FORM>
  </DIV>

  <!-- 바로구매 주문 -->
  <FORM id='frm_order_used' name='frm_order_used'>
  <input type='hidden' name='b_num' id='b_num' value='1'>
  </FORM>
        
  <form name='frm' id='frm' method="get" action="./list.do">
  <ASIDE style='float: left;'>
    <A href='../category/list.do'>카테고리 목록</A>
  </ASIDE>
  
  <ASIDE style='float: right;'>
    <A href="javascript:location.reload();"><IMG src='./images/F5.png'  style='width: 17px; height: 17px;' title="새로고침"></A>
    
    <span class='menu_divide' >│</span> 
      <A href='./create.do?categoryno=${categoryVO.categoryno }'><IMG  src='./images/create.png'  style='width: 17px; height: 17px;' title="등록"></A>
    <span class='menu_divide' >│</span> 
      <A href="javascript:check();" ><img style='width: 17px; height: 17px;' src="./images/cart.png" title="장바구니" border='0'/></A>

    <c:choose>
      <c:when test="${param.word != '' }">
        <input type='text' name='word' id='word' value='${param.word }' style='width: 30%; border: 2px solid #cccccc;'>
      </c:when>
      <c:otherwise>
        <input type='text' name='word' id='word' value='' >
      </c:otherwise>
    </c:choose>
    <button type='submit'>검색</button>
    <button type='button'  onclick="location.href='./list.do?contentsno=${contentsVO.contentsno }'">전체 보기</button>
  </ASIDE>
  </form>
    
  <TABLE class='table_basic1'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
  </colgroup>
  
  <thead>  
  <TR style="border-bottom: 1px solid #cccccc; ">
    <TH style='text-align: center ;'>컨텐츠 번호<br><br></TH>
    <TH style='text-align: center ;'>이미지<br><br></TH>
    <TH style='text-align: center ;'>상품정보<br><br></TH>
    <TH style='text-align: center ;'>가격<br><br></TH> 
    <TH style='text-align: center ;'>주문<br><br></TH>
    <TH style='text-align: center ;'>재고량<br><br></TH>
  </TR>
  </thead>
 
 
  <c:forEach var="contentsVO" items="${list }">
  
  <TR>
    <TD style='text-align: center; border: none; padding-left: 20px;' >
          <div>
          <c:choose>
            <c:when test="${contentsVO.num != 0 }">
            <input type="checkbox" id="check" name="check" value="${contentsVO.contentsno }">
          </div>${contentsVO.contentsno }
            </c:when>
            <c:otherwise>
          </div>${contentsVO.contentsno }
            </c:otherwise>
          </c:choose>
    </TD>
    <TD style='vertical-align: middle;'>
          <div style="display: block; text-align: center;">
              <c:choose>
                <c:when test="${fn:length(fn:trim(contentsVO.thumbs)) > 0 }">
                  <IMG id='thumbs' src='./storage/${contentsVO.thumbs }' style='width: 110px; height: 130px; box-shadow:5px 5px 5px silver; '> <!-- 이미지 파일명 출력 -->
                </c:when>
                <c:otherwise>
                  <!-- 파일이 존재하지 않는 경우 -->
                  <IMG src='./images/none3.png' style='width: 130px; height: 130px;' >
                </c:otherwise>
              </c:choose>
              </div>
    </TD>
    <TD style='text-align: center; border: none;' class="td_list_basic" >
    <A href='../contents/read.do?contentsno=${contentsVO.contentsno }&categoryno=${contentsVO.categoryno}'>${contentsVO.title}</A>
    </TD>
    <TD style='text-align: center; border: none;' class="td_list_basic" >${contentsVO.price }</TD>
    <TD style='text-align: center; border: none;' class="td_list_basic">
            <c:choose>  
               <%--로그인 안했을 경우 로그인 페이지로 이동  --%>
               <c:when test="${sessionScope.mid == null }">
                  <A href='../member/login_need.jsp'>
                  <button type="button" onclick="order2(${contentsVO.contentsno})" id="order2">바로구매</button><br><br>
                  <button type="button" onclick="basket(${contentsVO.contentsno})" id="basket">장바구니</button>
                  </A>
               </c:when>
               <%--로그인 성공,재고량이 0이 아닐경우->주문 --%>
               <c:when test="${sessionScope.mid != null && contentsVO.num != 0 }">
                  <button type="button" onclick="order2(${contentsVO.contentsno})" id="order2">바로구매</button><br><br>
                  <button type="button" onclick="basket(${contentsVO.contentsno})" id="basket">장바구니</button>
               </c:when>
               <%--로그인 성공,재고량이 0일 경우->알림 띄우기 --%>
               <c:when test="${sessionScope.mid != null && contentsVO.num == 0 }">
                  <button type="button" onclick="order_X(${contentsVO.contentsno})" id="order_X">바로구매</button><br><br>
                  <button type="button" onclick="basket_X(${contentsVO.contentsno})" id="basket_X">장바구니</button>
               </c:when>
           </c:choose>    
    </TD>
 
    
    <TD style='text-align: center; border: none;' class="td_list_basic">
    <c:choose>
      <c:when test="${contentsVO.num != 0 }">
        ${contentsVO.num }
      </c:when>
      <c:otherwise>
        <img src="./images/soldout.png" title="재고 없음" border='0' style="width: 25px; height: 25px; "/>
      </c:otherwise>
    </c:choose>  
    </TD>
  </TR>
  </c:forEach>
</TABLE>

  <DIV class='bottom_menu'>${paging }</DIV> <!--  페이지 목록 출력 -->
  <br><br>


<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>

</html>