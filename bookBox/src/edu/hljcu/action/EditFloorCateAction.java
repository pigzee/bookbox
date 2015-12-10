package edu.hljcu.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.FloorCate;
import edu.hljcu.service.IBookBoxSrv;

public class EditFloorCateAction extends ActionSupport {

	IBookBoxSrv bookBoxSrv = null;
	private String floorCateId = null;
	private String cateName = null;
	private FloorCate floorCate = null;
	public String execute()throws Exception{
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			ActionContext ctx=ActionContext.getContext();
			floorCate = bookBoxSrv.showFloorCateById(floorCateId);
			String result = bookBoxSrv.editFloorCate(floorCateId, cateName.trim());
			
			if(!"true".equals(result)){
				this.addActionMessage(result);
				return "editAgain";
			}else{
				ctx.put("message", "修改成功");
				return SUCCESS;
			}

		}
		

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
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public FloorCate getFloorCate() {
		return floorCate;
	}
	public void setFloorCate(FloorCate floorCate) {
		this.floorCate = floorCate;
	}
	
	
}
