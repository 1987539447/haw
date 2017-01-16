package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * user 与 org 的中间表
 */
@XmlRootElement //默认Entity bean都能解析为xml 必须加入这个注解
public class UserOrgLink extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "UserOrg";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_USERID = "用户编号";
	public static final String ALIAS_ORGID = "机构编号";
	public static final String ALIAS_MANAGER = "0.不是1.是";
    */
	//date formats
	
	//columns START
	/**
	 * 编号
	 */
	private String id;
	/**
	 * 用户编号
	 */
	private String userId;
	/**
	 * 机构编号
	 */
	private String orgId;
	/**
	 * 0.不是1.是
	 */
	private Integer manager;
	//columns END 数据库字段结束

	//concstructor

	public UserOrgLink(){
	}

	public UserOrgLink(String id){
		this.id = id;
	}

	//get and set
	public void setId(String value) {
		this.id = value;
	}


	public String getId() {
		return this.id;
	}
	public void setUserId(String value) {
		this.userId = value;
	}

	public String getUserId() {
		return this.userId;
	}
	public void setOrgId(String value) {
		this.orgId = value;
	}

	public String getOrgId() {
		return this.orgId;
	}
	public void setManager(Integer value) {
		this.manager = value;
	}

	public Integer getManager() {
		return this.manager;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("编号[").append(getId()).append("],")
			.append("用户编号[").append(getUserId()).append("],")
			.append("机构编号[").append(getOrgId()).append("],")
			.append("0.不是1.是[").append(getManager()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserOrgLink == false) return false;
		if(this == obj) return true;
		UserOrgLink other = (UserOrgLink)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
