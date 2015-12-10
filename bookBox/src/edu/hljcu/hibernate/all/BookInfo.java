package edu.hljcu.hibernate.all;

import java.util.Date;

/**
 * BookInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BookInfo implements java.io.Serializable {

	// Fields

	private Integer bookInfoId;
	private Box box;
	private String studNo;
	private String studName;
	private String status;
	private Date bookTime;
	private Float currBoxprice;
	private Integer ownCount;
	private Date payTime;
	private String boxPlace;
	private String college;
	private String studClass;
	private String phone;
	// Constructors

	/** default constructor */
	public BookInfo() {
	}

	/** minimal constructor */
	public BookInfo(String studNo, String studName, String status,
			Date bookTime, Float currBoxprice, Integer ownCount,Date payTime,String boxPlace,String college,String studClass,String phone) {
		this.studNo = studNo;
		this.studName = studName;
		this.status = status;
		this.bookTime = bookTime;
		this.currBoxprice = currBoxprice;
		this.ownCount = ownCount;
		this.payTime = payTime;
		this.boxPlace = boxPlace;
		this.college = college;
		this.studClass = studClass;
		this.phone = phone;
	}

	/** full constructor */
	public BookInfo(Box box, String studNo, String studName, String status,
			Date bookTime, Float currBoxprice, Integer ownCount,Date payTime,String boxPlace,String college,String studClass,String phone) {
		this.box = box;
		this.studNo = studNo;
		this.studName = studName;
		this.status = status;
		this.bookTime = bookTime;
		this.currBoxprice = currBoxprice;
		this.ownCount = ownCount;
		this.payTime = payTime;
		this.boxPlace = boxPlace;
		this.college = college;
		this.studClass = studClass;
		this.phone = phone;
	}

	// Property accessors

	public Integer getBookInfoId() {
		return this.bookInfoId;
	}

	public void setBookInfoId(Integer bookInfoId) {
		this.bookInfoId = bookInfoId;
	}

	public Box getBox() {
		return this.box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public String getStudNo() {
		return this.studNo;
	}

	public void setStudNo(String studNo) {
		this.studNo = studNo;
	}

	public String getStudName() {
		return this.studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBookTime() {
		return this.bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}


	public Float getCurrBoxprice() {
		return currBoxprice;
	}

	public void setCurrBoxprice(Float currBoxprice) {
		this.currBoxprice = currBoxprice;
	}

	public Integer getOwnCount() {
		return this.ownCount;
	}

	public void setOwnCount(Integer ownCount) {
		this.ownCount = ownCount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getBoxPlace() {
		return boxPlace;
	}

	public void setBoxPlace(String boxPlace) {
		this.boxPlace = boxPlace;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getStudClass() {
		return studClass;
	}

	public void setStudClass(String studClass) {
		this.studClass = studClass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}