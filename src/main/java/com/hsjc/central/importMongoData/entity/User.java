
package com.hsjc.central.importMongoData.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * Created by henry on 12/8/14.
 */
public class User implements Serializable{
	private String id;

	/**
	 * 昵称
	 * TODO 需要吗？
	 */
	private String nickname = "";

	/**
	 * 真实姓名
	 */
	private String realName = "";

	/**
	 * 类型
	 * @see com.hsjc.core.constant.TypeConstant
	 */
	private Integer type;

	/**
	 * 用户头像
	 */
	private String userIcon ="";

	/**
	 * 云账号
	 */
	private Long starBeldId;

	/**
	 * 邮箱
	 */
	private String email ="";

	/**
	 * 师训号
	 */
	private String teacherNum ="";

	/**
	 * 学籍号
	 */
	private String studentNum ="";

	/**
	 * 身份证号
	 */
	private Long idCardNum;

	/**
	 * 注册时间
	 */
//	private Date registerTime;

	/**
	 * 邮箱激活状态
	 *
	 */
	private Integer emailValidateStatus;

	/**
	 * 手机号码
	 */
	private String mobile ="";

	/**
	 * 出生日期
	 */
	private Date birthday;

	/**
	 * 性别
	 */
	private Integer gender;

	/**
	 * 祖籍市
	 */
	private Integer birthPlace;

	/**
	 * 居住市
	 */
	private Integer residePlace;

	/**
	 * 教龄
	 */
	private Integer seniority;

	/**
	 * 职称
	 */
	private Integer jobTitle;

	/**
	 * 民族
	 */
	private Integer nationality;

	/**
	 * 管理员类型
	 * @see com.hsjc.authLogin.core.constant.TypeConstant
	 */
	private Integer adminType;

	/**
	 * 数据添加时间（注册时间）
	 */
	private Date addTime;

	/**
	 * 是否助教
	 * 是：1
	 * 否：0
	 */
	private int tutorState;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getStarBeldId() {
		return starBeldId;
	}

	public void setStarBeldId(Long starBeldId) {
		this.starBeldId = starBeldId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public Long getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(Long idCardNum) {
		this.idCardNum = idCardNum;
	}

	public Integer getEmailValidateStatus() {
		return emailValidateStatus;
	}

	public void setEmailValidateStatus(Integer emailValidateStatus) {
		this.emailValidateStatus = emailValidateStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(Integer birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Integer getResidePlace() {
		return residePlace;
	}

	public void setResidePlace(Integer residePlace) {
		this.residePlace = residePlace;
	}

	public Integer getSeniority() {
		return seniority;
	}

	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}

	public Integer getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(Integer jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Integer getNationality() {
		return nationality;
	}

	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}

	public Integer getAdminType() {
		return adminType;
	}

	public void setAdminType(Integer adminType) {
		this.adminType = adminType;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public int getTutorState() {
		return tutorState;
	}

	public void setTutorState(int tutorState) {
		this.tutorState = tutorState;
	}

	@Override
	public String toString() {
		return getRealName();
	}

}
