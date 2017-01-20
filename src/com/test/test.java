/**
 * Copyright(c) Guangzhou JiaxinCloud Science & Technology Ltd. 
 */
package com.test;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author 王文辉  wangwenhui@jiaxincloud.com
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String,String>dataMap=new TreeMap<String,String>();
		dataMap.put("2016-10-13", "1");
		dataMap.put("2016-10-12", "2");
		dataMap.put("2016-10-10", "3");
		dataMap.put("2016-10-09", "4");
		dataMap.put("2016-10-03", "5");
		dataMap.put("2016-10-01", "6");
		dataMap=sortMapByKey(dataMap);
		for (Entry<String, String> entry : dataMap.entrySet()) {
			System.out.println(entry.getKey());
		}
		
	}
	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static Map<String,String> sortMapByKey(Map<String,String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String,String> sortMap = new TreeMap<String,String>(
				new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}
}
