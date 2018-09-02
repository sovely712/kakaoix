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

<script type="text/javascript">
$(function() {
  var checkboxValues = "";
  
  $('#btn_delete').click(function(){
    check_delete();
 });

  
  //체크박스 클릭할때마다 함수 실행
  $("input[name='ck_basketno']").click(function(){
    check_each(checkboxValues);    
 
   });
});

//체크된 체크박스의 장바구니 넘버 문자열로 붙여서 check 실행
function check_each(checkboxValues){
  $("input[name='ck_basketno']").each(function(i) {
    if($(this).is(":checked") == true){
      checkboxValues += ($(this).val() + "/");
    }
    check(checkboxValues);
  });
}

//체크박스 전체선택
function check_basketno_all(){
  var checkboxValues = "";
  console.log("check");
  console.log("check: "+ $('#ck_basketno_all').is(":checked"));
  if($('#ck_basketno_all').is(":checked") == true){
   // $("input[name='ck_basketno']").attr("checked", true);
    $("input[name='ck_basketno']").attr("checked", true);
  }else{
    //$("input[name='ck_basketno']").attr("checked", false);
    $("input[name='ck_basketno']").attr("checked", false);
  }
  check_each(checkboxValues);
}


//선택된 장바구니넘버 컨트롤러로 보내기
function check(checkboxValues) {
  $.ajax({
    url: "../basket/check.do", // 요청을 보낼주소
    type: "get",  // or get
    cache: false,
    dataType: "json",
    data: "checkboxValues=" + checkboxValues ,  
    // Ajax 통신 성공, JSP 정상 처리
    success: function(rdata) { // callback 함수
      $('#sum').html(rdata.sum);
      $('#cnt').html(rdata.cnt);
     
    },
    // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
    error: function(request, status, error) { // callback 함수
      console.log("check error");
      var panel = '';
      panel += "<DIV id='panel' class='popup1' style='height: 150px; position: fixed; right: 20px;'>";
      panel += '  ERROR<br><br>';
      panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
      panel += '  <strong>error</strong><br>'+error + '<hr>';
      panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
      panel += "</DIV>";
      
      $('#main_panel').html(panel);
      $('#main_panel').show();

    }
  });
} 

//장바구니 1개이상 선택하여 삭제하기
function check_delete() {
    var frm_many_delete = $('#frm_many_delete');    
    console.log("check_delete");
    var checkboxValues = "";
   
   $("input[name='ck_basketno']").each(function(i) {
     if($(this).is(":checked") == true){
       checkboxValues += ($(this).val() + "/");
     }
    });
  
  $('#checkboxValues', frm_many_delete).val(checkboxValues);
 
   var panel = '';
   
   if(checkboxValues==''){
     panel += "<DIV id='panel' class='popup1' style='height: 150px; position: fixed; right: 20px;'>";
     panel += "  삭제할 상품을 선택하세요<br>";
     panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 닫기 </button>";
     panel += "</DIV>";
     
   }else{
     panel += "<DIV id='panel' class='popup1' style='height: 150px; position: fixed; right: 20px;'>";
     panel += "  삭제하시겠습니까?<br>";
     panel += "  <button type='button' onclick='frm_many_delete.submit();' class='popup_button'> 확인 </button>";
     panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 취소 </button>";
     panel += "</DIV>";
     
   }
   
   $('#main_panel').html(panel);
   $('#main_panel').show();
   

} 
 //삭제시 pannel 띄우기
 function deletePanel(basketno) { 
   console.log("panel basketno: " + basketno);
   var panel = '';
   panel += "<DIV id='panel' class='popup1' style='height: 150px; line-height: 30px; position: fixed; right: 20px;'>";
   panel += "  장바구니를 삭제합니다.<br>";
   panel += "  삭제하면 복구 할 수 없습니다.<br><br>";
   panel += "  <button type='button' onclick='deleteOne("+basketno+")' class='popup_button'> 삭제 </button>";
   panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 닫기 </button>";
   panel += "</DIV>";
   
   $('#main_panel').html(panel);
   $('#main_panel').show();
   
 }
 
 //삭제처리 ajax
 function deleteOne(basketno){
   $('#main_panel').hide();
   
   $.ajax({
     url: "../basket/delete.do", // 요청을 보낼주소
     type: "post",  // or get
     cache: false,
     dataType: "html", // 응답 데이터 형식, or json
     data: 'basketno=' +basketno,  // $('#frm').serialize(), 
     // Ajax 통신 성공, JSP 정상 처리
     success: function(rdata) { // callback 함수
       console.log("delete success");
       location.reload(); //새로고침
     },
     // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
     error: function(request, status, error) { // callback 함수
       console.log("delete error");
       var panel = '';
       panel += "<DIV id='panel' class='popup1' style='height: 150px; position: fixed; right: 20px;'>";
       panel += '  ERROR<br><br>';
       panel += '  <strong>request.status</strong><br>'+request.status + '<hr>';
       panel += '  <strong>error</strong><br>'+error + '<hr>';
       panel += "  <br><button type='button' onclick=\"$('#main_panel').hide();\">닫기</button>";
       panel += "</DIV>";
       
       $('#main_panel').html(panel);
       $('#main_panel').show();

     }
   });
   
 }
 
 //basketno를 "/"로 합쳐서 주문 작성 controller(basket_read.do)로 보내기 
 function check_order() {
      console.log("count");
      var checkOrder = "";
      var memberno = 1;
      var frm_basket_order = $('#frm_basket_order');
      
       $("input[name='ck_basketno']").each(function(i) {
         if($(this).is(":checked") == true){
           checkOrder += ($(this).val() + "/");
         }

       });
           
     console.log(checkOrder);
     
     $('#checkOrder', frm_basket_order).val(checkOrder);
     $('#memberno', frm_basket_order).val(memberno);
     
     var panel = '';
     
     if(checkOrder==''){
       panel += "<DIV id='panel' class='popup1' style='height: 150px; position: fixed; right: 20px;'>";
       panel += "  주문하실 상품을 선택해주세요<br>";
       panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 닫기 </button>";
       panel += "</DIV>";
       
     }else{
       panel += "<DIV id='panel' class='popup1' style='height: 150px; position: fixed; right: 20px;'>";
       panel += "  주문하시겠습니까?<br>";
       panel += "  <button type='button' onclick='frm_basket_order.submit();' class='popup_button'> 확인 </button>";
       panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 취소 </button>";
       panel += "</DIV>";
       
     }

     $('#main_panel').html(panel);
     $('#main_panel').show();
     
   } 

 </script>
 
 
 
</head> 
 
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

  <DIV id="test"></DIV>
  <DIV id='main_panel'></DIV>

  <FORM name='frm_many_delete' id='frm_many_delete' method='post' action='./many_delete.do'>
    <input type='hidden' name='checkboxValues' id='checkboxValues' value=''>
    <input type='hidden' name='memberno' id='memberno' value='1'>
    <input type='hidden' name='redirect' id='redirect' value='0'>
  </FORM>
  
   <FORM name='frm_basket_order' id='frm_basket_order' method='POST' action='../order/basket_read.do'>
      <input type='hidden' name='checkOrder'  id='checkOrder'  value=''  >
      <input type='hidden' name='memberno'  id='memberno'  value='' >
   </FORM>
 
  <ASIDE style='float: left; margin-right: 10px;'>
    <A href='./list_by_member.do'>장바구니</A>( 총 ${cnt } 개 ) &nbsp;
    <span class='menu_divide' >│</span> 
  </ASIDE>
  
  <ASIDE style='float: left;'>&nbsp;
    <input type='checkbox' name='ck_basketno_all'  id='ck_basketno_all'  onclick='check_basketno_all()'>&nbsp;전체선택   
    <button type="button" id="btn_delete">삭제</button>
</ASIDE>


<TABLE class='table_basic'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 30%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
  </colgroup>
 
<thead> 
  <TR>
    <TH style='text-align: center ;' class="td_list_basic"></TH>
    <TH style='text-align: center ;' colspan='2' class="td_list_basic">이미지</TH>
    <TH style='text-align: center ;' class="td_list_basic">가격</TH>
    <TH style='text-align: center ;' class="td_list_basic">수량</TH>
    <TH style='text-align: center ;' class="td_list_basic">삭제</TH>
  </TR>
  </thead>

    <c:forEach var="Contents_BasketVO" items="${list }" varStatus="status">
    <TR>
      <TD style='text-align: center;' class="td_list_basic">
        <input type='checkbox' name='ck_basketno'  id='ck_basketno'  value='${Contents_BasketVO.basketno }' >
      </TD>
      <TD style='vertical-align: middle;' class="td_list_basic">
            <c:choose>
              <c:when test="${Contents_BasketVO.thumbs != ''}">
                <IMG id='thumbs' src='../contents/storage/${Contents_BasketVO.thumbs }'  style='width: 100px; height: 120px; box-shadow:5px 5px 5px silver;'> <!-- 이미지 파일명 출력 -->
              </c:when>
              <c:otherwise>
                <!-- 파일이 존재하지 않는 경우 -->
                <IMG src='../contents/images/none3.png' style='width: 100px; height: 120px; box-shadow:5px 5px 5px silver;'>
              </c:otherwise>
            </c:choose>
           
       </TD>
      <TD style='text-align: center;' class="td_list_basic">${Contents_BasketVO.title }</TD>
      <TD style='text-align: center;' class="td_list_basic">${Contents_BasketVO.price }</TD>
      <TD style='text-align: center;' class="td_list_basic">${Contents_BasketVO.b_num }</TD>
      <TD style='text-align: center;' class="td_list_basic">
      <A href="javascript:deletePanel(${Contents_BasketVO.basketno })"><IMG src='./images/delete.png'  style='width: 17px; height: 17px;' title='삭제'></A>
      </TD>
    </TR>
    </c:forEach>
</TABLE>

<br>
<table class='table_basic' style="vertical-align: middle; width: 100%;">
      
      <colgroup>
       <col style='width: 30%;'/>
       <col style='width: 70%;'/>
      </colgroup>
  
  <tbody>
   
      <tr class="tr_basic">
            <td class="td_left_basic"  style="text-align: center;">
            <div>
                <label>총 주문 상품수</label>
             </div>
            </td>
            <td class="td_basic2">
                 <span id='cnt' style='font-size: 18px;'>0</span>
            </td>
      </tr> 
      
      <tr class="tr_basic">
            <td class="td_left_basic"  style="text-align: center;">
            <div>
                <label>총 상품가격</label>
             </div>
            </td>
            <td class="td_basic2">
                 <span id='sum' style='font-size: 18px;'>0</span>
            </td>
      </tr>
</TABLE>

    <br>
    <div style='text-align: center;'>
        <button type="button" onclick="location.href='../contents/list.do?categoryno=1'">쇼핑계속하기</button>
        <button type="button" onclick="check_order()">선택상품 주문하기</button>
   </div>
   <br>

<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
 
</html> 