package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.MetasDao;
import me.imomo.typeasy.vo.Metas;

public class MetasService {

	MetasDao metasDao=new MetasDao();
	public boolean insert(Metas me){
		if(me==null){
			metasDao.insert(me);
			return true;
		}else{
			return false;
		}
	}
	
	
	public void  delete(int mid){
	      metasDao.delete(mid);
	}
	
	
	
	public boolean modify(Metas me){
		if(me!=null){
			metasDao.modify(me);
			return true;
		}else{
			return false;
		}
	}
	
	
	public List<Metas> listAll(){
		return metasDao.findAll();
	}
	
}
