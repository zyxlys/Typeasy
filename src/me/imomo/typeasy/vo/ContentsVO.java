package me.imomo.typeasy.vo;

import java.io.Serializable;

/**
 * 
 * @author Mo
 *
 */
public class ContentsVO implements Serializable {
	private Integer cid;
	private String title;
	private String slug;
	private String created;
	private String modified;
	private String text;
	private Integer order;
	private Integer authorId;
	private String template;
	private String type;
	private String status;
	private String password;
	private Integer commentsNum;
	private String allowComment = "1";
	private String allowPing = "1";
	private String allowFeed = "1";
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getCommentsNum() {
		return commentsNum;
	}
	public void setCommentsNum(Integer commentsNum) {
		this.commentsNum = commentsNum;
	}
	public String getAllowComment() {
		return allowComment;
	}
	public void setAllowComment(String allowComment) {
		this.allowComment = allowComment;
	}
	public String getAllowPing() {
		return allowPing;
	}
	public void setAllowPing(String allowPing) {
		this.allowPing = allowPing;
	}
	public String getAllowFeed() {
		return allowFeed;
	}
	public void setAllowFeed(String allowFeed) {
		this.allowFeed = allowFeed;
	}
	public ContentsVO() {
		super();
	}

	
}
