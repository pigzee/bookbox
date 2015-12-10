package edu.hljcu.hibernate.all;

import java.util.Date;

/**
 * AdminOper entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AdminOper implements java.io.Serializable {

	// Fields

	private Integer adminOperId;
	private String adminName;
	private String adminPsw;
	private Float boxPrice;
	private Date bookBeginTime;
	private Date bookEndTime;
	private Date conBeginTime;
	private Date conEndTime;
	private String maxBoxCount;
	// Constructors

	/** default constructor */
	public AdminOper() {
	}

	/** full constructor */
	public AdminOper(String adminName, String adminPsw, Float boxPrice,
			Date bookBeginTime,Date bookEndTime,Date conBeginTime,Date conEndTime,String maxBoxCount) {
		this.adminName = adminName;
		this.adminPsw = adminPsw;
		this.boxPrice = boxPrice;
		this.bookBeginTime =bookBeginTime;
		this.bookEndTime =bookEndTime;
		this.conBeginTime =conBeginTime;
		this.conEndTime =conEndTime;
		this.maxBoxCount = maxBoxCount;
	}

	// Property accessors

	public Integer getAdminOperId() {
		return this.adminOperId;
	}

	public void setAdminOperId(Integer adminOperId) {
		this.adminOperId = adminOperId;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPsw() {
		return this.adminPsw;
	}

	public void setAdminPsw(String adminPsw) {
		this.adminPsw = adminPsw;
	}


	public Float getBoxPrice() {
		return boxPrice;
	}

	public void setBoxPrice(Float boxPrice) {
		this.boxPrice = boxPrice;
	}

	public Date getBookBeginTime() {
		return bookBeginTime;
	}

	public void setBookBeginTime(Date bookBeginTime) {
		this.bookBeginTime = bookBeginTime;
	}

	public Date getBookEndTime() {
		return bookEndTime;
	}

	public void setBookEndTime(Date bookEndTime) {
		this.bookEndTime = bookEndTime;
	}

	public Date getConBeginTime() {
		return conBeginTime;
	}

	public void setConBeginTime(Date conBeginTime) {
		this.conBeginTime = conBeginTime;
	}

	public Date getConEndTime() {
		return conEndTime;
	}

	public void setConEndTime(Date conEndTime) {
		this.conEndTime = conEndTime;
	}

	public String getMaxBoxCount() {
		return maxBoxCount;
	}

	public void setMaxBoxCount(String maxBoxCount) {
		this.maxBoxCount = maxBoxCount;
	}


}