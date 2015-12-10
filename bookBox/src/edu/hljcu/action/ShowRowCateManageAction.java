package edu.hljcu.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.service.IBookBoxSrv;

public class ShowRowCateManageAction extends ActionSupport {

	private IBookBoxSrv bookBoxSrv = null;
	private List allRowCates = null;
	private String floorCateId = null;
	public String execute()throws Exception{
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			allRowCates = bookBoxSrv.getAllRowCates(floorCateId);
			return SUCCESS;
		}
		
		
	}

	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}

	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}

	public List getAllRowCates() {
		return allRowCates;
	}

	public void setAllRowCates(List allRowCates) {
		this.allRowCates = allRowCates;
	}

	public String getFloorCateId() {
		return floorCateId;
	}

	public void setFloorCateId(String floorCateId) {
		this.floorCateId = floorCateId;
	}
	
	
}
