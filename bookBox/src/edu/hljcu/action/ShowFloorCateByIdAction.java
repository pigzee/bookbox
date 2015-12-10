package edu.hljcu.action;

import java.util.Map;

import org.apache.log4j.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.FloorCate;
import edu.hljcu.service.IBookBoxSrv;

public class ShowFloorCateByIdAction extends ActionSupport {

	IBookBoxSrv bookBoxSrv = null;
	private FloorCate floorCate = null;
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
			floorCate = bookBoxSrv.showFloorCateById(floorCateId);
			return SUCCESS;
		}
		
		
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}
	public FloorCate getFloorCate() {
		return floorCate;
	}
	public void setFloorCate(FloorCate floorCate) {
		this.floorCate = floorCate;
	}
	public String getFloorCateId() {
		return floorCateId;
	}
	public void setFloorCateId(String floorCateId) {
		this.floorCateId = floorCateId;
	}
	
	
}
