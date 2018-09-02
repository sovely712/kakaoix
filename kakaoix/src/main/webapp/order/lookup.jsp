<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>주문 상세 조회</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<script type="text/JavaScript" src="../js/tool.js"></script>        
   <script type="text/javascript">
   function button1_click(){
	   
    	var lookup_status_frm = $('#lookup_status_frm');
    	var cancel_status_frm = $('#cancel_status_frm');
    	
    	var lookup_memberno = $('#lookup_memberno',lookup_status_frm).val();
    	var lookup_odate = $('#lookup_odate',lookup_status_frm).val();	
    	var lookup_ordernum = $('#lookup_ordernum',lookup_status_frm).val();
    	var lookup_status = "2";
    	
    	console.log("lookup_memberno:"+lookup_memberno);
    	console.log("lookup_odate:"+lookup_odate);
    	console.log("lookup_ordernum:"+lookup_ordernum);
    	console.log("lookup_status:"+lookup_status);
    	
     	$('#status',cancel_status_frm).val(lookup_status);
    	$('#ordernum',cancel_status_frm).val(lookup_ordernum);
    	$('#odate',cancel_status_frm).val(lookup_odate);
    	$('#memberno',cancel_status_frm).val(lookup_memberno);
    	
       var panel = '';
        
          panel += "<DIV id='panel' class='popup1' style='height: 150px; line-height: 30px; position: fixed; right: 20px;'>";
          panel += "  정말 결제 취소 하시겠습니까?<br>";
          panel += "  <button type='button' onclick='cancel_status_frm.submit();' class='popup_button'> 확인 </button>";
          panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 취소 </button>";
          panel += "</DIV>";
      
        $('#main_panel').html(panel);
        $('#main_panel').show();  
    } 
   
    </script>

</head>
<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

<DIV id='main_panel'></DIV> 
 
<FORM name='cancel_status_frm' id='cancel_status_frm' method='POST' action='../order/updateStatus.do'>
<input type='hidden' name='status' id='status' value=''>
<input type='hidden' name='ordernum' id='ordernum' value=''>
<input type='hidden' name='odate' id='odate' value=''>
<input type='hidden' name='memberno' id='memberno' value=''>
<input type='hidden' name='tot_price' id='tot_price' value='${tot_price}'>
<input type='hidden' name='dir_price' id='dir_price' value='${dir_price}'>
<input type='hidden' name='totdir_price' id='totdir_price' value='${totdir_price}'>
<input type='hidden' name='dc_price' id='dc_price' value='${dc_price}'>
<input type='hidden' name='b_price' id='b_price' value='${b_price}'>
<input type='hidden' name='dname' id='dname' value='${dname}'>
<input type='hidden' name='dzipcode' id='dzipcode' value='${dzipcode}'>
<input type='hidden' name='daddress' id='daddress' value='${daddress}'>
<input type='hidden' name='dtel' id='dtel' value='${dtel}'>
<input type='hidden' name='dcontent' id='dcontent' value='${dcontent}'>
</FORM>

<FORM name='lookup_status_frm' id='lookup_status_frm'>
<input type='hidden' name='lookup_status' id='lookup_status' value='${status}'>
<input type='hidden' name='lookup_ordernum' id='lookup_ordernum' value='${ordernum}'>
<input type='hidden' name='lookup_odate' id='lookup_odate' value='${odate}'>
<input type='hidden' name='lookup_memberno' id='lookup_memberno' value='${memberno}'>
</FORM>

<DIV class='title_line2'>주문정보</DIV>
  <TABLE class='table_basic' style="vertical-align: middle; width: 80%;">
      
      <colgroup>
       <col style='width: 15%;'/>
       <col style='width: 85%;'/>
      </colgroup>
  
  <tbody>
      <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>주문번호</label>
             </div>
            </td>
            <td class="td_basic2">
                 ${ordernum }
            </td>
      </tr> 
      
      <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>주문일자</label>
             </div>
            </td>
            <td class="td_basic2">
                 ${odate }
            </td>
      </tr> 
      
      <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>주문자</label>
             </div>
            </td>
            <td class="td_basic2">
                ${memberVO.mname }
            </td>
      </tr> 
      
      <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>주문처리상태</label>
             </div>
            </td>
            <td class="td_basic2">
               <c:set var="if_status" value="${status }"/>${status_label}
               <c:choose>
               <c:when test="${if_status eq '1'}">
               <button type="button" id="button1" onclick="button1_click();">결제 취소</button>
               </c:when>
               </c:choose>
            </td>
      </tr> 
  </tbody>
</TABLE> 

<br>
<DIV class='title_line2'>결제정보</DIV>
  <TABLE class='table_basic' style="vertical-align: middle; width: 80%;">
      
      <colgroup>
       <col style='width: 15%;'/>
       <col style='width: 85%;'/>
      </colgroup>
  
  <tbody>
   
     <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>총 주문 금액</label>
             </div>
            </td>
            <td class="td_basic2">
                ${tot_price }
            </td>
       </tr>
       
       <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>배송비</label>
             </div>
            </td>
            <td class="td_basic2">
                ${dir_price }
            </td>
       </tr>   
       
       <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>총 합계</label><!--총 합계(총주문금액+배송비) -->
             </div>
            </td>
            <td class="td_basic2">
                ${totdir_price }
            </td>
       </tr>
       
       <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>할인 금액</label>
             </div>
            </td>
            <td class="td_basic2">
                ${dc_price }
            </td>
       </tr>  
       
       <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>총 결제 금액</label>
             </div>
            </td>
            <td class="td_basic2">
                ${b_price}
            </td>
       </tr>
       
       <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>결제 수단</label>
             </div>
            </td>
            <td class="td_basic2">
                                      무통장 입금
            </td>
       </tr>
  </tbody>
</TABLE> 

<br>
<DIV class='title_line2'>배송정보</DIV>
  <TABLE class='table_basic' style="vertical-align: middle; width: 80%;">
      
      <colgroup>
       <col style='width: 15%;'/>
       <col style='width: 85%;'/>
      </colgroup>
      
        <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>받으시는분</label>
             </div>
            </td>
            <td class="td_basic2">
               ${dname}        
            </td>
         </tr>   
         
         <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>우편 번호</label>
             </div>
            </td>
            <td class="td_basic2">
               ${dzipcode}        
            </td>
         </tr> 
         
         <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>주소</label>
             </div>
            </td>
            <td class="td_basic2">
               ${daddress}        
            </td>
         </tr> 
         
         <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>휴대전화</label>
             </div>
            </td>
            <td class="td_basic2">
               ${dtel}        
            </td>
         </tr> 
         
          <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>배송메시지</label>
             </div>
            </td>
            <td class="td_basic2">
               ${dcontent}        
            </td>
         </tr> 
  </tbody>
</TABLE>  

<br>
<DIV class='title_line2'>주문 상품 정보</DIV>
  <TABLE class='table_basic' style="vertical-align: middle; width: 80%;">
      
    <colgroup>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
  </colgroup>
  
  <tbody>
  <thead>  
  <TR>
    <TH style='text-align: center ;' class="td_list_basic">이미지</TH>
    <TH style='text-align: center ;' class="td_list_basic">상품정보</TH>
    <TH style='text-align: center ;' class="td_list_basic">수량</TH>
    <TH style='text-align: center ;' class="td_list_basic">판매가</TH>
    <TH style='text-align: center ;' class="td_list_basic">주문처리상태</TH>
  </TR>
  </thead>
 
  <c:forEach var="Order_JoinVO" items="${list }">
  <TR>
    <TD style='text-align: center;' class="td_list_basic">
            <c:choose>
              <c:when test="${Order_JoinVO.thumbs != ''}">
                <IMG id='thumbs' src='../contents/storage/${Order_JoinVO.thumbs }' style='width: 25%'> <!-- 이미지 파일명 출력 -->
              </c:when>
              <c:otherwise>
                <!-- 파일이 존재하지 않는 경우 -->
                <IMG src='../contents/images/none3.png' style='width: 120px; height: 80px;'>
              </c:otherwise>
            </c:choose>
    </TD>
    <TD style='text-align: center;' class="td_list_basic">${Order_JoinVO.title}</TD>
    <TD style='text-align: center;' class="td_list_basic">${Order_JoinVO.b_num }</TD>
    <TD style='text-align: center;' class="td_list_basic">${Order_JoinVO.price }</TD>
    <TD style='text-align: center;' class="td_list_basic">
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
 
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- content END -->
</DIV> <!-- container END -->
 
</body>
</html>