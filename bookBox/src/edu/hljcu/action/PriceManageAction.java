package edu.hljcu.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.BookInfo;
import edu.hljcu.service.IBookBoxSrv;

public class PriceManageAction extends ActionSupport {

	private IBookBoxSrv bookBoxSrv = null;
	private List<BookInfo> allBookInfoes = null;
	private String beginTime = null;
	private String endTime = null;
	private String sum = "0";
	public String execute() throws Exception{	
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object adminIdTemp = session.get("adminName");
		if(null == adminIdTemp)
		{
			return "failure";
		}else
		{
			float t = 0;
			allBookInfoes = bookBoxSrv.getBookInfosByPartTime(beginTime, endTime);
			SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			for(BookInfo bi : allBookInfoes){
				t += bi.getCurrBoxprice();
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
			sum = t+"";
			return "success";
		}
	}

	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}

	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<BookInfo> getAllBookInfoes() {
		return allBookInfoes;
	}

	public void setAllBookInfoes(List<BookInfo> allBookInfoes) {
		this.allBookInfoes = allBookInfoes;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}
	
}
