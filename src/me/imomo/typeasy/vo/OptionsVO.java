package me.imomo.typeasy.vo;

import java.io.Serializable;

public class OptionsVO implements Serializable {
	private String name;
	private Integer user = 0;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public OptionsVO() {
		super();
	}

}
