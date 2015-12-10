package edu.hljcu.hibernate.all;

import java.util.HashSet;
import java.util.Set;

/**
 * Box entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Box implements java.io.Serializable {

	// Fields

	private Integer boxId;
	private RowCate rowCate;
	private String boxName;
	private String status;
	private Set bookInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Box() {
	}

	/** minimal constructor */
	public Box(String boxName, String status) {
		this.boxName = boxName;
		this.status = status;
	}

	/** full constructor */
	public Box(RowCate rowCate, String boxName, String status, Set bookInfos) {
		this.rowCate = rowCate;
		this.boxName = boxName;
		this.status = status;
		this.bookInfos = bookInfos;
	}

	// Property accessors

	public Integer getBoxId() {
		return this.boxId;
	}

	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}

	public RowCate getRowCate() {
		return this.rowCate;
	}

	public void setRowCate(RowCate rowCate) {
		this.rowCate = rowCate;
	}

	public String getBoxName() {
		return this.boxName;
	}

	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set getBookInfos() {
		return this.bookInfos;
	}

	public void setBookInfos(Set bookInfos) {
		this.bookInfos = bookInfos;
	}

}