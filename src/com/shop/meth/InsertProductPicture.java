package com.shop.meth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.product.model.ProductJDBCDAO;
import com.product.model.ProductVO;

public class InsertProductPicture {
public static void main(String[] args) throws IOException, InterruptedException {

	// -------------------------------
	ProductJDBCDAO dao = new ProductJDBCDAO();
	for (int i = 1; i <= 73; i++) {
		String k = "";
		if (i == 50) {
			Thread.sleep(2000);
		}
		if (i < 10) {
			k += "0" + i;
		} else {
			k += i + "";
		}
		String a=System.getProperty("user.dir");	

		a+="\\WebContent\\product\\picture";
		a+="\\"+i+".jpg";
		byte[] pic = ProductJDBCDAO.getPictureByteArray(a);
		ProductVO daoT02 = new ProductVO();
		daoT02.setProduct_id("3200" + k);
		System.out.println(i);
		k = "";
		daoT02.setProduct_photo(pic);
		System.out.println();
		dao.updatepicture(daoT02);
	}
	System.out.println("看到這個應該就等於改寫完了");
	
	
	
}
}
