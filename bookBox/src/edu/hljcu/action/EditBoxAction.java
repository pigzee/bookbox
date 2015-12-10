package edu.hljcu.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.Box;
import edu.hljcu.service.IBookBoxSrv;

public class EditBoxAction extends ActionSupport {
	IBookBoxSrv bookBoxSrv = null;
	private String boxName = null;
	private String boxId = null;
	private String rowCateId = null;
	private Box box = null;
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
			box = bookBoxSrv.showBoxById(boxId);
			String result = bookBoxSrv.editBox(rowCateId, boxId, boxName.trim());
			
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
	public String getBoxName() {
		return boxName;
	}
	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}
	public String getBoxId() {
		return boxId;
	}
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}
	public String getRowCateId() {
		return rowCateId;
	}
	public void setRowCateId(String rowCateId) {
		this.rowCateId = rowCateId;
	}
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}

}
