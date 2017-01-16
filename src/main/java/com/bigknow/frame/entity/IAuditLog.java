package com.bigknow.frame.entity;

import java.util.Date;

/**
 * 处理公共的日志接口，主要是多个数据库
 */
public interface IAuditLog extends IEntity {

	public void setOperationType(String value);

	public String getOperationType();

	public void setOperatorName(String value);

	public String getOperatorName();

	public void setPreValue(String value);

	public String getPreValue();

	public void setCurValue(String value);

	public String getCurValue();

	public void setOperationTime(Date value);

	public Date getOperationTime();

	public void setOperationClass(String value);

	public String getOperationClass();

	public void setOperationClassId(String value);

	public String getOperationClassId();
	
	public String getExt();
	public void setExt(String ext);
	
	
}
