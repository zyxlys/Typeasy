package me.imomo.typeasy.commons;


import java.util.Comparator;

import me.imomo.typeasy.vo.ContentsVO;

/**
 * Contents 比较接口
 * @author Administrator
 * @version 1.0	2013/06/06
 */

public class ContentsComparator implements Comparator<ContentsVO>{

	/**
	 * 实现Comparator接口中的方法
	 */
	public int compare(ContentsVO c1, ContentsVO c2) {

		if(c1 == null){
			return 1;
		}
		if(c2 == null || !(c2 instanceof ContentsVO)){
			return -1;
		}
		
		long cid1 = c1.getCid();
		long cid2 = c2.getCid();
		return cid1 > cid2 ? 1 : cid1 < cid2 ? -1 : 0;
		
	}
	
}
