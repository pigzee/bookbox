package edu.hljcu.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.Box;
import edu.hljcu.service.IBookBoxSrv;

public class DeleteBoxAction extends ActionSupport {

	IBookBoxSrv bookBoxSrv = null;
	private String boxId = null;
	
	public String execute() throws Exception{
		
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			//删除外键关系
			bookBoxSrv.deleteBox(boxId);
			return SUCCESS;
		}
		

	}

	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}

	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}

	public String getBoxId() {
		return boxId;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}
	
	
}
