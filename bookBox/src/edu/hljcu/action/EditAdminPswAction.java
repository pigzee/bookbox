package edu.hljcu.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.service.IBookBoxSrv;

public class EditAdminPswAction extends ActionSupport {
	IBookBoxSrv bookBoxSrv = null;
	private String newPassword=null;
	private String oldPassword=null;
	public String execute()throws Exception
	{
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			if(bookBoxSrv.editAdminPsw(oldPassword,newPassword)){
				return SUCCESS;	
			}else{
				return "fail";
			}
			
		}
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	
	
}
