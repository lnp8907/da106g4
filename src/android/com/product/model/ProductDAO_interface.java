package android.com.product.model;

import java.util.List;

public interface ProductDAO_interface {
	ProductVO findByPrimaryKey(String product_id);
	List<String> getProduct_idByC_no(String c_no);
	List<ProductVO> getAll();
	byte[] getImage(String product_id);
	
}
