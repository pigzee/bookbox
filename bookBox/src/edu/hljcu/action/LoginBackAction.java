package edu.hljcu.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.AdminOper;
import edu.hljcu.service.IBookBoxSrv;

public class LoginBackAction extends ActionSupport {

	IBookBoxSrv bookBoxSrv = null;
	private String adminName=null;
	private String adminPsw=null;
	public String execute()throws Exception{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();		
		AdminOper admin=bookBoxSrv.validateAdmin(adminName, adminPsw);
		if (admin!=null)
		{
			session.setAttribute("adminName", adminName);
			session.setAttribute("adminPsw", adminPsw);
			return "success";		
		} 
		this.addActionMessage("用户名或密码错误！");
		return "failure";	
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPsw() {
		return adminPsw;
	}
	public void setAdminPsw(String adminPsw) {
		this.adminPsw = adminPsw;
	}
	
	
}
