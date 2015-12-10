package edu.hljcu.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.hibernate.all.BookInfo;
import edu.hljcu.service.IBookBoxSrv;

public class ShowBookInfoAction extends ActionSupport {

	IBookBoxSrv bookBoxSrv = null;
	private BookInfo bookInfo = null;
	public String execute()throws Exception{
			
			this.clearErrorsAndMessages();
			Map session = ActionContext.getContext().getSession();
			Object studNoTemp = session.get("studNo");
			if(null == studNoTemp)
			{
				return "failure";
			}else
			{
				String studNo = (String)studNoTemp;
				List<BookInfo> bookInfos = bookBoxSrv.findInfoByStudNo(studNo);
				if(null != bookInfos && bookInfos.size() > 0){
					bookInfo = bookInfos.get(0);
				}
				return SUCCESS;
			}
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}
	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	
	
}
