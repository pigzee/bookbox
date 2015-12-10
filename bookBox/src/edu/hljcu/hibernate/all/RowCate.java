package edu.hljcu.hibernate.all;

import java.util.HashSet;
import java.util.Set;

/**
 * RowCate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class RowCate implements java.io.Serializable {

	// Fields

	private Integer rowCateId;
	private FloorCate floorCate;
	private String cateName;
	private Set boxes = new HashSet(0);

	// Constructors

	/** default constructor */
	public RowCate() {
	}

	/** minimal constructor */
	public RowCate(String cateName) {
		this.cateName = cateName;
	}

	/** full constructor */
	public RowCate(FloorCate floorCate, String cateName, Set boxes) {
		this.floorCate = floorCate;
		this.cateName = cateName;
		this.boxes = boxes;
	}

	// Property accessors

	public Integer getRowCateId() {
		return this.rowCateId;
	}

	public void setRowCateId(Integer rowCateId) {
		this.rowCateId = rowCateId;
	}

	public FloorCate getFloorCate() {
		return this.floorCate;
	}

	public void setFloorCate(FloorCate floorCate) {
		this.floorCate = floorCate;
	}

	public String getCateName() {
		return this.cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Set getBoxes() {
		return this.boxes;
	}

	public void setBoxes(Set boxes) {
		this.boxes = boxes;
	}

}