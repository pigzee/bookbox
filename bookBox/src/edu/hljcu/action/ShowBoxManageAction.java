package edu.hljcu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.service.IBookBoxSrv;

public class ShowBoxManageAction extends ActionSupport {

	private IBookBoxSrv bookBoxSrv = null;
	private List allBoxes = null;
	private String rowCateId = null;
	
	public String execute()throws Exception{
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			allBoxes = bookBoxSrv.getAllBoxByRowId(rowCateId);
			return SUCCESS;
		}

	}

	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}

	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}

	public List getAllBoxes() {
		return allBoxes;
	}

	public void setAllBoxes(List allBoxes) {
		this.allBoxes = allBoxes;
	}

	public String getRowCateId() {
		return rowCateId;
	}

	public void setRowCateId(String rowCateId) {
		this.rowCateId = rowCateId;
	}

	
}
