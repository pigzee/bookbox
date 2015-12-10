package edu.hljcu.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.common.StaticUtil;
import edu.hljcu.hibernate.all.AdminOper;
import edu.hljcu.hibernate.all.BookInfo;
import edu.hljcu.hibernate.all.Box;
import edu.hljcu.hibernate.all.Students;
import edu.hljcu.service.IBookBoxSrv;

public class LoginAction extends ActionSupport {

	private IBookBoxSrv bookBoxSrv = null;
	private String studNo=null;
	private String studName=null;
	private List<BookInfo> bookInfos = null;
	private AdminOper adminOper = null;
	private List<BookInfo> biList = null;
	private List<BookInfo> biHasConList = null;
	private List<BookInfo> biHasPayList = null;
	private List<BookInfo> biNoConBookList = null;
	private BookInfo bookInfo = null;

	
	public String execute() throws Exception
	{
		this.clearErrorsAndMessages();
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();		
		Students stud=bookBoxSrv.validate(studNo, studName);
		if (stud!=null)
		{
			session.setAttribute("studNo", studNo);
			session.setAttribute("studName", studName);
			//获取预约时间和续约时间
			adminOper = bookBoxSrv.getAdmin();		
			if (bookBoxSrv.judgeConTime()) {
				//获得该用户所有的bookInfo记录
				bookInfos = bookBoxSrv.findInfoByStudNo(studNo);
				boolean flag = false;
				//存在可以续约的记录
				if(null != bookInfos && bookInfos.size() != 0){
					biList = new ArrayList<BookInfo>();
					for(BookInfo bi : bookInfos){
						if(null != bi.getBookTime() && !"NULL".equals(bi.getBookTime()) 
								&& !"null".equals(bi.getBookTime()) && !"".equals(bi.getBookTime())
								&& bi.getOwnCount() > 0) {
							biList.add(bi);
							flag = true;
						}
					}
				}
				//flag=false:没有交过费
				if(!flag){
					return "conTime";
				}
				//flag=true:状态为2或者3
				//状态为2者判断箱子是否已经缴费
				else{
					if(null != biList && biList.size()>0){
						biHasConList  = new ArrayList<BookInfo>();
						biHasPayList  = new ArrayList<BookInfo>();
						biNoConBookList  = new ArrayList<BookInfo>();
						for(BookInfo bi : biList){
							//status = 3 已续约，但是没交钱
							if(null != bi.getBookTime() && !"NULL".equals(bi.getBookTime()) 
									&& !"null".equals(bi.getBookTime()) && !"".equals(bi.getBookTime())){
								if(StaticUtil.BOOKINFO_STATUS_HASCON.equals(bi.getStatus())){
									biHasConList.add(bi);
								}else if (StaticUtil.BOOKINFO_STATUS_HASPAY.equals(bi.getStatus())) {
									//status = 2 两种情况：缴费或者还没续约
									if(null != bi.getBox()){
										Box b = bookBoxSrv.findBoxById(bi.getBox().getBoxId()+"");
										if(null != b && StaticUtil.BOX_BOOK_STATUS_HASPAY.equals(b.getStatus())){
											biHasPayList.add(bi);
										}else if (null != b && StaticUtil.BOX_BOOK_STATUS_NOBOOK.equals(b.getStatus())){
											biNoConBookList.add(bi);
										}
									}
								}
							}
						}
					}
					String maxBoxCount = adminOper.getMaxBoxCount();
					if(null == maxBoxCount || "".equals(maxBoxCount) || !isNumeric(maxBoxCount)){
						adminOper.setMaxBoxCount("1");
					}
					return "conBook";
				}
			}else if(bookBoxSrv.judgeBookTime()){
				bookInfo = null;
				List<BookInfo> biList = bookBoxSrv.findInfoByStudNo(studNo);
				if(null != biList && biList.size() > 0){
					bookInfo = (BookInfo)biList.get(0);
				}
				return "success";
			}else{
				return "over";
			}
		}
		this.addActionMessage("用户名或者密码错误！");
		return "failure";		
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}
	public String getStudNo() {
		return studNo;
	}
	public void setStudNo(String studNo) {
		this.studNo = studNo;
	}
	public String getStudName() {
		return studName;
	}
	public void setStudName(String studName) {
		this.studName = studName;
	}
	public List<BookInfo> getBookInfos() {
		return bookInfos;
	}
	public void setBookInfos(List<BookInfo> bookInfos) {
		this.bookInfos = bookInfos;
	}
	public AdminOper getAdminOper() {
		return adminOper;
	}
	public void setAdminOper(AdminOper adminOper) {
		this.adminOper = adminOper;
	}
	public List<BookInfo> getBiList() {
		return biList;
	}
	public void setBiList(List<BookInfo> biList) {
		this.biList = biList;
	}
	public List<BookInfo> getBiHasConList() {
		return biHasConList;
	}
	public void setBiHasConList(List<BookInfo> biHasConList) {
		this.biHasConList = biHasConList;
	}
	public List<BookInfo> getBiHasPayList() {
		return biHasPayList;
	}
	public void setBiHasPayList(List<BookInfo> biHasPayList) {
		this.biHasPayList = biHasPayList;
	}
	public List<BookInfo> getBiNoConBookList() {
		return biNoConBookList;
	}
	public void setBiNoConBookList(List<BookInfo> biNoConBookList) {
		this.biNoConBookList = biNoConBookList;
	}
	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
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
}
