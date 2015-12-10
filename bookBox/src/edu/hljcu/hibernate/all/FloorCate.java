package edu.hljcu.hibernate.all;

import java.util.HashSet;
import java.util.Set;

/**
 * FloorCate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FloorCate implements java.io.Serializable {

	// Fields

	private Integer floorCateId;
	private String cateName;
	private Set rowCates = new HashSet(0);

	// Constructors

	/** default constructor */
	public FloorCate() {
	}

	/** minimal constructor */
	public FloorCate(String cateName) {
		this.cateName = cateName;
	}

	/** full constructor */
	public FloorCate(String cateName, Set rowCates) {
		this.cateName = cateName;
		this.rowCates = rowCates;
	}

	// Property accessors

	public Integer getFloorCateId() {
		return this.floorCateId;
	}

	public void setFloorCateId(Integer floorCateId) {
		this.floorCateId = floorCateId;
	}

	public String getCateName() {
		return this.cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Set getRowCates() {
		return this.rowCates;
	}

	public void setRowCates(Set rowCates) {
		this.rowCates = rowCates;
	}

}