package me.imomo.typeasy.test.dao;

import java.util.List;

import org.junit.Test;

import me.imomo.typeasy.dao.MetasDAO;
import me.imomo.typeasy.vo.MetasVO;


public class MetasDAOTest {

		private  MetasDAO met=null;
		
		@Test
		public void findName(){
			met=new MetasDAO();
			System.out.println(met.findName("娱乐").getMid());
		}
		
		@Test
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
		public void del(){
			met=new MetasDAO();
			met.del(5);
		}
		
		@Test
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
		public void findMId(){
			met=new MetasDAO();
			System.out.println(met.findMid(8).getName());
		}
		
		@Test
		public void find(){
			met=new MetasDAO();
			List<MetasVO> metas=met.find();
			for(MetasVO met:metas){
				System.out.println(met.getMid());
			}
		}
	}


