package edu.hljcu.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.Box;
import edu.hljcu.hibernate.all.RowCate;
import edu.hljcu.service.IBookBoxSrv;


public class ShowRowCateByIdAction extends ActionSupport {
	IBookBoxSrv bookBoxSrv = null;
	private RowCate rowCate = null;
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
			rowCate = bookBoxSrv.showRowCateById(rowCateId);
			return SUCCESS;
		}
		
		
	}

	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}

	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}

	public RowCate getRowCate() {
		return rowCate;
	}

	public void setRowCate(RowCate rowCate) {
		this.rowCate = rowCate;
	}

	public String getRowCateId() {
		return rowCateId;
	}

	public void setRowCateId(String rowCateId) {
		this.rowCateId = rowCateId;
	}

	
	
}
