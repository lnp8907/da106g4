package com.shop.meth;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class CompositeQuery {
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("product_type".equals(columnName)) {// 用於其他
		
				aCondition = columnName + "=" +"'"+ value+"' ";				
		}
		else if ("product_name".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
	public static String get_WhereCondition(Map<String, String[]> map,Integer product_status) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		System.out.println(product_status);
		if(product_status>1) {
			product_status=0;
		}
		String c=product_status+"";
		int count = 0;
		whereCondition.append("where product_status="+c+" ");
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
			
			
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				
				if (count >= 1)
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
	
	
	
	
	
	
	
//測試
	public static void main(String[] args) {
		Map<String, String[]> map = new TreeMap<String, String[]>();
		Integer product_status=1;
		map.put("product_type", new String[] { "蔬菜" });
		String finalSQL = "select * from product where "
		          + CompositeQuery.get_WhereCondition(map)
		          + "order by product_id";
		System.out.println(finalSQL);
	}
}

