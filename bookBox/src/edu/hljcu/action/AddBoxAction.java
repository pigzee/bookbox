package edu.hljcu.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.Box;
import edu.hljcu.service.IBookBoxSrv;

public class AddBoxAction extends ActionSupport {
	IBookBoxSrv bookBoxSrv = null;
	private String boxName = null;
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
			bookBoxSrv.addBox(rowCateId, boxName.trim());
			return SUCCESS;
		}
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}
	public String getBoxName() {
		return boxName;
	}
	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}
	public String getRowCateId() {
		return rowCateId;
	}
	public void setRowCateId(String rowCateId) {
		this.rowCateId = rowCateId;
	}

	
}
