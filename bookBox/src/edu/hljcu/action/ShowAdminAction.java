package edu.hljcu.action;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.AdminOper;
import edu.hljcu.service.IBookBoxSrv;

public class ShowAdminAction extends ActionSupport {

	private IBookBoxSrv bookBoxSrv = null;
	private AdminOper adminOper = null;
	private String status = null;
	public String execute()throws Exception{
		
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			adminOper = bookBoxSrv.getAdmin();
			if("admin".equals(status)){
				return "admin";
			}else if("maxBoxCount".equals(status) || "editMaxBoxCount".equals(status)){
				return "maxBoxCount";
			}else if("conTime".equals(status)) {
				String bcDate = adminOper.getConBeginTime().toString();
				String ecDate = adminOper.getConEndTime().toString();
				String bbDate = adminOper.getBookBeginTime().toString();
				String ebDate = adminOper.getBookEndTime().toString();
				SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
				Date newbcDate =simpleDateFormat.parse(bcDate);
				Date newecDate =simpleDateFormat.parse(ecDate);
				Date newbbDate =simpleDateFormat.parse(bbDate);
				Date newebDate =simpleDateFormat.parse(ebDate);
                java.sql.Date bc =new java.sql.Date(newbcDate.getTime());
                java.sql.Date ec =new java.sql.Date(newecDate.getTime());
                java.sql.Date bb =new java.sql.Date(newbbDate.getTime());
                java.sql.Date eb =new java.sql.Date(newebDate.getTime());
				adminOper.setConBeginTime(bc);
				adminOper.setConEndTime(ec);
				adminOper.setBookBeginTime(bb);
				adminOper.setBookEndTime(eb);
				return "conTime";
			}else if("bookTime".equals(status)){
				String bcDate = adminOper.getConBeginTime().toString();
				String ecDate = adminOper.getConEndTime().toString();
				String bbDate = adminOper.getBookBeginTime().toString();
				String ebDate = adminOper.getBookEndTime().toString();
				SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
				Date newbcDate =simpleDateFormat.parse(bcDate);
				Date newecDate =simpleDateFormat.parse(ecDate);
				Date newbbDate =simpleDateFormat.parse(bbDate);
				Date newebDate =simpleDateFormat.parse(ebDate);
                java.sql.Date bc =new java.sql.Date(newbcDate.getTime());
                java.sql.Date ec =new java.sql.Date(newecDate.getTime());
                java.sql.Date bb =new java.sql.Date(newbbDate.getTime());
                java.sql.Date eb =new java.sql.Date(newebDate.getTime());
				adminOper.setConBeginTime(bc);
				adminOper.setConEndTime(ec);
				adminOper.setBookBeginTime(bb);
				adminOper.setBookEndTime(eb);
				return "bookTime";
			}else {
				return "failure";
			}
			
		}
		

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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
