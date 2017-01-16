package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement //默认Entity bean都能解析为xml 必须加入这个注解
public class RoleMenuLink extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //alias
    /*
	public static final String TABLE_ALIAS = "RoleMenu";
	public static final String ALIAS_ID = "编号";
	public static final String ALIAS_ROLEID = "角色编号";
	public static final String ALIAS_MENUID = "菜单编号";
    */
    //date formats

    //columns START
    /**
     * 编号
     */
    private String id;
    /**
     * 角色编号
     */
    private String roleId;
    /**
     * 菜单编号
     */
    private String menuId;
    //columns END 数据库字段结束

    //concstructor

    public RoleMenuLink() {
    }

    public RoleMenuLink(
            String id
    ) {
        this.id = id;
    }

    //get and set
    public void setId(String value) {
        this.id = value;
    }


    public String getId() {
        return this.id;
    }

    public void setRoleId(String value) {
        this.roleId = value;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setMenuId(String value) {
        this.menuId = value;
    }

    public String getMenuId() {
        return this.menuId;
    }

    public String toString() {
        return new StringBuffer()
                .append("编号[").append(getId()).append("],")
                .append("角色编号[").append(getRoleId()).append("],")
                .append("菜单编号[").append(getMenuId()).append("],")
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof RoleMenuLink == false) return false;
        if (this == obj) return true;
        RoleMenuLink other = (RoleMenuLink) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

	
