package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.MetasDAO;
import me.imomo.typeasy.vo.MetasVO;

public class MetasService {

	MetasDAO metasDao=new MetasDAO();
	public void add(MetasVO meta){
		  metasDao.add(meta);
	}
	

	public void  del(int mid){
	      metasDao.del(mid);
	}
	
	
	
	public void edit(MetasVO meta){
			metasDao.edit(meta);
	}
	
	
	public List<MetasVO> listAll(){
		return metasDao.find();
	}


	public MetasVO findMid(int mid) {
		return metasDao.findMid(mid);
	}

	
}
