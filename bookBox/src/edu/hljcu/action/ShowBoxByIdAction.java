package edu.hljcu.action;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.Box;
import edu.hljcu.service.IBookBoxSrv;

public class ShowBoxByIdAction extends ActionSupport {
	IBookBoxSrv bookBoxSrv = null;
	private Box box = null;
	private String boxId = null;
	private String rowCateId = null;
	public String execute()throws Exception{
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			box = bookBoxSrv.showBoxById(boxId);
			return SUCCESS;
		}
		

	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	public String getBoxId() {
		return boxId;
	}
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}
	public String getRowCateId() {
		return rowCateId;
	}
	public void setRowCateId(String rowCateId) {
		this.rowCateId = rowCateId;
	}

	
	
}
