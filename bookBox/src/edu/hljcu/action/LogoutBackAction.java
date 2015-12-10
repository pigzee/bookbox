package edu.hljcu.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.service.IBookBoxSrv;

public class LogoutBackAction extends ActionSupport {

	private IBookBoxSrv bookBoxSrv = null;
	public String execute()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();	
        //清空session		
		Enumeration e = session.getAttributeNames();		
		while( e!=null&&e.hasMoreElements())   
		{   
		    String  sessionName= (String)e.nextElement();   
		    session.removeAttribute(sessionName); 
		}	
		return "success";	
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}
	
}
