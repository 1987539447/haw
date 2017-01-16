package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 */
public class DicData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //alias
    /*
	public static final String TABLE_ALIAS = "公共字典";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_CODE = "编码";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_SORT = "sort";
	public static final String ALIAS_STATE = "是否有效";
	public static final String ALIAS_TYPEKEY = "类型";
    */
    //date formats

    //columns START
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;

    /**
     * 父ID
     */
    private String pid;


    /**
     * 描述
     */
    private String remark;

    /**
     * 是否有效
     */
    private String state;
    /**
     * 类型
     */
    private String typekey;

    private Integer sort;
    //columns END 数据库字段结束

    //concstructor

    public DicData() {
    }

    public DicData(
            String id
    ) {
        this.id = id;
    }

    //get and set
    public void setId(String value) {
        this.id = trim(value);
    }

    public String getId() {
        return this.id;
    }

    public void setName(String value) {

        this.name = trim(value);
    }

    public String getName() {
        return this.name;
    }

    public void setCode(String value) {

        this.code = trim(value);
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCode() {
        return this.code;
    }

    public void setRemark(String value) {

        this.remark = trim(value);
    }

    public String getRemark() {
        return this.remark;
    }

    public void setState(String value) {

        this.state = trim(value);
    }

    public String getState() {
        return this.state;
    }

    public void setTypekey(String value) {

        this.typekey = trim(value);
    }

    public String getTypekey() {
        return this.typekey;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String toString() {
        return new StringBuffer()
                .append("id[").append(getId()).append("],")
                .append("名称[").append(getName()).append("],")
                .append("编码[").append(getCode()).append("],")
                .append("描述[").append(getRemark()).append("],")
                .append("是否有效[").append(getState()).append("],")
                .append("类型[").append(getTypekey()).append("],")
                .append("排序[").append(getSort()).append("],")
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof DicData == false) return false;
        if (this == obj) return true;
        DicData other = (DicData) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

	
