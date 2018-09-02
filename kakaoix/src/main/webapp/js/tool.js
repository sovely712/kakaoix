// version 0.2
 
  // input 태그의 값 추출
  function value(id) {
    return document.getElementById(id).value;
  }
  
  // <span><div><p> 태그의 값 추출
  function text(id) {
    return document.getElementById(id).innerHTML;
  }
  
  // 문자열에서 숫자만 추출
  function getNumber(val){
    var regexp = /[^0-9]/g;  
    var returnval = new String(val); // 타입 명시
    returnval = returnval.replace(regexp, ''); // 문자만 찾아서 삭제
    
    return returnval;
  }
 
  // 천단위 구분기호 출력
  function comma(price){
    var _price = Number(price).toLocaleString('en');
    return _price;
  }
    
  /*
   * 태그를 검색합니다.
   */
  function find(id) {
	//alert(document.getElementById(id));  
    return document.getElementById(id);
  }
  
  /*
   * 태그를 출력합니다.
   */
  function show(id) {
    document.getElementById(id).style.display = '';
  }
 
  /*
   * 태그를 숨깁니다.
   */
  function hide(id) {
    document.getElementById(id).style.display = 'none';
  }
 
 
  function length(id) {
    return document.getElementById(id).value.trim().length;
  }
  
 
  /*
   * 파일 크기의 단위를 계산합니다.
   */
  function unit(length){
    var str = "";
  
    if (length < 1024){  // Byte: 0 ~ 1023
      str = parseInt(length) + " Byte";   
    }else if (length < (1024 * 1024)){ // Byte: 1024 ~ 1048575
      str = parseInt(length / 1024) + " KB";
    }else if(length < (1024 * 1024 * 1024)){ // Byte: 1048576 ~ 1073741823
      str = parseInt(length / 1024 / 1024) + " MB";
    }else if(length < (1024 * 1024 * 1024 * 1024)){ // Byte: 1073741824 ~
      str = parseInt(length / 1024 / 1024 / 1024) + " GB";
    }
  
    return str;
  }
 
