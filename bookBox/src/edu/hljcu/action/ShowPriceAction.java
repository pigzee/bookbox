package edu.hljcu.action;

import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.AdminOper;
import edu.hljcu.service.IBookBoxSrv;

public class ShowPriceAction extends ActionSupport {

	IBookBoxSrv bookBoxSrv = null;
	private AdminOper adminOper = null;
	
	public String execute()throws Exception{
		adminOper = bookBoxSrv.getAdmin();
		return SUCCESS;
	}

	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}

	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}

	public AdminOper getAdminOper() {
		return adminOper;
	}

	public void setAdminOper(AdminOper adminOper) {
		this.adminOper = adminOper;
	}

	
	
}
