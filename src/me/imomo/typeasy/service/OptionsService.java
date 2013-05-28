package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.OptionsDAO;
import me.imomo.typeasy.vo.OptionsVO;

/**
 * Options Service
 * 
 * @version 1.0 2013/05/18
 * @author YL
 *
 */
public class OptionsService {

	private OptionsDAO od = new OptionsDAO();
	
	
	public boolean edit(OptionsVO option)
	{
		if(option!=null)
		{
			od.edit(option);
			return true;	
		}else{
			return false;
		}
	}
	
	
	public OptionsVO findByName(String name){
		return od.findByName(name);
	}
	
	public List<OptionsVO> list() {
		return od.list();
	}
}
