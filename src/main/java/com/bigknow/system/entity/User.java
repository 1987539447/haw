package com.bigknow.system.entity;

import com.bigknow.frame.entity.BaseEntity;
import com.bigknow.frame.entity.IEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement //默认Entity bean都能解析为xml 必须加入这个注解
public class User extends BaseEntity implements IEntity {

    /**
     * 编号
     */
    private String id;
    /**
     * 姓名
    */
    private String name;
    /**
     * 工号
     */
    private String workno;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 身份证
     */
    private String cardno;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private String sex;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 地址
     */
    private String address;
    /**
     * 级别
     */
    private String gradeId;
    /**
     * 学历
     */
    private String eduName;

    /**
     * 紧急联系人
     */
    private String fireName;
    /**
     * 紧急联系电话
     */
    private String firePhone;
    /**
     * 备注
     */
    private String description;
    /**
     * 是否有效,是/否/离职
     */
    private String state;


    /**
     * 微信Id
     */
    private String weixinId;


    //columns END 数据库字段结束


    //用户所有的部门
    private List<Org> userOrgs;

    //用户的所有角色
    private List<Role> userRoles;

    //城市节点
    private int cityId;

    public User() {

    }

    public User(String id) {
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

    public void setWorkno(String workno) {
        this.workno = trim(workno);
    }

    public String getWorkno() {
        return this.workno;
    }

    public void setAccount(String value) {
        this.account = trim(value);
    }

    public String getAccount() {
        return this.account;
    }

    public void setPassword(String value) {

        this.password = trim(value);
    }

    public String getPassword() {
        return this.password;
    }

    public void setCardno(String value) {

        this.cardno = trim(value);
    }

    public String getCardno() {
        return this.cardno;
    }

    public void setAge(Integer value) {
        this.age = value;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setSex(String value) {

        this.sex = trim(value);
    }

    public String getSex() {
        return this.sex;
    }

    public void setPhone(String value) {

        this.phone = trim(value);
    }

    public String getPhone() {
        return this.phone;
    }

    public void setMobile(String value) {

        this.mobile = trim(value);
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setEmail(String value) {

        this.email = trim(value);
    }

    public String getEmail() {
        return this.email;
    }

    public void setAddress(String value) {

        this.address = trim(value);
    }

    public String getAddress() {
        return this.address;
    }

    public void setGradeId(String value) {

        this.gradeId = trim(value);
    }

    public String getGradeId() {
        return this.gradeId;
    }

    public String getEduName() {
        return eduName;
    }

    public void setEduName(String eduName) {
        this.eduName = eduName;
    }


    public void setFireName(String value) {

        this.fireName = trim(value);
    }

    public String getFireName() {
        return this.fireName;
    }

    public void setFirePhone(String value) {

        this.firePhone = trim(value);
    }

    public String getFirePhone() {
        return this.firePhone;
    }

    public void setDescription(String value) {

        this.description = trim(value);
    }

    public String getDescription() {
        return this.description;
    }

    public void setState(String value) {
        this.state = trim(value);
    }

    public String getState() {
        return this.state;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public List<Org> getUserOrgs() {
        return userOrgs;
    }

    public void setUserOrgs(List<Org> userOrgs) {
        this.userOrgs = userOrgs;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof User == false) return false;
        if (this == obj) return true;
        User other = (User) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }



}

	
