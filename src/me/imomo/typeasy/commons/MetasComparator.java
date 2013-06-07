package me.imomo.typeasy.commons;

import java.util.Comparator;

import me.imomo.typeasy.vo.MetasVO;

/**
 * Metas Comparator接口
 * @author Administrator
 *
 */

public class MetasComparator implements Comparator<MetasVO> {

	/**
	 * 实现Comparator接口中的方法
	 */
	public int compare(MetasVO m1, MetasVO m2) {

		if (m1 == null) {
			return 1;
		}
		if (m2 == null || !(m2 instanceof MetasVO)) {
			return -1;
		}

		long mid1 = m1.getMid();
		long mid2 = m2.getMid();
		return mid1 > mid2 ? 1 : mid1 < mid2 ? -1 : 0;

	}

}
