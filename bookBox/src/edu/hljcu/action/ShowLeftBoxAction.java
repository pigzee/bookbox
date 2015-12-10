package edu.hljcu.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import edu.hljcu.common.StaticUtil;
import edu.hljcu.hibernate.all.BookInfo;
import edu.hljcu.hibernate.all.Box;
import edu.hljcu.hibernate.all.FloorCate;
import edu.hljcu.hibernate.all.RowCate;
import edu.hljcu.service.IBookBoxSrv;

public class ShowLeftBoxAction extends ActionSupport {

	IBookBoxSrv bookBoxSrv = null;
	private List<FloorCate> allFloorCates = null;
	private String floorCateId = null;
	private List<BookInfo> bookInfos = null;
	public String execute()throws Exception{
		allFloorCates = bookBoxSrv.getAllFloorCate();
		List<RowCate> rowCatesList = null;
			
		if(null != floorCateId){
			rowCatesList = bookBoxSrv.findRowByFloorId(floorCateId);
		}else{
			if(null != allFloorCates && allFloorCates.size() >0){
				rowCatesList = bookBoxSrv.findRowByFloorId(allFloorCates.get(0).getFloorCateId()+"");
			}
			
		}
		if(null != rowCatesList){
			 String boxPlace = "";
			 String temp = "";
			 bookInfos = new ArrayList<BookInfo>();
			for(RowCate  rc : rowCatesList){
				boxPlace = rc.getCateName();
				temp = rc.getCateName();
				List<Box> boxList = bookBoxSrv.getAllBoxByRowId(rc.getRowCateId()+"");
				if(null != boxList){
					for(Box b : boxList){
						if(StaticUtil.BOX_BOOK_STATUS_NOBOOK.equals(b.getStatus())){
							boxPlace = "排号："+temp + " 箱子号：" + b.getBoxName();
							 BookInfo bi = new BookInfo();
							 bi.setBoxPlace(boxPlace);
							 bookInfos.add(bi);
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	public IBookBoxSrv getBookBoxSrv() {
		return bookBoxSrv;
	}
	public void setBookBoxSrv(IBookBoxSrv bookBoxSrv) {
		this.bookBoxSrv = bookBoxSrv;
	}
	public List<FloorCate> getAllFloorCates() {
		return allFloorCates;
	}
	public void setAllFloorCates(List<FloorCate> allFloorCates) {
		this.allFloorCates = allFloorCates;
	}
	public String getFloorCateId() {
		return floorCateId;
	}
	public void setFloorCateId(String floorCateId) {
		this.floorCateId = floorCateId;
	}
	public List<BookInfo> getBookInfos() {
		return bookInfos;
	}
	public void setBookInfos(List<BookInfo> bookInfos) {
		this.bookInfos = bookInfos;
	}

	
}
