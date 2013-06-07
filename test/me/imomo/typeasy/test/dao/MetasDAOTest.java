package me.imomo.typeasy.test.dao;

import java.util.List;

import org.junit.Test;

import me.imomo.typeasy.dao.MetasDAO;
import me.imomo.typeasy.vo.MetasVO;

/**
 *	项目测试
 * @author 夏、末
 *
 */
public class MetasDAOTest {

		private  MetasDAO met=null;
		
		@Test
		/**
		 * 测试通过名字查找
		 */
		public void findName(){
			met=new MetasDAO();
			System.out.println(met.findByName("娱乐").getMid());
		}
		
		@Test
		/**
		 * 测试添加目录
		 */
		public void add(){
			met=new MetasDAO();
			MetasVO meta=new MetasVO();
			meta.setName("娱乐");
			meta.setSlug("YL");
			meta.setType("category");
			meta.setDescription("adhgoiahg");
			met.add(meta);
		}
		
		@Test
		/**
		 * 测试删除
		 */
		public void del(){
			met=new MetasDAO();
			met.del(5);
		}
		
		@Test
		/**
		 * 测试编辑目录和标签
		 */
		public void edit(){	
			met=new MetasDAO();
			MetasVO meta=new MetasVO();
			meta.setMid(9);
			meta.setName("sdf");
			meta.setSlug("sa");
			meta.setType("category");
			met.edit(meta);
		}
		
		@Test
		/**
		 * 测试通过mid查找目录
		 */
		public void findMId(){
			met=new MetasDAO();
			System.out.println(met.findByMid(8).getName());
		}
		
		@Test
		/**
		 * 测试查找所有
		 */
		public void find(){
			met=new MetasDAO();
			List<MetasVO> metas=met.find();
			for(MetasVO met:metas){
				System.out.println(met.getMid());
			}
		}
	}


