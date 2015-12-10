package edu.hljcu.displaytag;

import org.displaytag.decorator.TableDecorator;

import edu.hljcu.common.StaticUtil;
import edu.hljcu.hibernate.all.BookInfo;


public class BookWrapper extends TableDecorator{
	
	public String editLink;

	public String getEditLink() {
		BookInfo bookInfo= (BookInfo) getCurrentRowObject();
        String disabled = "";
        String hasPay = "缴费";
        if(StaticUtil.BOOKINFO_STATUS_HASPAY.equals(bookInfo.getStatus())){
        	disabled = "disabled";
        	hasPay = "已缴费";
        }
        	
        return "<a href='#'  ><input type='button' id='"+bookInfo.getBookInfoId()+"' value='"+hasPay +"'  "+disabled+" onclick='pay(\""+bookInfo.getBookInfoId()+"\")'></a> ";
	}

	public void setEditLink(String editLink) {
		this.editLink = editLink;
	}
	
	
}
