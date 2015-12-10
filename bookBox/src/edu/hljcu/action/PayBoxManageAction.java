package edu.hljcu.action;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.BookInfo;
import edu.hljcu.service.IBookBoxSrv;

public class PayBoxManageAction extends ActionSupport{
	
	private IBookBoxSrv bookBoxSrv = null;
	private List<BookInfo> allBookInfoes = null;
	private String studNo = null;
	
	public String execute() throws Exception{	
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			allBookInfoes = bookBoxSrv.getAllBookInfo(studNo);
			SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			for(BookInfo bi : allBookInfoes){
				if(null != bi.getBookTime() && !"".equals(bi.getBookTime()) && !"NULL".equals(bi.getBookTime()) && !"null".equals(bi.getBookTime())){
					date =simpleDateFormat.parse(bi.getBookTime().toString());
					java.sql.Date bt =new java.sql.Date(date.getTime());
					bi.setBookTime(bt);
				}
				if(null != bi.getPayTime()&& !"".equals(bi.getPayTime()) && !"NULL".equals(bi.getPayTime()) && !"null".equals(bi.getPayTime())){
					date =simpleDateFormat.parse(bi.getPayTime().toString());
					java.sql.Date pt =new java.sql.Date(date.getTime());
					bi.setPayTime(pt);
				}
			}
			return "success";
		}
		

	
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}

	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}

	public List getAllBookInfoes() {
		return allBookInfoes;
	}
	public void setAllBookInfoes(List allBookInfoes) {
		this.allBookInfoes = allBookInfoes;
	}
	public String getStudNo() {
		return studNo;
	}

	public void setStudNo(String studNo) {
		this.studNo = studNo;
	}
	
	
}
