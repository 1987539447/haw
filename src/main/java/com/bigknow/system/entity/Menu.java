package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.beans.Transient;
import java.util.List;
@XmlRootElement //默认Entity bean都能解析为xml 必须加入这个注解
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //alias
    /*
	public static final String TABLE_ALIAS = "Menu";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_PID = "pid";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_PAGEURL = "pageurl";
	public static final String ALIAS_TYPE = "0.普通资源1.菜单资源";
	public static final String ALIAS_SYSTEMID = "systemId";
	public static final String ALIAS_STATE = "state";
    */
    //date formats

    //columns START
    /**
     * id
     */
    private String id;
    /**
     * name
     */
    private String name;
    /**
     * pid
     */
    private String pid;
    /**
     * description
     */
    private String description;
    /**
     * pageurl
     */
    private String pageurl;
    /**
     * 0.普通资源1.菜单资源
     */
    private Integer type;
    /**
     * 排序
     */

    private Integer sort;
    /**
     * 图标样式
     */
    private String icon0;


    /**
     * state
     */
    private String state;
    //columns END 数据库字段结束
    private String pidName;

    @Transient
    public String getPidName() {
        return pidName;
    }

    public void setPidName(String pidName) {
        this.pidName = pidName;
    }

    //
    private List<Menu> leaf;

    //concstructor

    public Menu() {
    }

    public Menu(
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

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setPageurl(String value) {
        this.pageurl = value;
    }

    public String getPageurl() {
        return this.pageurl;
    }

    public void setType(Integer value) {
        this.type = value;
    }

    public Integer getType() {
        return this.type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon0() {
        return icon0;
    }

    public void setIcon0(String icon0) {
        this.icon0 = icon0;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getState() {
        return this.state;
    }

    public String toString() {
        return new StringBuffer()
                .append("id[").append(getId()).append("],")
                .append("name[").append(getName()).append("],")
                .append("pid[").append(getPid()).append("],")
                .append("description[").append(getDescription()).append("],")
                .append("pageurl[").append(getPageurl()).append("],")
                .append("0.普通资源1.菜单资源[").append(getType()).append("],")
                .append("state[").append(getState()).append("],")
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Menu == false) return false;
        if (this == obj) return true;
        Menu other = (Menu) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    @Transient
    public List<Menu> getLeaf() {
        return leaf;
    }

    public void setLeaf(List<Menu> leaf) {
        this.leaf = leaf;
    }
}

	
