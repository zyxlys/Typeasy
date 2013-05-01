package me.imomo.typeasy.vo;

/**
 * User Value Bean
 * @version 1.0	2013/05/01
 * @author Mo
 *
 */
public class User {
	private Integer uid;
	private String name;
	private String password;
	private String mail;
	private String url;
	private String screenName;
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
	public User() {
		super();
	}
	
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public User(Integer uid, String name, String password, String mail,
			String url, String screenName, String created, String activated,
			String logged, String group, String authCode) {
		super();
		this.uid = uid;
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.url = url;
		this.screenName = screenName;
		this.created = created;
		this.activated = activated;
		this.logged = logged;
		this.group = group;
		this.authCode = authCode;
	}
	
	
	
}
