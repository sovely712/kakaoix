package dev.mvc.order;

import java.util.HashMap;
 
public class Order_status {
  
  public static synchronized HashMap<String, String> getStatus(){
    HashMap<String,String> order_status = new HashMap<String,String>();
    order_status.put("1", "���� �Ϸ�");
    order_status.put("2", "���� ���");
    
    return order_status;
  }
}