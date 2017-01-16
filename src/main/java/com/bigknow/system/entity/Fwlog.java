package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;
import com.bigknow.frame.util.GlobalStatic;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.beans.Transient;
import java.util.Calendar;


public class Fwlog  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Fwlog";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_STARTDATE = "访问时间";
	public static final String ALIAS_STRDATE = "访问时间(毫秒)";
	public static final String ALIAS_TOMCAT = "Tomcat";
	public static final String ALIAS_USERCODE = "登陆账号";
	public static final String ALIAS_USERNAME = "姓名";
	public static final String ALIAS_SESSIONID = "sessionId";
	public static final String ALIAS_IP = "IP";
	public static final String ALIAS_FWURL = "访问菜单";
	public static final String ALIAS_MENUNAME = "菜单名称";
	public static final String ALIAS_ISQX = "是否有权限访问";
    */
	//date formats
	//public static final String FORMAT_STARTDATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * ID
	 */
	private String id;
	/**
	 * 访问时间
	 */
	private java.util.Date startDate;
	/**
	 * 访问时间(毫秒)
	 */
	private String strDate;
	/**
	 * Tomcat
	 */
	private String tomcat;
	/**
	 * 登陆账号
	 */
	private String userCode;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * sessionId
	 */
	private String sessionId;
	/**
	 * IP
	 */
	private String ip;
	/**
	 * 访问菜单
	 */
	private String fwUrl;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 是否有权限访问
	 */
	private String isqx;
	//columns END 数据库字段结束

	private String ext;

	//concstructor

	public Fwlog(){
	}

	public Fwlog(
		String id
	){
		this.id = id;
	}

	//get and set
	public void setId(String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}

	public String getId() {
		return this.id;
	}
		/*
	public String getstartDateString() {
		return DateUtils.convertDate2String(FORMAT_STARTDATE, getstartDate());
	}
	public void setstartDateString(String value) throws ParseException{
		setstartDate(DateUtils.convertString2Date(FORMAT_STARTDATE,value));
	}*/

	public void setStartDate(java.util.Date value) {
		this.startDate = value;
	}

	public java.util.Date getStartDate() {
		return this.startDate;
	}
	public void setStrDate(String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.strDate = value;
	}

	public String getStrDate() {
		return this.strDate;
	}
	public void setTomcat(String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tomcat = value;
	}

	public String getTomcat() {
		return this.tomcat;
	}
	public void setUserCode(String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userCode = value;
	}

	public String getUserCode() {
		return this.userCode;
	}
	public void setUserName(String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userName = value;
	}

	public String getUserName() {
		return this.userName;
	}
	public void setSessionId(String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sessionId = value;
	}

	public String getSessionId() {
		return this.sessionId;
	}
	public void setIp(String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.ip = value;
	}

	public String getIp() {
		return this.ip;
	}
	public void setFwUrl(String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fwUrl = value;
	}

	public String getFwUrl() {
		return this.fwUrl;
	}
	public void setMenuName(String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.menuName = value;
	}

	public String getMenuName() {
		return this.menuName;
	}
	public void setIsqx(String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.isqx = value;
	}

	public String getIsqx() {
		return this.isqx;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("ID[").append(getId()).append("],")
			.append("访问时间[").append(getStartDate()).append("],")
			.append("访问时间(毫秒)[").append(getStrDate()).append("],")
			.append("Tomcat[").append(getTomcat()).append("],")
			.append("登陆账号[").append(getUserCode()).append("],")
			.append("姓名[").append(getUserName()).append("],")
			.append("sessionId[").append(getSessionId()).append("],")
			.append("IP[").append(getIp()).append("],")
			.append("访问菜单[").append(getFwUrl()).append("],")
			.append("菜单名称[").append(getMenuName()).append("],")
			.append("是否有权限访问[").append(getIsqx()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Fwlog == false) return false;
		if(this == obj) return true;
		Fwlog other = (Fwlog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	
	@Transient
	public String getExt() {
		if(StringUtils.isBlank(ext)){
			int year= Calendar.getInstance().get(Calendar.YEAR);
			this.ext= GlobalStatic.tableExt + year;
		}
			return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
}

	
