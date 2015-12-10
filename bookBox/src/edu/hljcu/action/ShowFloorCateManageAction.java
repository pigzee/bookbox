package edu.hljcu.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.service.IBookBoxSrv;

public class ShowFloorCateManageAction extends ActionSupport {

	private IBookBoxSrv bookBoxSrv = null;
	private List allFloorCates = null;
	private String adminName = null;
	public String execute()throws Exception{
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			allFloorCates = bookBoxSrv.getAllFloorCate();
			return SUCCESS;
		}
	}

	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}

	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}


	public List getAllFloorCates() {
		return allFloorCates;
	}

	public void setAllFloorCates(List allFloorCates) {
		this.allFloorCates = allFloorCates;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	
}
