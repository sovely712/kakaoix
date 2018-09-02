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
  $(function() {
    $('#panel_create').show();
    $('#panel_update').hide();
    
  });
  
  //수정 값 저장하기 
  function update(categoryno) {
    $('#panel_create').hide();
    $('#panel_update').show();
    
    $.ajax({
      url: "./update.do", // 요청을 보낼주소
      type: "get",  // or get
      cache: false,
      dataType: "json", // 응답 데이터 형식, or json
      data: 'categoryno=' +categoryno,  // $('#frm').serialize(), 
      // Ajax 통신 성공, JSP 정상 처리
      success: function(rdata) { // callback 함수
        var frm_update = $('#frm_update');
        $('#categoryno', frm_update).val(rdata.categoryno);        
        $('#title', frm_update).val(rdata.title);
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var panel = '';
        panel += "<DIV id='panel' class='popup1' style='heigth: 350px;'>";
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

  function create_update_cancel() {
    $('#panel_update').hide();
    $('#panel_create').show();

  }
  
  
</script>

</head> 

<body>
<DIV class='container' >
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>
  
  <DIV id='main_panel'></DIV>
  
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px;  width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
       
      <table class='table_basic' style="vertical-align: middle; width: 25%; margin-top: 20px;">
      
      <colgroup>
       <col style='width: 40%;'/>
       <col style='width: 60%;'/>
      </colgroup>
      
       
        <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
             <label for='title'>카테고리 이름</label>
             </div>
           </td>
           <td class="td_basic2" >
           <input type='text' class="form-control"  name='title' id='title' size='10' value='' required="required" style='width: 90%;'>
           </td>
         </tr>
       
        <tr>
          <td colspan='2' style="text-align: center;">
          <br>
            <button type="submit" id='submit'>등록</button>
            <button type="button" onclick="create_update_cancel()">취소</button>
          </td>
        </tr>
      </table> 
    </FORM>
    </DIV>
  
  <!--  수정폼은 항상 PK 전달한다. -->
    <DIV id='panel_update' style='padding: 10px 0px 10px 0px;  width: 100%; text-align: center;'>
    <FORM name='frm_update' id='frm_update' method='POST' action='./update.do'>
      <input type='hidden' name='categoryno' id='categoryno' value=''> 
       <table class='table_basic' style="vertical-align: middle; width: 25%; margin-top: 20px;">
      
      <colgroup>
       <col style='width: 40%;'/>
       <col style='width: 60%;'/>
      </colgroup>
      
       
        <tr class="tr_basic">
            <td class="td_left_basic">
            <div>
             <label for='title'>카테고리 이름</label>
             </div>
           </td>
           <td class="td_basic2" >
           <input type='text' class="form-control"  name='title' id='title' size='10' value='' required="required" style='width: 90%;'>
           </td>
         </tr>
       
        <tr>
          <td colspan='2' style="text-align: center;">
          <br>
            <button type="submit" id='submit'>수정</button>
            <button type="button" onclick="create_update_cancel()">취소</button>
          </td>
        </tr>
      </table> 

    </FORM>
  </DIV>
  
<TABLE class='table_basic' style='width: 100%;'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 30%;'/>
    <col style='width: 30%;'/>
    <col style='width: 30%;'/>

  </colgroup>
  <thead>  
  <TR>
    <TH class='th_basic2'>카테고리번호</TH>
    <TH class='th_basic2'>이름</TH>
    <TH class='th_basic2'>등록일</TH>
    <TH class='th_basic2'>기타</TH>
    
  </TR>
  </thead>
  <tbody>
    <c:forEach var="CategoryVO" items="${list }">
      <TR style='margin: 10px;'>
        <TD class='td_basic' >${CategoryVO.categoryno}</TD>
        <TD class='td_basic' ><A href='../contents/list.do?categoryno=${CategoryVO.categoryno }'>${CategoryVO.title}</A></TD>
        <TD class='td_basic' >${CategoryVO.cdate}</TD>
        <TD class='td_basic' style='text-align: center;'>
          <A href="javascript:update(${CategoryVO.categoryno })"><IMG style='width: 17px; height: 17px;' src='./images/update.png' title='수정'></A>
        </TD>
      </TR>
    </c:forEach>
  </tbody>
</TABLE>


<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>

</html> 
 