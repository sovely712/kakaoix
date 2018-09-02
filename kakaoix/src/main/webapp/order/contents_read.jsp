<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>주문서 작성</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<script type="text/JavaScript" src="../js/tool.js"></script>    
    <script type="text/javascript">
     var ajax = new XMLHttpRequest();
      
      $(function(){  
        price1();
        
        $("input[name='same_refresh']").click(function(){
            radio_check();
          });
      });
      
       function Enter_Check(){
        
        console.log("Enter_Check()실행 시작");
        var sum = ${sum};  
        if(event.keyCode == 13){
            //사용하려고 하는 적립금이 사용가능한 적립금보다 작으면 할인 컬럼에 사용하려고 하는 적립금 넣고 point_event()실행 
            //크다면 메세지 띄우기
            if($('#point_sum').val() <= sum ){
              $('#dc_price').val($('#point_sum').val());  
               point_event();  
            }else{
              alert("적립금이 부족합니다.\n적립금을 다시 입력해주세요.");
            }
            
           point_event();  
        }
      }
       
    function price1(){
      
      var frm_create = $('#frm_create');
      var n = ${b_num};             //상품 수량
      var p = ${contentsVO.price};  //상품 가격
      var n_price =n*p;             //수량별 가격
      var n_point = n_price*0.03;   //적립금
      var dir_pri = 0;              //배송비
      
      
      //수량별금액
      find('nprice').innerHTML = n_price;
      $('#n_price', frm_create).val(n_price); 
      //총주문금액
      find('total_price').innerHTML = n_price;   
      $('#tot_price', frm_create).val(n_price); 
      

      //가격별적립금
      find('bpoint').innerHTML = n_point;
      $('#n_point', frm_create).val(n_point);
      //총적립금
      find('total_point').innerHTML = n_point; 
      $('#b_point', frm_create).val(n_point);
       

      //배송비
      if(n_price<50000){  
        dir_pri =2500;
      }else{
        dir_pri =0;
      }
        
      find('dir_price').innerHTML = dir_pri;
      $('#dir_price', frm_create).val(dir_pri);
     
      //총주문금액+배송비
      var totdir_sum = n_price+dir_pri;
      $('#totdir_price', frm_create).val(totdir_sum);
      
      //총주문금액+배송비+할인(적립금 사용안했을때)
      var b_sum = n_price+dir_pri;
      find('b_price').innerHTML = b_sum;
      $('#b_price', frm_create).val(b_sum); 
      
    }
    
     function point_event(){
      
        var frm_create = $('#frm_create');
        var n = ${b_num};                   //상품 수량 
        var p = ${contentsVO.price};        //상품 가격
        var n_price =n*p;                   //수량별 가격
        var dir_pri = 0;                    //배송비
        
        if(n_price<50000){  
           dir_pri =2500;
        }else{
           dir_pri =0;
        }
        console.log("Enter_Check->point_event()실행 부분");
        
        //할인금액
        var discount_price = $('#point_sum').val();
        find('discount_price').innerHTML = discount_price;
        
        //총사용가능한 적립금 바꾸기
        var sum = ${sum};
        var point_total= sum-discount_price;
        find('point_total').innerHTML = point_total;
        
        //총주문금액+배송비+할인(적립금 사용했을때)
        var b_sum = n_price+dir_pri-discount_price; 
        find('b_price').innerHTML = b_sum;
        $('#b_price', frm_create).val(b_sum); 
      } 
      
    //배송정보 선택하기
    function radio_check() {
        
        var mname ="";
        var zipcode ="";
        var address1 ="";
        var address2 ="";
        var tel1 ="";
        var tel2 ="";
        var tel3 ="";
        var select_tel = new Array();
        
        var frm_same = $('#frm_same');
        
        $("input[id='same_refresh1']").each(function(i) {
              if($(this).is(":checked") == true){
                mname =$('#member_mname' ,frm_same).val();
                zipcode =$('#member_zipcode' ,frm_same).val();
                address1 =$('#member_address1' ,frm_same).val();
                address2 =$('#member_address2' ,frm_same).val();
                tel1 =$('#member_tel1' ,frm_same).val();
                tel2 =$('#member_tel2' ,frm_same).val();
                tel3 =$('#member_tel3' ,frm_same).val();
        
              }
            });
        
        $("input[id='same_refresh2']").each(function(i) {
            if($(this).is(":checked") == true){
              mname ='';
              zipcode ='';
              address1 ='';
              address2 ='';
              tel1='';
              tel2='';
              tel3='';
            }
          });
        
         $('#mname').val(mname);
         $('#zipcode').val(zipcode);
         $('#address1').val(address1);
         $('#address2').val(address2);
         $('#tel1').val(tel1);
         $('#tel2').val(tel2);
         $('#tel3').val(tel3);
      }

    //결제하기
    function order() {
       
        var frm_create = $('#frm_create');
        
        var dname = $('#mname').val();
        var dzipcode = $('#zipcode').val();
        var daddress1 = $('#address1').val();
        var daddress2 = $('#address2').val();
        var daddress = daddress1+daddress2;
        var tel1 = $('#tel1').val();
        var tel2 = $('#tel2').val();
        var tel3 = $('#tel3').val();
        var s = "-";
        var dtel = tel1+s+tel2+s+tel3; 
        var dcontent = $('#content').val();
        
       $('#dname', frm_create).val(dname);
       $('#dzipcode', frm_create).val(dzipcode);
       $('#daddress', frm_create).val(daddress);
       $('#dtel', frm_create).val(dtel);
       $('#dcontent', frm_create).val(dcontent);
       
       var panel = '';
       
       if(dname==''){
           panel += "<DIV id='panel' class='popup1' style='height: 150px; position: fixed; right: 20px;'>";
           panel += "  받으시는 분을 입력해주세요<br>";
           panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 닫기 </button>";
           panel += "</DIV>";
           
       }else if(dzipcode==''){
           panel += "<DIV id='panel' class='popup1' style='height: 150px; position: fixed; right: 20px;'>";
           panel += "  우편번호를 입력해주세요<br>";
           panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 닫기 </button>";
           panel += "</DIV>";
           
       }else if(daddress1==''||daddress2==''){
           panel += "<DIV id='panel' class='popup1' style='height: 150px; position: fixed; right: 20px;'>";
           panel += "  주소를 입력해주세요<br>";
           panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 닫기 </button>";
           panel += "</DIV>";
           
       }else if(tel1==''||tel2==''||tel3==''){
           panel += "<DIV id='panel' class='popup1' style='height: 150px; position: fixed; right: 20px;'>";
           panel += "  휴대 전화를 입력해주세요<br>";
           panel += "  <button type='button' onclick=\"$('#main_panel').hide();\" class='popup_button'> 닫기 </button>";
           panel += "</DIV>";
           
       }else{
          panel += "<DIV id='panel' class='popup1' style='height: 150px; line-height: 30px; position: fixed; right: 20px;'>";
          panel += "  결제하시겠습니까?<br>";
          panel += "  <button type='button' onclick='frm_create.submit();' class='popup_button'> 확인 </button>";
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
  <FORM name='frm_same' id='frm_same'>
    <input type='hidden' name="member_mname" id="member_mname" value='${memberVO.mname }'>
    <input type='hidden' name="member_zipcode" id="member_zipcode" value='${memberVO.zipcode }'>
    <input type='hidden' name="member_address1" id="member_address1" value='${memberVO.address1 }'>
    <input type='hidden' name="member_address2" id="member_address2" value='${memberVO.address2 }'>
    <input type='hidden' name="member_tel1" id="member_tel1" value='${tel1}'>
    <input type='hidden' name="member_tel2" id="member_tel2" value='${tel2}'>
    <input type='hidden' name="member_tel3" id="member_tel3" value='${tel3}'>
  </FORM>

<DIV class='title_line2'>주문내역</DIV>
  <TABLE class='table_basic' style="vertical-align: middle; width: 80%;">
  <colgroup>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
  </colgroup>
 
  <thead>  
  <TR>
    <TH style='text-align: center ;' class="td_list_basic">이미지</TH>
    <TH style='text-align: center ;' class="td_list_basic">상품 정보</TH>
    <TH style='text-align: center ;' class="td_list_basic">가격</TH>
    <TH style='text-align: center ;' class="td_list_basic">수량</TH>
    <TH style='text-align: center ;' class="td_list_basic">금액</TH>
    <TH style='text-align: center ;' class="td_list_basic">적립금</TH>
  </TR>
  </thead>
  
  <TR>
    <TD style='text-align: center;' class="td_list_basic">
            <c:choose>
              <c:when test="${contentsVO.thumbs != ''}">
                <IMG id='thumbs' src='../contents/storage/${contentsVO.thumbs }' style='width: 30%'> <!-- 이미지 파일명 출력 -->
              </c:when>
              <c:otherwise>
                <!-- 파일이 존재하지 않는 경우 -->
                <IMG src='../contents/images/noimage.jpg' style='width: 120px; height: 80px;'>
              </c:otherwise>
            </c:choose>
    </TD>
    <TD style='text-align: center;' class="td_list_basic">${contentsVO.title}</TD>
    <TD style='text-align: center;' class="td_list_basic">${contentsVO.price}</TD>
    <TD style='text-align: center;' class="td_list_basic">${b_num }</TD>
    <TD style='text-align: center;' class="td_list_basic"><span id='nprice'></span></TD>
    <TD style='text-align: center;' class="td_list_basic"><span id='bpoint'></TD>
  </TR>
</TABLE>
<br>

<br>
<DIV class='title_line2'>주문정보</DIV>
  <TABLE class='table_basic' style="vertical-align: middle; width: 80%;">

  <colgroup>
    <col style="width:160px">
    <col style="width:auto">
  </colgroup>
  
  <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label>주문하시는 분</label>
             </div>
            </td>
            <td class="td_basic2">
                 ${memberVO.mname }
            </td>
      </tr>   
     
     <tr class="tr_basic">
            <td class="td_left_basic" >
            <div>
                <label>주소</label>
             </div>
            </td>
            <td class="td_basic2">
                 ${memberVO.zipcode }
                 ${memberVO.address1 }
                 ${memberVO.address2 }
            </td>
      </tr> 
     
     <tr class="tr_basic">
            <td class="td_left_basic" >
            <div>
                <label>휴대전화</label>
             </div>
            </td>
            <td class="td_basic2">
                 ${memberVO.tel }
            </td>
      </tr>
      
      <tr class="tr_basic">
            <td class="td_left_basic" >
            <div>
                <label>아이디</label>
             </div>
            </td>
            <td class="td_basic2">
                 ${memberVO.mid }
            </td>
      </tr>
  </tbody>
</TABLE> 

<br>
<DIV class='title_line2'>배송정보( * : 필수 )</DIV>
  <TABLE class='table_basic' style="vertical-align: middle; width: 80%;">
      
      <colgroup>
       <col style='width: 15%;'/>
       <col style='width: 85%;'/>
      </colgroup>
  
  <tbody>
     <tr class="tr_basic">
            <td class="td_left_basic" >
            <div>
                <label>배송지 선택</label>
             </div>
            </td>
            <td class="td_basic2">
                <input type="radio" name="same_refresh" id="same_refresh1"/>주문자 정보와 동일
                <input type="radio" name="same_refresh" id="same_refresh2"/>새로운 배송지
            </td>
    </tr>
      
    <tr class="tr_basic">
            <td class="td_left_basic" >
            <div>
                <label>* 받으시는분</label>
             </div>
            </td>
            <td class="td_basic2">
                <input type='text' style='width: 20%;' name="mname" id="mname" value=''>
            </td>
    </tr>
    
    <tr class="tr_basic">
            <td class="td_left_basic" >
            <div>
                <label>* 주소</label>
             </div>
            </td>
            <td class="td_basic2">
                <input type='text' style='width: 10%;' name="zipcode" id="zipcode" value=''><span>   </span><button type="button" onclick="javascript: DaumPostcode();" style='text-align: center;' >주소 검색</button><br><br>
                <input type='text' style='width: 45%;' name="address1" id="address1" value=''><br><br>
                <input type='text' style='width: 45%;' name="address2" id="address2" value=''>
             <!-- ----- DAUM 우편번호 API 시작 ----- -->
<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 110px;position:relative">
  <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
</div>
 
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('wrap');
 
    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }
 
    function DaumPostcode() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
 
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수
 
                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }
 
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('address1').value = fullAddr;
 
                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';
 
                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
                
                document.getElementById('address2').focus();
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);
 
        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }
</script>
<!-- ----- DAUM 우편번호 API 종료----- -->
            </td>
    </tr>
     
    <tr class="tr_basic">
            <td class="td_left_basic" >
            <div>
                <label>* 휴대전화</label>
             </div>
            </td>
            <td class="td_basic2">
                <select id="tel1" name="tel1" >
                    <option value="000" <c:if test="${tel1 eq 000}">selected</c:if>>000</option>
                    <option value="010" <c:if test="${tel1 eq 010}">selected</c:if>>010</option>
                    <option value="011" <c:if test="${tel1 eq 011}">selected</c:if>>011</option>
                    <option value="016" <c:if test="${tel1 eq 016}">selected</c:if>>016</option>
                    <option value="017" <c:if test="${tel1 eq 017}">selected</c:if>>017</option>
                     <option value="018" <c:if test="${tel1 eq 018}">selected</c:if>>018</option>
                    <option value="019" <c:if test="${tel1 eq 019}">selected</c:if>>019</option>
                </select>-
                <input type='text' style='width: 15%;' name="tel2" id="tel2" value=''>-
                <input type='text' style='width: 15%;' name="tel3" id="tel3" value=''> 
          </td>
    </tr> 
      
    <tr class="tr_basic">
            <td class="td_left_basic" >
            <div>
                <label>배송메시지</label>
             </div>
            </td>
            <td class="td_basic2">
                <textarea name="content" id="content" cols="100" rows="3" ></textarea>
          </td>
    </tr> 
  </tbody>
</TABLE> 

<br>
<DIV class='title_line2'>결제 예정 금액</DIV>
  <TABLE class='table_basic' style="vertical-align: middle; width: 80%;">
  
  <colgroup>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 25%;'/>
    <col style='width: 25%;'/>
  </colgroup>
  
  <thead>  
  <TR>
    <TH style='text-align: center ;' class="td_left_basic">총주문금액</TH>
    <TH style='text-align: center ;' class="td_left_basic">배송비</TH>
    <TH style='text-align: center ;' class="td_left_basic">할인금액</TH>
    <TH style='text-align: center ;' class="td_left_basic">총결제금액</TH>
  </TR>
  </thead>
  
  <TR>
    <TD style='text-align: center; border-right: none; border-left: 1px solid #cccccc;' class="td_basic2"><span id='total_price' ></span></TD>
    <TD style='text-align: center;' class="td_list_basic"><span id='dir_price'></span></TD>
    <TD style='text-align: center;' class="td_list_basic">-<span id='discount_price'></span></TD>
    <TD style='text-align: center;' class="td_basic2" ><span id='b_price'></span></TD>
  </TR>
</TABLE> 
  <TABLE class='table_basic' style="vertical-align: middle; width: 80%;">

  <colgroup>
    <col style='width: auto'/>
  </colgroup>
  
  <tbody>
     <tr class="tr_basic">
            <td class="td_left_basic" >
            <div>
                <label>총적립금</label>
             </div>
            </td>
            <td class="td_basic2">
               <span id='total_point'></span>
            </td>
    </tr>
     <tr class="tr_basic">
            <td class="td_left_basic" >
            <div>
                <label>예치금</label>
             </div>
            </td>
            <td class="td_basic2">
               <input type="text" style='width: 20%' name='point_sum' id='point_sum' value='' onkeydown="JavaScript:Enter_Check();">원 (총 사용가능 적립금:${sum}-><span id='point_total'></span>)
            </td>
    </tr>
  </tbody>
</TABLE>
<br>
<FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
  <input type='hidden' name='b_num' id='b_num' value='${b_num}'>
  <input type='hidden' name='memberno' id='memberno' value='${memberno}'>
  <input type='hidden' name='contentsno' id='contentsno' value='${contentsno}'>
  <input type='hidden' name='n_price' id='n_price' value=''>
  <input type='hidden' name='n_point' id='n_point' value=''>
  <input type='hidden' name='b_point' id='b_point' value=''>
  <input type='hidden' name='tot_price' id='tot_price' value=''>
  <input type='hidden' name='dir_price' id='dir_price' value=''>
  <input type='hidden' name='totdir_price' id='totdir_price' value=''>
  <input type='hidden' name='dc_price' id='dc_price' value='0'>
  <input type='hidden' name='b_price' id='b_price' value=''>
  <input type='hidden' name='dname'  id='dname'  value=''>
  <input type='hidden' name='dzipcode'  id='dzipcode'  value=''>
  <input type='hidden' name='daddress'  id='daddress'  value=''>
  <input type='hidden' name='dtel'  id='dtel'  value=''>
  <input type='hidden' name='dcontent'  id='dcontent'  value=''>
  <div style='text-align: center;'>
    <button type="button" onclick="order()" style='text-align: center;' >결제</button>
  </div><br>
</FORM>   
     
<DIV id='main_panel'></DIV>      
<DIV id='panel' class='message' style='display:none; width: 60%;'></DIV>


 
    
</DIV> <!-- content END -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- container END -->
 
</body>
</html>