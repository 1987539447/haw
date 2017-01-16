package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;
import com.bigknow.frame.entity.IAuditLog;
import com.bigknow.frame.util.GlobalStatic;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.beans.Transient;
import java.util.Calendar;

/**
 *
 */
@XmlRootElement //默认Entity bean都能解析为xml 必须加入这个注解
public class AuditLog extends BaseEntity implements IAuditLog {

    private static final long serialVersionUID = 1L;

    //alias
    /*
	public static final String TABLE_ALIAS = "Auditlog";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_OPERATION_TYPE = "操作类型";
	public static final String ALIAS_OPERATOR_NAME = "操作人姓名";
	public static final String ALIAS_PRE_VALUE = "旧值";
	public static final String ALIAS_CUR_VALUE = "新值";
	public static final String ALIAS_OPERATION_TIME = "操作时间";
	public static final String ALIAS_OPERATION_CLASS = "操作类";
	public static final String ALIAS_OPERATION_CLASS_ID = "记录ID";
    */
    //date formats
    //public static final String FORMAT_OPERATION_TIME = DateUtils.DATETIME_FORMAT;

    //columns START
    /**
     * ID
     */
    private String id;
    /**
     * 操作类型
     */
    private String operationType;
    /**
     * 操作人姓名
     */
    private String operatorName;
    /**
     * 旧值
     */
    private String preValue;
    /**
     * 新值
     */
    private String curValue;
    /**
     * 操作时间
     */
    private java.util.Date operationTime;
    /**
     * 操作类
     */
    private String operationClass;
    /**
     * 记录ID
     */
    private String operationClassId;
    //columns END


    private String ext;

    //concstructor

    public AuditLog() {
    }

    public AuditLog(
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

    public void setOperationType(String value) {
        this.operationType = value;
    }

    public String getOperationType() {
        return this.operationType;
    }

    public void setOperatorName(String value) {
        this.operatorName = value;
    }

    public String getOperatorName() {
        return this.operatorName;
    }

    public void setPreValue(String value) {
        this.preValue = value;
    }

    public String getPreValue() {
        return this.preValue;
    }

    public void setCurValue(String value) {
        this.curValue = value;
    }

    public String getCurValue() {
        return this.curValue;
    }


    public void setOperationTime(java.util.Date value) {
        this.operationTime = value;
    }

    public java.util.Date getOperationTime() {
        return this.operationTime;
    }

    public void setOperationClass(String value) {
        this.operationClass = value;
    }

    public String getOperationClass() {
        return this.operationClass;
    }

    public void setOperationClassId(String value) {
        this.operationClassId = value;
    }

    public String getOperationClassId() {
        return this.operationClassId;
    }

    public String toString() {
        return new StringBuffer()
                .append("ID[").append(getId()).append("],")
                .append("操作类型[").append(getOperationType()).append("],")
                .append("操作人姓名[").append(getOperatorName()).append("],")
                .append("旧值[").append(getPreValue()).append("],")
                .append("新值[").append(getCurValue()).append("],")
                .append("操作时间[").append(getOperationTime()).append("],")
                .append("操作类[").append(getOperationClass()).append("],")
                .append("记录ID[").append(getOperationClassId()).append("],")
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof AuditLog == false) return false;
        if (this == obj) return true;
        AuditLog other = (AuditLog) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }

    @Transient
    public String getExt() {
        if (StringUtils.isBlank(ext)) {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            this.ext = GlobalStatic.tableExt + year;
        }
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

}

	
