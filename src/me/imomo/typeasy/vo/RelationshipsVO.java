package me.imomo.typeasy.vo;

import java.io.Serializable;

public class RelationshipsVO implements Serializable {
	private Integer cid;
	private Integer mid;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	
	public RelationshipsVO() {
		super();
	}

	
}
