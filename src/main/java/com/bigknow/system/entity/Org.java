package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.beans.Transient;
import java.util.List;

public class Org  extends BaseEntity {

	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "Org";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_COMCODE = "代码";
	public static final String ALIAS_PID = "上级部门ID";
	public static final String ALIAS_SYSID = "子系统ID";
	public static final String ALIAS_TYPE = "0,组织机构 1.部门";
	public static final String ALIAS_LEAF = "叶子节点(0:树枝节点;1:叶子节点)";
	public static final String ALIAS_SORTNO = "排序号";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_STATE = "0.失效 1.有效";
    */
	//date formats

	//columns START
	/**
	 * 编号
	 */
	private String id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 代码
	 */
	private String comcode;
	/**
	 * 上级部门ID
	 */
	private String pid;
	/**
	 * 子系统ID
	 */
	private String sysid;
	/**
	 * 0,组织机构 1.部门
	 */
	private Integer type;
	/**
	 * 叶子节点(0:树枝节点;1:叶子节点)
	 */
	private Integer leaf;
	/**
	 * 排序号
	 */
	private Integer sortno;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 0.失效 1.有效
	 */
	private String state;
	//columns END 数据库字段结束



	private List<Org> leafOrg;

	//concstructor

	public Org(){
	}

	public Org(
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
	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}
	public void setComcode(String value) {
		this.comcode = value;
	}

	public String getComcode() {
		return this.comcode;
	}
	public void setPid(String value) {
		this.pid = value;
	}

	public String getPid() {
		return this.pid;
	}
	public void setSysid(String value) {
		this.sysid = value;
	}

	public String getSysid() {
		return this.sysid;
	}
	public void setType(Integer value) {
		this.type = value;
	}

	public Integer getType() {
		return this.type;
	}
	public void setLeaf(Integer value) {
		this.leaf = value;
	}

	public Integer getLeaf() {
		return this.leaf;
	}
	public void setSortno(Integer value) {
		this.sortno = value;
	}

	public Integer getSortno() {
		return this.sortno;
	}
	public void setDescription(String value) {
		this.description = value;
	}

	public String getDescription() {
		return this.description;
	}
	public void setState(String value) {
		this.state = value;
	}

	public String getState() {
		return this.state;
	}

	public String toString() {
		return new StringBuffer()
			.append("编号[").append(getId()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("代码[").append(getComcode()).append("],")
			.append("上级部门ID[").append(getPid()).append("],")
			.append("子系统ID[").append(getSysid()).append("],")
			.append("0,组织机构 1.部门[").append(getType()).append("],")
			.append("叶子节点(0:树枝节点;1:叶子节点)[").append(getLeaf()).append("],")
			.append("排序号[").append(getSortno()).append("],")
			.append("描述[").append(getDescription()).append("],")
			.append("0.失效 1.有效[").append(getState()).append("],")
			.toString();
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}

	public boolean equals(Object obj) {
		if(obj instanceof Org == false) return false;
		if(this == obj) return true;
		Org other = (Org)obj;
		return new EqualsBuilder()
			.append(getId(), other.getId())
			.isEquals();
	}

	@Transient
	public List<Org> getLeafOrg() {
		return leafOrg;
	}

	public void setLeafOrg(List<Org> leafOrg) {
		this.leafOrg = leafOrg;
	}
}

	
