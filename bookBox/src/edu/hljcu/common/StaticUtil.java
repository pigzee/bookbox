package edu.hljcu.common;

public class StaticUtil {
	//箱子表 未预约
	public static final String BOX_BOOK_STATUS_NOBOOK = "0"; 
	//箱子表 已预约但未付费
	public static final String BOX_BOOK_STATUS_HASBOOK = "1"; 
	//箱子表 已付费
	public static final String BOX_BOOK_STATUS_HASPAY = "2"; 
	
	
	//预约信息表 未预约
	public static final String BOOKINFO_BOOK_STATUS_NOBOOK = "0"; 
	//预约信息表 已预约但未付费
	public static final String BOOKINFO_STATUS_HASBOOK = "1"; 
	//预约信息表 已付费
	public static final String BOOKINFO_STATUS_HASPAY = "2"; 
	//预约信息表 已续约
	public static final String BOOKINFO_STATUS_HASCON = "3"; 
}
