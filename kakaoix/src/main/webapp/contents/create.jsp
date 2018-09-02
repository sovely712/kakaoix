<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
<title>상품 등록</title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
  
  
</script>

</head> 

<body>
<DIV class='container'>
<jsp:include page="/menu/top.jsp" flush='false' />
<DIV class='content'>     
  <br>

  <ASIDE style='float: left;'>
      <A href='../category/list.do'>카테고리 목록</A><span class='menu_divide' >│</span>  
      <A href='../contents/list.do?categoryno=${param.categoryno }'>컨텐츠 목록</A>
  </ASIDE>
  
  
    <FORM name='frm' method='POST' action='./create.do' enctype='multipart/form-data'>     
      
      <input type='hidden' name='categoryno' id='categoryno' value='${param.categoryno }'> 
      
      <table class="table_basic">
      <colgroup>
        <col style='width: 35%;'/>
        <col style='width: 65%;'/>
      </colgroup>
      <tr>
        <td>
            <div style="text-align: center; font-size: 18px; font-weight: bold; margin-bottom: 10px;">상품 등록</div>
            <div>
              <label class="label_basic" for="title" >제목</label>
              <input class="input_basic" type='text' name='title' id='title' value='공부노트_후디라이언' required="required" style='width: 45%;'>
            </div>
            
            <div>
              <label class="label_basic" for="price">가격</label>
              <input class="input_basic" type='text' name='price' id='price' value='3000' required="required" style='width: 30%;'>원
            </div>
            
            <div>
              <label class="label_basic" for="num">수량</label>
              <input class="input_basic" type='text' name='num' id='num' value='3' required="required" style='width: 30%;'>개
            </div>
            
        <div>
            <label class="label_basic" for="filesMF">파일<br></label>
            <input class="input_basic" type="file"  name='filesMF' id='filesMF' size='40'>
            <br><br>
            Preview(미리보기) 이미지는 자동 생성됩니다.
        </div>
        </td>
       <td>
          <textarea style="width:80%;" name='content' id='content'  rows='10'>라이언 공책입니다.</textarea>
       </td>
      </tr>  
      </table>

      <DIV style='text-align: center;'>
        <button type="submit">등록</button>
        <button type="button" onclick="location.href='./list_by_search.do'">취소</button>
      </DIV>
    </FORM>
  


<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV> <!-- content END -->
</DIV> <!-- container END -->
</body>

</html> 
