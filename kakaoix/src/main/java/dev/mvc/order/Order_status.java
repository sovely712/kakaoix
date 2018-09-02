package dev.mvc.order;

import java.util.HashMap;
 
public class Order_status {
  
  public static synchronized HashMap<String, String> getStatus(){
    HashMap<String,String> order_status = new HashMap<String,String>();
    order_status.put("1", "결제 완료");
    order_status.put("2", "결제 취소");
    
    return order_status;
  }
}