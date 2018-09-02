<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript">

$(function() {
  $.cookie('checkId', 'FALSE'); // Cookie 생성
  $.cookie('checkEmail', 'FALSE'); // Cookie 생성
});

function checkId(){
  $('#modal_panel').show()
  var frm = $('#frm');
  var params = 'mid='+$('#mid', frm).val(); // #: mid
  // alert('params: ' + params);

  $.ajax({
    url: "./checkId.do",
    type: "GET",
    cache: false,
    dataType: "json", // or html
    data: params,
    success: function(data){
  //    var msg = "";

      if (data.cnt > 0) {
        alert("『Danger!』 이미 사용중인 ID 입니다.");
      } else {
        alert( "『Success!』 사용 가능한 ID 입니다.");
        $.cookie('checkId', 'TRUE'); // Cookie 값 변경
      }

    },
    // 통신 에러, 요청 실패, 200 아닌 경우, dataType이 다른경우
    error: function (request, status, error){  
      alert("에러가 발생했습니다. <br><br>");
      alert("다시 시도해주세요.<br><br>");
      alert("request.status: " + request.status + "<br>");
      alert("request.responseText: " + request.responseText + "<br>");
      alert("status: " + status + "<br>");
      alert("error: " + error);

   
     }
  });
}

function send() {
  var checkId = $.cookie('checkId'); // 쿠키값
  
  $('#modal_panel').show()
  
  if (checkId != 'TRUE') {
    alert("ID 중복확인이 되지 않았습니다.<br>",
             "ID [중복확인] 버튼을 클릭하세요.<br>");

    
    return false; // submit 중지
  }
  
  if ($('#passwd').val() != $('#passwd2').val()) {
    alert("입력된 패스워드가 일치하지 않습니다.<br>",
               "패스워드를 다시 입력해주세요.<br>");

    return false; // submit 중지
  }

  return true; // submit 진행
}

</script>
</head>

<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>


  <DIV  style='width: 100%;'>
  <DIV class='title_line'>회원 가입 페이지</DIV>

  <FORM name='frm' id='frm' method='POST' action='./create.do' 
              onsubmit="return send();" enctype="multipart/form-data"  class="form-horizontal">
    <table class='table_basic' style="vertical-align: middle; width: 80%;">
      
      <colgroup>
       <col style='width: 15%;'/>
       <col style='width: 85%;'/>
      </colgroup>
      
        <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label for="mid" >아이디</label>
             </div>
            </td>
            <td class="td_basic2">
               <input type='text' class="form-control" name='mid' id='mid' value='member' required="required" style='width: 25%;' placeholder="아이디" autofocus="autofocus">
               <button type='button' onclick="checkId()" style="vertical-align: middle;">아이디 중복 확인</button>
               <SPAN id='id_span'></SPAN> <!-- ID 중복 관련 메시지 -->            
            </td>
         </tr>   
         
         <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label for="passwd" >패스워드</label>
             </div>
            </td>
            <td class="td_basic2">
               <input type='password' class="form-control" name='passwd' id='passwd' value='1234' required="required" style='width: 25%;' placeholder="패스워드">
            </td>
         </tr>
         
         <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label for="passwd2" >패스워드 확인</label>
             </div>
            </td>
            <td class="td_basic2">
               <input type='password' class="form-control" name='passwd2' id='passwd2' value='1234' required="required" style='width: 25%;' placeholder="패스워드">
            </td>
         </tr>
         
          <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label for="passwd2" >성명</label>
             </div>
            </td>
            <td class="td_basic2">
               <input type='text' class="form-control" name='mname' id='mname' 
                   value='홍길동' required="required" style='width: 25%;' placeholder="성명">
            </td>
         </tr>
         
         <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label for="gender" >성별</label>
             </div>
            </td>
            <td class="td_basic2">
               <select name='gender' id ='gender'>
                    <option value='male'>남성</option>
                    <option value='female'>여성</option>
                </select>
            </td>
         </tr>
         
         <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label for="age" >나이</label>
             </div>
            </td>
            <td class="td_basic2">
               <input type='text' class="form-control" name='age' id='age' 
                   value='10' required="required" style='width: 25%;' placeholder="나이">
            </td>
         </tr>
         
         <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label for="tel" >전화번호</label>
             </div>
            </td>
            <td class="td_basic2">
               <input type='text' class="form-control" name='tel' id='tel' 
                   value='010-0000-0000' required="required" style='width: 25%;' placeholder="전화번호"> 예) 010-0000-0000
            </td>
         </tr>
         
         <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label for="zipcode" >우편번호</label>
             </div>
            </td>
            <td class="td_basic2">
                 <input type='text' class="form-control" name='zipcode' id='zipcode' 
                   value='12345' required="required" style='width: 25%;' placeholder="우편번호">
                 <button type="button" onclick="DaumPostcode()" style="vertical-align: middle;">우편번호찾기</button>
            </td>
         </tr>
         
         <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label for="address1" >주소</label>
             </div>
            </td>
            <td class="td_basic2">
                 <input type='text' class="form-control" name='address1' id='address1' 
                   value='' required="required" style='width: 60%;' placeholder="주소">
            </td>
         </tr>
         
         <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
                <label for="address2" >상세 주소</label>
             </div>
            </td>
            <td class="td_basic2">
                 <input type='text' class="form-control" name='address2' id='address2' 
                   value='' required="required" style='width: 60%;' placeholder="상세 주소">
            </td>
         </tr>
   
   

      <div>

<!-- ----- DAUM 우편번호 API 시작 ----- -->
<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 110px;position:relative">
  <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
</div>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
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

                $('#address2').focus();
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

       </div>
    </table>
    <div style='text-align: center;'>
        <button type="submit" >등록</button>
        <button type="button"  onclick="location.href='../index.jsp'">취소</button>
      </div><br>
  </FORM>
</DIV>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
</html> 
  