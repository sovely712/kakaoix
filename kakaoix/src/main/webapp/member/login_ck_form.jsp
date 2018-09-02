<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title>로그인</title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/JavaScript"src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript"></script>
 
</head> 
 
<body>
<DIV class='container' >
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>

<DIV class='title_line'>로그인 </DIV>

 
  <FORM name='frm' method='POST' action='./login.do' >
  
  
    <table class='table_basic' style="width: 50%; ">
      
      <colgroup>
       <col style='width: 10%;'/>
       <col style='width: 50%;'/>
      </colgroup>
      
        <tr class="tr_basic">
            <td class="td_left_basic" style="text-align: center;">
            <div>
                <label for="mid" >ID</label>
             </div>
            </td>
            <td class="td_basic2" style="text-align: center;">
                <input type='text' name='mid' id='mid' value='${ck_mid }' required="required" style='width: 50%;' placeholder="아이디" autofocus="autofocus">
                <Label>   
          <input type='checkbox' name='mid_save' value='Y' 
                    ${ck_mid_save == 'Y' ? "checked='checked'" : "" }> 저장
        </Label>
            </td>
         </tr> 
    
      <tr class="tr_basic">
            <td class="td_left_basic" style="text-align: center;">
            <div>
                <label for="passwd" >Password</label>
             </div>
            </td>
            <td class="td_basic2" style="text-align: center;">
                <input type='password' name='passwd' id='passwd' value='${ck_passwd }' required="required" style='width: 50%;' placeholder="패스워드">
                <Label>
          <input type='checkbox' name='passwd_save' value='Y' 
                    ${ck_passwd_save == 'Y' ? "checked='checked'" : "" }> 저장
        </Label>
            </td>
         </tr>
         
      
        </table>
        
        
           <div style='text-align: center;'>
          <button type="button" onclick="location.href='./create.do'">회원가입</button>
          <button type="submit" >로그인</button>
          <button type="button"  onclick="history.back()">취소</button>
       
      </div><br>
    </FORM>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>
 
</html> 