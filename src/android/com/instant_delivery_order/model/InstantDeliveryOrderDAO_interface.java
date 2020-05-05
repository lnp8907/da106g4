package android.com.instant_delivery_order.model;

import java.sql.Connection;
import java.util.*;



import android.com.recipe_order_details.model.RecipeOrderDetailsVO;

public interface InstantDeliveryOrderDAO_interface {
          public String insertWithDetails(InstantDeliveryOrderVO instantDeliveryOrderVO, List<RecipeOrderDetailsVO> orderProductList);
//          public void update(InstantDeliveryOrderVO instantDeliveryOrderVO); 商業利益不能亂修改
//          public void delete(String ido_no); 有取消訂單的狀態
          public InstantDeliveryOrderVO findByPrimaryKey(String ido_no);
          public List<InstantDeliveryOrderVO> getAll();
          public List<InstantDeliveryOrderVO> getAll(String member_id);
          public List<InstantDeliveryOrderVO> getDeliveryOrder(Integer o_status);
          public boolean updateStaffId(String staff_id, String ido_no, String o_status);
          public List<OrderProductVO> getOneOrderProductVO(String ido_no, Connection con);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
      //  public List<RecipeVO> getAll(Map<String, String[]> map); 
}
