package com.hsjc.central.importMongoData.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 机构
 * Created by henry on 12/23/14.
 */
@Document
public class Organization {

	private String id;

	/**
	 * 名称
	 */
	private String name ="";

	/**
	 * 站点状态
	 * @see com.hsjc.schoolSpace.core.constant.StatusConstant
	 */
	private Integer status;

	/**
	 * 类型
	 * @see com.hsjc.schoolSpace.core.constant.TypeConstant#
	 */
	private Integer type;


	/**
	 * 体制类型
	 * @see com.hsjc.schoolSpace.core.constant.Constant.FIVE_FOUR
	 */
	private Integer system ;

	/**
	 * 站点描述
	 */
	private String description ="";

	/**
	 * 省份
	 */
	private Integer province;

	/**
	 * 城市
	 */
	private Integer city;

	/**
	 * 地区
	 */
	private Integer area;

	/**
	 * 学校地址    
	 */
	private String address ="";

	/**
	 * 父机构id
	 * @see com.hsjc.centralApi.core.entity.mongodb.Organization#id
	 */
	private String parentId ="";

	/**
	 * 数据添加时间
	 */
	private Date addTime;

	public Organization(){
		
		addTime = new Date();
	
	}

	public Integer getSystem() {
		return system;
	}

	public void setSystem(Integer system) {
		this.system = system;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getAddTime() {
		return addTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "Organization{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", status=" + status +
				", type=" + type +
				", system=" + system +
				", description='" + description + '\'' +
				", province=" + province +
				", city=" + city +
				", area=" + area +
				", address='" + address + '\'' +
				", parentId='" + parentId + '\'' +
				", addTime=" + addTime +
				'}';
	}
}
