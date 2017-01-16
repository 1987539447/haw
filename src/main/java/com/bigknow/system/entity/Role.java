package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;
import com.bigknow.frame.entity.IEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement //默认Entity bean都能解析为xml 必须加入这个注解
public class Role extends BaseEntity implements IEntity {

    private static final long serialVersionUID = 1L;

    //alias
    /*
	public static final String TABLE_ALIAS = "Role";
	public static final String ALIAS_ID = "角色ID";
	public static final String ALIAS_NAME = "角色名称";
	public static final String ALIAS_PERMISSIONS = "权限";
	public static final String ALIAS_PID = "所属部门";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_STATE = "状态(0:禁用2:启用)";
    */
    //date formats

    //columns START
    /**
     * 角色ID
     */
    private String id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码，用于生成权限框架的惟一标识权限
     */
    private String code;
    /**
     * 所属部门
     */
    private String pid;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态(0:禁用1:启用)
     */
    private String state;
    //columns END 数据库字段结束

    private List<Menu> menus;


    private String menuIds ="";

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }

//concstructor

    public Role() {
    }

    public Role(
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

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setPid(String value) {
        this.pid = value;
    }

    public String getPid() {
        return this.pid;
    }

    public void setRemark(String value) {
        this.remark = value;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getState() {
        return this.state;
    }

    public String toString() {
        return new StringBuffer()
                .append("角色ID[").append(getId()).append("],")
                .append("角色名称[").append(getName()).append("],")
                .append("权限编码[").append(getCode()).append("],")
                .append("所属部门[").append(getPid()).append("],")
                .append("备注[").append(getRemark()).append("],")
                .append("状态(0:禁用1:启用)[").append(getState()).append("],")
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Role == false) return false;
        if (this == obj) return true;
        Role other = (Role) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

	
