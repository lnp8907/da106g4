package android.com.instant_delivery_order.model;

import android.com.product.model.ProductVO;

public class OrderProductVO extends ProductVO {
    private Integer quantity;
    
    public OrderProductVO() {
    	
    }

    public OrderProductVO(String product_id, String recipe_id, String product_type, String product_name, Integer product_price, Integer product_status, Double carbohydrate, Double protein, Double fat, Double calorie, Double vitamin_B, Double vitamin_C, Double salt, Double vagetbale, String content, Integer quantity) {
        super(product_id, recipe_id, product_type, product_name, product_price, product_status, carbohydrate, protein, fat, calorie, vitamin_B, vitamin_C, salt, vagetbale, content);
        this.quantity = quantity;
    }

    public OrderProductVO(ProductVO productVO, Integer quantity){
        this(productVO.getProduct_id(),productVO.getRecipe_id(), productVO.getProduct_type(), productVO.getProduct_name(), productVO.getProduct_price(), productVO.getProduct_status(), productVO.getCarbohydrate(), productVO.getProtein(), productVO.getFat(), productVO.getCalorie(), productVO.getVitamin_B(), productVO.getVitamin_C(), productVO.getSalt(), productVO.getVagetbale(), productVO.getContent(), quantity);
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
