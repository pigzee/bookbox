package edu.hljcu.action;

import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.service.IBookBoxSrv;

public class ListLeftBoxAction extends ActionSupport {
	IBookBoxSrv bookBoxSrv = null;
	private String floorCateId = null;
	public String execute()throws Exception{
		return SUCCESS;
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}
	public String getFloorCateId() {
		return floorCateId;
	}
	public void setFloorCateId(String floorCateId) {
		this.floorCateId = floorCateId;
	}
	
}
