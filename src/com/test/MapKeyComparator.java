/**
 * Copyright(c) Guangzhou JiaxinCloud Science & Technology Ltd. 
 */
package com.test;

import java.util.Comparator;

/**
 * <pre>
 * map比较器。
 * </pre>
 * @author 王文辉  wangwenhui@jiaxincloud.com
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class MapKeyComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
//		 String tem1=o1.replace("-", "");
//		 Long t1=Long.valueOf(tem1);
//		 String tem2=o2.replace("-", "");
//		 Long t2=Long.valueOf(tem2);
//		 int x=(int) (t2-t1);
		return  o2.compareTo(o1);
	}
}
