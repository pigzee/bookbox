package edu.hljcu.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.RowCate;
import edu.hljcu.service.IBookBoxSrv;

public class EditRowCateAction extends ActionSupport {
	IBookBoxSrv bookBoxSrv = null;
	private String rowCateId = null;
	private String cateName = null;
	private String floorCateId = null;
	private RowCate rowCate = null;
		
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
			rowCate = bookBoxSrv.showRowCateById(rowCateId);
			String result = bookBoxSrv.editRowCate(floorCateId,rowCateId, cateName.trim());
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
	public String getRowCateId() {
		return rowCateId;
	}
	public void setRowCateId(String rowCateId) {
		this.rowCateId = rowCateId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getFloorCateId() {
		return floorCateId;
	}
	public void setFloorCateId(String floorCateId) {
		this.floorCateId = floorCateId;
	}
	public RowCate getRowCate() {
		return rowCate;
	}
	public void setRowCate(RowCate rowCate) {
		this.rowCate = rowCate;
	}

	
}
