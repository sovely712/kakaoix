package dev.mvc.order;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;




import dev.mvc.basket.BasketProcInter;
import dev.mvc.basket.Contents_BasketVO;
import dev.mvc.contents.ContentsProcInter;
import dev.mvc.contents.ContentsVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.point.Order_PointVO;
import dev.mvc.point.PointProcInter;
import dev.mvc.point.PointVO;
import dev.mvc.tool.Tool;


@Controller
public class OrderCont {
  
  @Autowired
  @Qualifier("dev.mvc.basket.BasketProc")
  private BasketProcInter basketProc;
  
  @Autowired
  @Qualifier("dev.mvc.point.PointProc")
  private PointProcInter pointProc;
  
  @Autowired
  @Qualifier("dev.mvc.contents.ContentsProc")
  private ContentsProcInter contentsProc = null;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")       
  private MemberProcInter memberProc;

  @Autowired
  @Qualifier("dev.mvc.order.OrderProc")
  private OrderProcInter orderProc;
  
  public OrderCont(){
    //System.out.println("-->OrderCont created.");    
  }
  
  /**
   * 바로 구매 등록 */
  @RequestMapping(value="/order/create.do",method = RequestMethod.GET)
  public ModelAndView create(){
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/order/create");
    return mav;
  }
  
  @RequestMapping(value="/order/create.do",method = RequestMethod.POST)
  public ModelAndView used_create(OrderVO orderVO, HttpSession session){
    
    int memberno = (Integer)session.getAttribute("memberno");
    
    ModelAndView mav = new ModelAndView();
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    //바로구매이므로 장바구니 번호는 0으로 바꿈
    orderVO.setBasketno(0);
    //주문번호 등록시키기
    orderVO.setOrdernum(orderProc.ordernum());
    int ordernum = orderVO.getOrdernum();
   
    if(orderProc.create(orderVO)==1){ 
      int discount = orderVO.getDc_price();
      int b_num = orderVO.getB_num();
      int contentsno = orderVO.getContentsno();
      int b_point = orderVO.getB_point();
      
      //주문한 수량 만큼 재고량 감소시키기
      int count = 0;
      for(int i=0;i<b_num;i++){
        count = contentsProc.decreaseNum(contentsno);
      }
      
      //포인트 적립과 사용 등록
       PointVO pointVO = new PointVO();
      
      if(discount!=0){
        pointVO.setPstatus('U');
        pointVO.setU_point(discount);
        pointVO.setS_point(b_point);
        pointVO.setMemberno(memberno);
        pointVO.setOrderno(ordernum);
        pointProc.create(pointVO);
      }else{
        pointVO.setPstatus('S');
        pointVO.setU_point(0);
        pointVO.setS_point(b_point);
        pointVO.setMemberno(memberno);
        pointVO.setOrderno(ordernum);
        pointProc.create(pointVO);
      }
      
      mav.setViewName("redirect:/order/list_order_search_paging.do?memberno="+orderVO.getMemberno());
    
    }else{
      msgs.add("주문 등록에 실패하였습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요 ☎ 고객 센터:00-000-0000");
      mav.setViewName("redirect:/basket/list.do");
      links.add("<button type='button' onclick=\"history.back();\">다시 시도</button>");
      links.add("<button type='button' onclick=\"location.href='./list.do'\">목록</button>");
      mav.addObject("msgs",msgs);         
      mav.addObject("links",links);
      
      mav.setViewName("/order/message"); 
 
    }
    return mav;
    
  }
  
  /**
   * 주문서 작성 */
  @RequestMapping(value = "/order/contents_read.do", method = RequestMethod.GET)
  public ModelAndView read(int contentsno, int b_num, HttpSession session) {
    
    ModelAndView mav = new ModelAndView();
    
    int memberno = (Integer)session.getAttribute("memberno");
    
    //주문 상품정보
    ContentsVO contentsVO = contentsProc.read(contentsno);
    mav.addObject("contentsVO",contentsVO);
    mav.addObject("memberno",memberno);
    mav.addObject("contentsno",contentsno);
    mav.addObject("b_num",b_num);
    
    //주문자 배송정보
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO",memberVO);
    
    //현재 포인트
    List<PointVO> list = pointProc.list(memberno);
    int sum = pointProc.calc(list);
    mav.addObject("sum", sum);
    
    String[] select_tel = null;
    String[] s_t = null;
    String tel1 ="";
    String tel2 ="";
    String tel3 ="";
    
    //회원가입시 등록한 번호를 불러와 000/0000/0000으로 나눠 tel1,tel2,tel3에 넣어 출력한다.
    select_tel = memberVO.getTel().split("-");
    s_t = new String[select_tel.length];
    
    for(int i=0; i<select_tel.length; i++){
      s_t[i] = select_tel[i];
    }
   
    tel1 = s_t[0];
    tel2 = s_t[1];
    tel3 = s_t[2];
   
    mav.addObject("tel1",tel1);
    mav.addObject("tel2",tel2);
    mav.addObject("tel3",tel3);
    
    mav.setViewName("/order/contents_read"); 
    return mav;
  }
  
  /**
   * 장바구니 구매 등록 */
  @RequestMapping(value="/order/many_create.do", method=RequestMethod.POST)
  public ModelAndView basket_create(String checkOrder, HttpSession session, int total_price, int total_point, int discount, String dname, String dzipcode, String daddress, String dtel, String dcontent) {

    int memberno = (Integer)session.getAttribute("memberno");
    System.out.println("many_create memberno:"+memberno);
    
    ModelAndView mav = new ModelAndView();
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    String[] basketsplit = null;
    int[] count = null;
    //int cnt=0;
    int ordernum=0;
    int contentsno=0;
    int b_num=0;
    int n_price=0;
    int n_point=0;
    int dir_price=0;
    int b_price=0;
    int b_point=0;
    int totdir_price=0;
   
    OrderVO orderVO = new OrderVO();
    
    //checkOrder에 basketno를 1/2/3 이런식으로 합쳐서 들어오는 것을 다시 나눠서 각각 저장한다.
    if(checkOrder.length() != 0){                           // 1개이상 체크했을때
      basketsplit= checkOrder.split("/");
        int[] basketno = new int[basketsplit.length];
        count = new int[basketsplit.length];
        
        //주문번호 등록하기
        ordernum = orderProc.ordernum();
       
        for(int i=0;i<basketsplit.length;i++){
          basketno[i] = Integer.parseInt(basketsplit[i]);
          //System.out.println("basketno["+i+"]:" + basketno[i]);
          
          Contents_BasketVO contents_basketVO = basketProc.read(basketno[i]);
          contentsno = contents_basketVO.getContentsno();
          b_num = contents_basketVO.getB_num();
          n_price = contents_basketVO.getPrice()*b_num;
          n_point = (int) (n_price*0.03);
          
          orderVO.setOrdernum(ordernum);
          orderVO.setContentsno(contentsno);
          orderVO.setMemberno(memberno);
          orderVO.setBasketno(basketno[i]);
          orderVO.setB_num(b_num);
          orderVO.setN_price(n_price);
          orderVO.setN_point(n_point);
          orderVO.setB_point(total_point);
          orderVO.setTot_price(total_price);
          orderVO.setDir_price(dir_price);
          orderVO.setDc_price(discount);
          orderVO.setB_price(b_price);
          orderVO.setDname(dname);
          orderVO.setDzipcode(dzipcode);
          orderVO.setDaddress(daddress);
          orderVO.setDtel(dtel);
          orderVO.setDcontent(dcontent);
          
          count[i] = orderProc.create(orderVO);
          
          //장바구니에서 선택한 상품이 주문으로 넘어가면 장바구니에서는 보여지지 않게 하기 
          contents_basketVO.setVisible('N');
          basketProc.visible(contents_basketVO);
          
         //주문한 수량 만큼 재고량 감소시키기
          int cnt = 0;
          for(int j=0;j<b_num;j++){
            cnt = contentsProc.decreaseNum(contentsno);
          }
       }
          
          if(total_price<50000){
            dir_price =2500;
          }else{
            dir_price =0;
          }
         
          totdir_price = total_price+dir_price;
          b_price = total_price+dir_price-discount;
          
          orderVO.setDir_price(dir_price);
          orderVO.setTotdir_price(totdir_price);
          orderVO.setB_price(b_price);
        
          //장바구니에 담겨서 온 하나의 상품의 가격별로(dir_price,totdir_price,b_price)가 달라지지만 같은 주문번호인 상품들의 배송비,총합계,총결제금액을 통일시켜 저장한다.
          int cnt = orderProc.updatePrice(orderVO); 
         
          //포인트 적립과 사용 등록
          PointVO pointVO = new PointVO();
         
         if(discount!=0){
           pointVO.setPstatus('U');
           pointVO.setU_point(discount);
           pointVO.setS_point(total_point);
           pointVO.setMemberno(memberno);
           pointVO.setOrderno(ordernum);
           pointProc.create(pointVO);
         }else{
           pointVO.setPstatus('S');
           pointVO.setU_point(0);
           pointVO.setS_point(total_point);
           pointVO.setMemberno(memberno);
           pointVO.setOrderno(ordernum);
           pointProc.create(pointVO);
         }

    }else{ 
        System.out.println("아무것도 체크하지않았을때");
    }
    mav.setViewName("redirect:/order/list_order_search_paging.do?memberno="+orderVO.getMemberno());
    return mav;
  }
  
  /**
   * 장바구니에서 주문한 주문서 작성 */
  @RequestMapping(value = "/order/basket_read.do", method = RequestMethod.POST)
  public ModelAndView read(String checkOrder, HttpSession session) {
   
    int memberno = (Integer)session.getAttribute("memberno");
    //System.out.println("basket_read memberno:"+memberno);
    
    ModelAndView mav = new ModelAndView();
    mav.addObject("memberno",memberno);
    
    //주문 상품정보
    List<Contents_BasketVO> list = new ArrayList<Contents_BasketVO>();
    
    String[] basketsplit = null;
    int[] count = null;
    int cnt = 0;
    if(checkOrder.length() != 0){             // 1개이상 체크했을때
        basketsplit= checkOrder.split("/");
        
        cnt = basketsplit.length;             // basket->order 주문 클릭했을때 선택한 갯수
        int[] basketno = new int[basketsplit.length];
        count = new int[basketsplit.length];
        
        for(int i=0;i<basketsplit.length;i++){
          basketno[i] = Integer.parseInt(basketsplit[i]);
          //System.out.println("basketno["+i+"]:" + basketno[i]);
          
          Contents_BasketVO contents_basketVO = basketProc.read(basketno[i]);
          list.add(contents_basketVO);
       }
    }else{ 
        System.out.println("아무것도 체크하지않았을때");
    }
    
    mav.addObject("list",list);
    mav.addObject("cnt",cnt);
    
    //주문자 배송정보
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO",memberVO);
    
    String[] select_tel = null;
    String[] s_t = null;
    String tel1 ="";
    String tel2 ="";
    String tel3 ="";
    
    select_tel = memberVO.getTel().split("-");
    s_t = new String[select_tel.length];
    
    for(int i=0; i<select_tel.length; i++){
      s_t[i] = select_tel[i];
    }
   
    tel1 = s_t[0];
    tel2 = s_t[1];
    tel3 = s_t[2];
   
    mav.addObject("tel1",tel1);
    mav.addObject("tel2",tel2);
    mav.addObject("tel3",tel3);
    
    //현재 포인트
    List<PointVO> list_basket = pointProc.list(memberno);
    int sum = pointProc.calc(list_basket);
    mav.addObject("sum", sum);
    
    mav.setViewName("/order/basket_read"); 
    return mav;
  }
  
  /**장바구니 주문서
   * 총주문금액,배송비 ajax */
  @ResponseBody
  @RequestMapping(value="/order/check_view.do", 
                            method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String check_view(String checkView) {
    
    String[] basketsplit = null;
    int total = 0;
    int dir_price =0;
    int b_point =0;
    int b_price =0;
    
    if(checkView.length() != 0){ // 1개이상 체크했을때
        basketsplit= checkView.split("/");
        int[] basketno = new int[basketsplit.length];
        
        for(int i=0;i<basketsplit.length;i++){
          basketno[i] = Integer.parseInt(basketsplit[i]);
          //System.out.println("basketno["+i+"]:" + basketno[i]);
          
          Contents_BasketVO contents_basketVO = basketProc.read(basketno[i]);
          total += (contents_basketVO.getPrice() * contents_basketVO.getB_num());
          
          if(total<50000){  
            dir_price =2500;
          }else{
            dir_price =0;
          }
          
          b_point = (int) (total*0.03);
          b_price = total + dir_price;
          
        }
    }else{ 
        System.out.println("아무것도 체크하지않았을때");
        total = 0;
    }
    
    JSONObject obj = new JSONObject();
    obj.put("total", total);
    obj.put("dir_price", dir_price);
    obj.put("b_point", b_point);
    obj.put("b_price", b_price);
    //System.out.println("total : " +total);
    //System.out.println("dir_price : " +dir_price);
    //System.out.println("b_point : " +b_point);
    //System.out.println("b_price : " +b_price);
    return obj.toJSONString();
  }
  
  /**장바구니 주문서
   * 총주문금액+배송비-할인금액=총결재금액 ajax */
  @ResponseBody
  @RequestMapping(value="/order/point_event.do", 
                            method=RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String point_event(int total, int dir_price, int discount_price, int b_price) {
    
    b_price = total+dir_price-discount_price;
    
    JSONObject obj = new JSONObject();
    obj.put("total", total);
    obj.put("dir_price", dir_price);
    obj.put("discount_price", discount_price);
    obj.put("b_price", b_price);
    
    return obj.toJSONString();
  }
  
  /**
   * 회원별 주문내역 조회+검색+페이징 */
  @RequestMapping(value = "/order/list_order_search_paging.do", method = RequestMethod.GET)
  public ModelAndView used_list_order_search_paging(
      HttpSession session,
      @RequestParam(value="status", defaultValue="") String status,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) { 
    int memberno = (Integer)session.getAttribute("memberno");
    
    ModelAndView mav = new ModelAndView();
    
    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("memberno", memberno); // #{memberno}
    hashMap.put("status", status);     // #{status}
    hashMap.put("nowPage", nowPage);       
    
    // 검색 목록
    List<Order_JoinVO> list = orderProc.list_order_search_paging(hashMap);  
    mav.addObject("list", list);
    
    // 검색된 레코드 갯수
    int search_count = orderProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    // 회원정보
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);

    String paging = orderProc.paging(memberno, search_count, nowPage, status);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);
    mav.setViewName("/order/list_order_search_paging");   
    
    return mav;
  }
  
  /**회원
   * 주문번호 클릭했을때 상세 조회 */
  @RequestMapping(value="/order/lookup.do", method=RequestMethod.GET)
  public ModelAndView lookup(int ordernum, String odate, String status, HttpSession session) {
    
    int memberno = (Integer)session.getAttribute("memberno");

    ModelAndView mav = new ModelAndView();
    
    //상품 정보
    List<Order_JoinVO> list = orderProc.List_read(ordernum);
    mav.addObject("list", list);
    //주문 정보
    mav.addObject("ordernum", ordernum);
    mav.addObject("odate", odate);
    
    //status 값 가져오기
    HashMap<String,String> order_status = Order_status.getStatus();
    mav.addObject("status", status);
    mav.addObject("status_label", order_status.get(status));
    
    //주문자 정보
    MemberVO memberVO = memberProc.read(memberno);
    mav.addObject("memberVO", memberVO);
    
    //두개 이상 주문한 경우는 상세조회에서 하나만 읽을 수 없기 때문에 어차피 보여줄 내용은 둘다 똑같으므로  List_read에서 첫번째 orderno을 가져와서 출력함.
    int orderno = list.get(0).getOrderno();
    
    OrderVO orderVO = orderProc.read(orderno);
    
    String dname = orderVO.getDname();
    String dzipcode = orderVO.getDzipcode();
    String daddress = orderVO.getDaddress();
    String dtel = orderVO.getDtel();
    String dcontent = orderVO.getDcontent();
    
    int tot_price = orderVO.getTot_price();
    int dir_price = orderVO.getDir_price();
    int totdir_price = orderVO.getTotdir_price();
    int dc_price = orderVO.getDc_price();
    int b_price = orderVO.getB_price();
    
    //배송지 정보
    mav.addObject("dname", dname);
    mav.addObject("dzipcode", dzipcode);
    mav.addObject("daddress", daddress);
    mav.addObject("dtel", dtel);
    mav.addObject("dcontent", dcontent);
    //결제 정보
    mav.addObject("tot_price",tot_price);
    mav.addObject("dir_price",dir_price);
    mav.addObject("totdir_price",totdir_price);
    mav.addObject("dc_price",dc_price);
    mav.addObject("b_price",b_price);
   
    mav.setViewName("/order/lookup");
    return mav;
  }
  
  /**회원
   * 주문처리 상태 변경 */
  @RequestMapping(value = "/order/updateStatus.do", method = RequestMethod.POST)
  public ModelAndView updateStatus(OrderVO orderVO) {
   
    ModelAndView mav = new ModelAndView();
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
        
    int count = orderProc.updateStatus(orderVO);
    
    if (count >= 1) {
      
      mav.setViewName("redirect:/order/lookup.do?ordernum="+orderVO.getOrdernum()+"&odate="+orderVO.getOdate()+"&status="+orderVO.getStatus()+"&memberno="+orderVO.getMemberno());
   
    } else {
      msgs.add("주문처리상태 수정에 실패했습니다.");
      msgs.add("죄송하지만 다시한번 시도해주세요. ☏ 고객 센터: 000-0000-0000");
      links.add("<button type='button' onclick=\"history.back();\">다시 시도</button>");
      links.add("<button type='button' onclick=\"location.href='./lookup.do?ordernum="+orderVO.getOrdernum()+"&odate="+orderVO.getOdate()+"&status="+orderVO.getStatus()+"&memberno="+orderVO.getMemberno()+"'\">취소</button>");
 
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
      
      mav.setViewName("/order/message"); 
    }
   
    return mav;
  }
  
  
}
