package com.recipe_order_details.model;

import java.util.List;

import com.order_detail.model.Order_detailDAO_interface;
import com.order_detail.model.Order_detailJDBCDAO;
import com.order_detail.model.Order_detailVO;

public class RecipeOrderDetailsService {
	
	private RecipeOrderDetailsDAO_interface dao;

	
	public List<RecipeOrderDetailsVO> getdetail(String ido_no){
		dao=new RecipeOrderDetailsDAO();
		return dao.findByPrimaryKey_IDO_no(ido_no);
	}
}
