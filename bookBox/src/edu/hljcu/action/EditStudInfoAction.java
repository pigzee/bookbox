package edu.hljcu.action;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.common.StaticUtil;
import edu.hljcu.hibernate.all.AdminOper;
import edu.hljcu.hibernate.all.BookInfo;
import edu.hljcu.service.IBookBoxSrv;

public class EditStudInfoAction extends ActionSupport {

	IBookBoxSrv bookBoxSrv = null;
	private String college = null;
	private String studClass = null;
	private String phone = null;
	private AdminOper adminOper = null;
	
	public boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() )
		{
			return false;
		}
			return true;
	}
	
	public String execute() throws Exception{
		this.clearErrorsAndMessages();
		Map session = ActionContext.getContext().getSession();
		Object studNoTemp = session.get("studNo");
		Object studNameTemp = session.get("studName");
		if(null == studNoTemp)
		{
			return "failure";
		}else
		{
			String studNo = (String)studNoTemp;
			String studName = (String)studNameTemp;
			adminOper = bookBoxSrv.getAdmin();
			String maxBoxCount = bookBoxSrv.getAdmin().getMaxBoxCount();
			if(null == maxBoxCount || "".equals(maxBoxCount) || !isNumeric(maxBoxCount)){
				adminOper.setMaxBoxCount("1");
			}
			List<BookInfo> bookInfoes = bookBoxSrv.findInfoByStudNo(studNo);
			if(null == bookInfoes || bookInfoes.size() == 0){
				bookBoxSrv.addBookInfo(studName, studNo, "", StaticUtil.BOX_BOOK_STATUS_NOBOOK,college,studClass,phone);
			}else{
				bookBoxSrv.editBookInfo(studNo, college, studClass, phone);
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
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getStudClass() {
		return studClass;
	}
	public void setStudClass(String studClass) {
		this.studClass = studClass;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public AdminOper getAdminOper() {
		return adminOper;
	}
	public void setAdminOper(AdminOper adminOper) {
		this.adminOper = adminOper;
	}
	
}
