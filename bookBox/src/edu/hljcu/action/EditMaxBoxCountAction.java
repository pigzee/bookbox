package edu.hljcu.action;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.service.IBookBoxSrv;

public class EditMaxBoxCountAction extends ActionSupport {

	private IBookBoxSrv bookBoxSrv = null;
	private String maxBoxCount = null;
	private String status = null;
	
	public boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() )
		{
			return false;
		}
			return true;
	} 
	
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
			if(isNumeric(maxBoxCount)){
				bookBoxSrv.editMaxBoxCount(maxBoxCount);
				return SUCCESS;
			}else{
				return "numError";
			}
			
		}
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}
	public String getMaxBoxCount() {
		return maxBoxCount;
	}
	public void setMaxBoxCount(String maxBoxCount) {
		this.maxBoxCount = maxBoxCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
