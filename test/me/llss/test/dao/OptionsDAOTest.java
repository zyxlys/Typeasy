package me.llss.test.dao;

import java.util.List;

import me.llss.dao.impl.OptionsDAOImpl;
import me.llss.vo.OptionsVO;

import org.junit.Test;

/**
 * 配置测试
 * 
 * @author 夏、末
 * 
 */
public class OptionsDAOTest {
	private OptionsVO options = new OptionsVO();
	private OptionsDAOImpl od = new OptionsDAOImpl();

	@Test
	/**
	 * 测试通过配置名称查找
	 */
	public void findByName() {
		options.setName("音乐");
		options.setValue("music");
	}

	@Test
	/**
	 * 测试修改操作
	 */
	public void edit() {
		options.setName("name");
		options.setValue("value");
		od.edit(options);
	}

	@Test
	/**
	 * 测试查询
	 */
	public void list() {
		List<OptionsVO> options = od.list();
		for (OptionsVO o : options) {
			System.out.println(o.getName());
		}
	}
}
