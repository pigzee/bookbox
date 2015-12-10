package edu.hljcu.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.AdminOper;
import edu.hljcu.service.IBookBoxSrv;

public class ShowAdminByIdAction extends ActionSupport {

	private IBookBoxSrv bookBoxSrv = null;
	private String adminOperId = null;
	private String edit = null;
	private AdminOper adminOper = null;
	public String execute()throws Exception{
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			adminOper = bookBoxSrv.getAdminById(adminOperId);
			if("editName".equals(edit)){
				return "editName";
			}else if("editPsw".equals(edit)){
				return "editPsw";
			}else if("editPrice".equals(edit)){
				return "editPrice";
			}else if("editMaxBoxCount".equals(edit)){
				return "editMaxBoxCount";
			}
		}
		return "failure";
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}

	public String getAdminOperId() {
		return adminOperId;
	}
	public void setAdminOperId(String adminOperId) {
		this.adminOperId = adminOperId;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public AdminOper getAdminOper() {
		return adminOper;
	}
	public void setAdminOper(AdminOper adminOper) {
		this.adminOper = adminOper;
	}
	
	
}
