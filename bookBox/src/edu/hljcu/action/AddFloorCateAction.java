package edu.hljcu.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.FloorCate;
import edu.hljcu.service.IBookBoxSrv;

public class AddFloorCateAction extends ActionSupport {

	IBookBoxSrv bookBoxSrv = null;
	private String cateName = null;
	public String execute()throws Exception{
		
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			FloorCate floorCate = new FloorCate();
			floorCate.setCateName(cateName.trim());
			String result = bookBoxSrv.addFloorCate(floorCate);
			return SUCCESS;	
		}
		
		

	}

	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}

	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	
}
