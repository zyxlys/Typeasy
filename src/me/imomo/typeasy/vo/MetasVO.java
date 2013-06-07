package me.imomo.typeasy.vo;

import java.io.Serializable;

/**
 * Metas VO
 * 
 * @author Administrator
 * 
 */
public class MetasVO implements Serializable {
	private Integer mid;
	private String name;
	private String slug;
	private String type;
	private String description;
	private Integer count;
	private Integer order = 0;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public MetasVO() {
		super();
	}

}
