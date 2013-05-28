package me.imomo.typeasy.vo;

import java.io.Serializable;

/**
 * User Value Bean
 * @version 1.0	2013/05/01
 * @author Mo
 *
 */
public class UsersVO implements Serializable {
	private Integer uid;
	private String name;
	private String password;
	private String mail;
	private String url;
	private String screenName;
	private String avatar;
	private String created;
	private String activated;
	private String logged;
	private String group;
	private String authCode;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getActivated() {
		return activated;
	}
	public void setActivated(String activated) {
		this.activated = activated;
	}
	public String getLogged() {
		return logged;
	}
	public void setLogged(String logged) {
		this.logged = logged;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public UsersVO() {
		super();
	}

	
}
