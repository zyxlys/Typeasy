package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.OptionsDao;
import me.imomo.typeasy.vo.Options;

/**
 * Options Service
 * 
 * @version 1.0 2013/05/18
 * @author YL
 *
 */
public class OptionsService {

	private OptionsDao od = new OptionsDao();
	
	/*public boolean Add(Options op){
		Options name = od.FindByName(op.getName());
		boolean flag = false;
		if(name.getValue()==null)
		{
			od.Insert(op);
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	
	public void delete(String name)
	{
		od.Delete(name);
	}*/
	
	public boolean Modify(Options op)
	{
		if(op!=null)
		{
			od.Modify(op);
			return true;
		}else{
			return false;
		}
	}
	
	public List<Options> findAll(){
		List<Options> list = od.findAll();
		return list;
	}
	
	public Options findOne(String name){
		return od.FindByName(name);
	}
}
