package com.product_browsing_history.model;

import java.util.List;
import java.util.Set;


public interface Product_browing_historyDAO_interface {
	//新增
    public void insert(Product_browing_historyVO VO) ;

	//刪除
    public void delete(Product_browing_historyVO VO);
    public void deleteAll(String member_id );

    //比對全部
    public List<Product_browing_historyVO> getmemberList(String member_id);

}
