package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement //默认Entity bean都能解析为xml 必须加入这个注解
public class UserRoleLink extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "UserRole";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_USERID = "用户编号";
	public static final String ALIAS_ROLEID = "角色编号";
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
	 * 角色编号
	 */
	private String roleId;
	//columns END 数据库字段结束

	//concstructor

	public UserRoleLink(){
	}

	public UserRoleLink(
            String id
    ){
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
	public void setRoleId(String value) {
		this.roleId = value;
	}

	public String getRoleId() {
		return this.roleId;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("编号[").append(getId()).append("],")
			.append("用户编号[").append(getUserId()).append("],")
			.append("角色编号[").append(getRoleId()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserRoleLink == false) return false;
		if(this == obj) return true;
		UserRoleLink other = (UserRoleLink)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
