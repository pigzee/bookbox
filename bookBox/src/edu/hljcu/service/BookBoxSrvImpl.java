package edu.hljcu.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.hljcu.common.MD5;
import edu.hljcu.common.StaticUtil;
import edu.hljcu.hibernate.all.AdminOper;
import edu.hljcu.hibernate.all.AdminOperDAO;
import edu.hljcu.hibernate.all.BookInfo;
import edu.hljcu.hibernate.all.BookInfoDAO;
import edu.hljcu.hibernate.all.Box;
import edu.hljcu.hibernate.all.BoxDAO;
import edu.hljcu.hibernate.all.FloorCate;
import edu.hljcu.hibernate.all.FloorCateDAO;
import edu.hljcu.hibernate.all.RowCate;
import edu.hljcu.hibernate.all.RowCateDAO;
import edu.hljcu.hibernate.all.Students;
import edu.hljcu.hibernate.all.StudentsDAO;


public class BookBoxSrvImpl implements IBookBoxSrv {
	
	private StudentsDAO studentsDAO;
	private AdminOperDAO adminOperDAO;
	private BookInfoDAO bookInfoDAO;
	private BoxDAO boxDAO;
	private FloorCateDAO floorCateDAO;
	private RowCateDAO rowCateDAO;
	
	public StudentsDAO getStudentsDAO() {
		return studentsDAO;
	}
	public void setStudentsDAO(StudentsDAO studentsDAO) {
		this.studentsDAO = studentsDAO;
	}
	public AdminOperDAO getAdminOperDAO() {
		return adminOperDAO;
	}
	public void setAdminOperDAO(AdminOperDAO adminOperDAO) {
		this.adminOperDAO = adminOperDAO;
	}
	public BookInfoDAO getBookInfoDAO() {
		return bookInfoDAO;
	}
	public void setBookInfoDAO(BookInfoDAO bookInfoDAO) {
		this.bookInfoDAO = bookInfoDAO;
	}
	public BoxDAO getBoxDAO() {
		return boxDAO;
	}
	public void setBoxDAO(BoxDAO boxDAO) {
		this.boxDAO = boxDAO;
	}
	public FloorCateDAO getFloorCateDAO() {
		return floorCateDAO;
	}
	public void setFloorCateDAO(FloorCateDAO floorCateDAO) {
		this.floorCateDAO = floorCateDAO;
	}
	public RowCateDAO getRowCateDAO() {
		return rowCateDAO;
	}
	public void setRowCateDAO(RowCateDAO rowCateDAO) {
		this.rowCateDAO = rowCateDAO;
	}
	
	public Students validate(String studNo,String studName) throws Exception{
		List<Students> results =studentsDAO.findByProperty("studNo",studNo);
		for(Students stud:results)
		{
		   if(studName.equals(stud.getStudName()))
		   {			
			   return stud;
		   }
	    }
		return null;
	}
	
	public AdminOper validateAdmin(String adminName,String adminPsw)  throws Exception{
		List<AdminOper> results =adminOperDAO.findByProperty("adminName",adminName);
		for(AdminOper admin:results)
		{
		   if(MD5.encrypt(adminPsw).equals(admin.getAdminPsw()))
		   {			
			   return admin;
		   }
	    }		return null;
	}
	
	public void editAdminName(String name) throws Exception{
		List<AdminOper> admins = adminOperDAO.findByExample(new AdminOper());
		for(AdminOper admin : admins){
			admin.setAdminName(name);
			adminOperDAO.attachDirty(admin);
		}
	}
	
	public boolean editAdminPsw(String oldPsw,String newPsw) throws Exception{
		List<AdminOper> admins = adminOperDAO.findByExample(new AdminOper());
		for(AdminOper admin : admins){
			if(MD5.encrypt(oldPsw).equals(admin.getAdminPsw()))
			{
				admin.setAdminPsw(MD5.encrypt(newPsw));
				adminOperDAO.attachDirty(admin);
				return true;	
			}
		}
		return false;
	}
	
	public void editBoxPrice(String boxPrice) throws Exception{
		if(null != boxPrice && !"".equals(boxPrice)){
			List<AdminOper> admins = adminOperDAO.findByExample(new AdminOper());
			for(AdminOper admin : admins){
				admin.setBoxPrice(Float.valueOf(boxPrice));
				adminOperDAO.attachDirty(admin);
			}
		}
	}
	
	public void editMaxBoxCount(String maxBoxCount) throws Exception{
		if(null != maxBoxCount && !"".equals(maxBoxCount)){
			List<AdminOper> admins = adminOperDAO.findByExample(new AdminOper());
			for(AdminOper admin : admins){
				admin.setMaxBoxCount(maxBoxCount);
				adminOperDAO.attachDirty(admin);
			}
		}
	}
	
	public List getAllFloorCate() throws Exception{
		return floorCateDAO.findByExample(new FloorCate());
	}
	
	public boolean hasBookOrPayBox(String studNo) throws Exception {
		if(null == studNo || "".equals(studNo)){
			return true;
		}
		List<BookInfo> bookInfos = bookInfoDAO.findByStudNo(studNo);
		for(BookInfo bookInfo : bookInfos){
			if(!StaticUtil.BOOKINFO_BOOK_STATUS_NOBOOK.equals(bookInfo.getStatus())
					&& !StaticUtil.BOOKINFO_STATUS_HASCON.equals(bookInfo.getStatus())
					&& !"".equals(bookInfo.getStatus())){
				return true;
			}
		}
		return false;
	}

	public List<RowCate> findRowByFloorId(String FloorId) throws Exception {
		return rowCateDAO.findByProperty("floorCate.floorCateId", Integer.valueOf(FloorId));
	}
	
	public List<Box> findBoxByRowId(String rowId) throws Exception{
		return boxDAO.findByProperty("rowCate.rowCateId", Integer.valueOf(rowId));
		
	}
	
	public List<BookInfo> findInfoByStudNo(String studNo) throws Exception{
		if(null != studNo && !"".equals(studNo)){
			return bookInfoDAO.findByStudNo(studNo);
		}else
			return null;
	}
	
	public BookInfo findInfoByStudNoAndBoxId(String studNo,String boxId) throws Exception{
		if(null != studNo && !"".equals(studNo) && null != boxId && !"".equals(boxId)){
			List<BookInfo> boList = findInfoByStudNo(studNo);
			if(null != boList){
				for(BookInfo bi : boList){
					if(null != bi.getBox() && boxId.equals(bi.getBox().getBoxId()+"")){
						return  bi;
					}
				}
			}
		}
		return null;
	}
	
	public Box getBoxById(String boxId) throws Exception{
		if(null != boxId && !"".equals(boxId)){
			return boxDAO.findById(Integer.valueOf(boxId));
		}else
			return null;
		
	}
	
	public BookInfo getBookedBoxByNo(String studNo) throws Exception{
		if(null != studNo && !"".equals(studNo)){
			List<BookInfo> infos = bookInfoDAO.findByProperty("studNo", studNo);
			if(infos.size() > 0){
				return infos.get(0);
			}else
				return null;
		}else
			return null;
	}
	
	public void editBoxStatus(String boxId,String status) throws Exception{
		if(null != boxId && !"".equals(boxId) && null != status && !"".equals(status)){
			Box box = boxDAO.findById(Integer.valueOf(boxId));
			box.setStatus(status);
			boxDAO.attachDirty(box);
		}
	}
	
	public void addBookInfo(String studName,String studNo,String boxId,String status,String college,String studClass,String phone) throws Exception{
		if(null == boxId || "".equals(boxId)){
			if(null ==  findInfoByStudNo(studNo) || findInfoByStudNo(studNo).size() == 0){
				BookInfo bi = new BookInfo();
				bi.setCollege(college);
				bi.setStudClass(studClass);
				bi.setStatus(status);
				bi.setStudName(studName);
				bi.setStudNo(studNo);
				bi.setPhone(phone);
				bi.setOwnCount(0);
				bi.setCurrBoxprice(0f);
				bi.setBoxPlace("");
				bookInfoDAO.save(bi);
			}
		}else{
			Box box =  boxDAO.findById(Integer.valueOf(boxId));
			RowCate rc = rowCateDAO.findById(box.getRowCate().getRowCateId());
			FloorCate fc = floorCateDAO.findById(rc.getFloorCate().getFloorCateId());
			String boxName = box.getBoxName();
			String rowCateName = rc.getCateName();
			String floorCateName = fc.getCateName();
			BookInfo bi = new BookInfo();
			bi.setBox(boxDAO.findById(Integer.valueOf(boxId)));
			bi.setStatus(status);
			bi.setStudName(studName);
			bi.setStudNo(studNo);
			bi.setBookTime(new Date());
			bi.setOwnCount(0);
			bi.setCurrBoxprice(0f);
			bi.setBoxPlace(floorCateName + " " + rowCateName + " 第" + boxName + "号箱子");
			bi.setCollege(college);
			bi.setStudClass(studClass);
			bi.setPhone(phone);
			bookInfoDAO.save(bi);
		}
		
		
	}
	
	public void editBookInfoStatus(String studNo,String status,String boxId) throws Exception{
		
		if(null != studNo && !"".equals(studNo) && null != status && !"".equals(status) 
				&& null != boxId && !"".equals(boxId)){
			List<BookInfo> bookInfos = findInfoByStudNo(studNo);
			if(null != bookInfos){
				for(BookInfo bi : bookInfos){
					if(null != bi.getBox() && boxId.equals(bi.getBox().getBoxId())){
						bi.setStatus(status);
						bookInfoDAO.attachDirty(bi);
						break;
					}
				}
			}
		}
	}
	
	public AdminOper getAdmin()throws Exception{
		List<AdminOper> admins = adminOperDAO.findByExample(new AdminOper());
		if(null != admins && admins.size() >0){
			return admins.get(0);
		}
		return null;
	}
	
	public AdminOper getAdminById(String adminId) throws Exception{
		return adminOperDAO.findById(Integer.valueOf(adminId));
	}
	
	public int[] getBoxCountArrByFloor(String floorCateId) throws Exception{
		//0:总数；1,hasBook；2，haspay；3，left
		int[] boxCountArr = new int[4];
		boxCountArr[0] = 0;
		boxCountArr[1] = 0;
		boxCountArr[2] = 0;
		boxCountArr[3] = 0;
		List<RowCate> rows = rowCateDAO.findByProperty("floorCate.floorCateId", Integer.valueOf(floorCateId));
		if(null != rows){
			for(RowCate rc : rows){
				List<Box> boxes = boxDAO.findByProperty("rowCate.rowCateId", rc.getRowCateId());
				if(null != boxes){
					boxCountArr[0] += boxes.size();
					for(Box bi : boxes){
						if(StaticUtil.BOX_BOOK_STATUS_HASBOOK.equals(bi.getStatus())){
							boxCountArr[1]++;
						}else if(StaticUtil.BOX_BOOK_STATUS_HASPAY.equals(bi.getStatus())){
							boxCountArr[2]++;
						}else if(StaticUtil.BOX_BOOK_STATUS_NOBOOK.equals(bi.getStatus())){
							boxCountArr[3]++;
						}
					}
				}
			}
		}
		return boxCountArr;
	}
	
	
	 public int[] getBoxCountArr() throws Exception{
		//0:总数；1,hasBook；2，haspay；3，left
		int[] boxCountArr = new int[4];
		boxCountArr[0] = 0;
		boxCountArr[1] = 0;
		boxCountArr[2] = 0;
		boxCountArr[3] = 0;
		List<Box> boxes = boxDAO.findByExample(new Box());
		if(null != boxes){
			boxCountArr[0] = boxes.size();
			for(Box bi : boxes){
				if(StaticUtil.BOX_BOOK_STATUS_HASBOOK.equals(bi.getStatus())){
					boxCountArr[1]++;
				}else if(StaticUtil.BOX_BOOK_STATUS_HASPAY.equals(bi.getStatus())){
					boxCountArr[2]++;
				}else if(StaticUtil.BOX_BOOK_STATUS_NOBOOK.equals(bi.getStatus())){
					boxCountArr[3]++;
				}
			}
		}
		return boxCountArr;
	}
	
	public int getBoxCountByFloor(String floorCateId) throws Exception{
		int sum = 0;
		List<RowCate> rows = rowCateDAO.findByProperty("floorCate.floorCateId", Integer.valueOf(floorCateId));
		if(null != rows){
			for(RowCate rc : rows){
				List<Box> boxes = boxDAO.findByProperty("rowCate.rowCateId", rc.getRowCateId());
				if(null != boxes){
					sum += boxes.size();
				}
			}
		}
		return sum;
	}
	
	public int getHasBookedCountByFloor(String floorCateId) throws Exception{
		int sum = 0;
		List<RowCate> rows = rowCateDAO.findByProperty("floorCate.floorCateId", Integer.valueOf(floorCateId));
		if(null != rows){
			for(RowCate rc : rows){
				List<Box> boxes = boxDAO.findByProperty("rowCate.rowCateId", rc.getRowCateId());
				if(null != boxes ){
					for(Box box : boxes){
						if(StaticUtil.BOX_BOOK_STATUS_HASBOOK.equals(box.getStatus())){
							sum++; 
						}
					}
				}
			}
		}
		return sum;
	}
	
	public int getHasPayedCountByFloor(String floorCateId) throws Exception{
		int sum = 0;
		List<RowCate> rows = rowCateDAO.findByProperty("floorCate.floorCateId", Integer.valueOf(floorCateId));
		if(null != rows){
			for(RowCate rc : rows){
				List<Box> boxes = boxDAO.findByProperty("rowCate.rowCateId", rc.getRowCateId());
				if(null != boxes ){
					for(Box box : boxes){
						if(StaticUtil.BOX_BOOK_STATUS_HASPAY.equals(box.getStatus())){
							sum++; 
						}
					}
				}
			}
		}
		return sum;
	}
		
	public int getLastCountByFloor(String floorCateId) throws Exception{
		int sum = 0;
		List<RowCate> rows = rowCateDAO.findByProperty("floorCate.floorCateId", Integer.valueOf(floorCateId));
		if(null != rows){
			for(RowCate rc : rows){
				List<Box> boxes = boxDAO.findByProperty("rowCate.rowCateId", rc.getRowCateId());
				if(null != boxes ){
					for(Box box : boxes){
						if(StaticUtil.BOX_BOOK_STATUS_NOBOOK.equals(box.getStatus())){
							sum++; 
						}
					}
				}
			}
		}
		return sum;
	}

	public int getAllBoxCount() throws Exception{
		int sum = 0;
		/*
		List<FloorCate> floors = floorCateDAO.findByExample(new FloorCate());
		if(null != floors){
			for(FloorCate floor : floors){
				sum += getBoxCountByFloor(floor.getFloorCateId() + "");
			}
		}
		*/
		List<Box> boxes = boxDAO.findByExample(new Box());
		if(null != boxes)
			sum = boxes.size();
		return sum;
	}
		
	public int getAllHasBookedCount() throws Exception {
		int sum = 0;
		/*
		List<FloorCate> floors = floorCateDAO.findByExample(new FloorCate());
		if(null != floors){
			for(FloorCate floor : floors){
				sum += getHasBookedCountByFloor(floor.getFloorCateId() + "");
			}
		}*/
		List<Box> boxes = boxDAO.findByExample(new Box());
		if(null != boxes)
			for(Box box : boxes){
				if(StaticUtil.BOX_BOOK_STATUS_HASBOOK.equals(box.getStatus())){
					sum ++ ;
				}
			}
		return sum;
	}

	public int getAllHasPayedCount() throws Exception {
		int sum = 0;
		/*
		List<FloorCate> floors = floorCateDAO.findByExample(new FloorCate());
		if(null != floors){
			for(FloorCate floor : floors){
				sum += getHasPayedCountByFloor(floor.getFloorCateId() + "");
			}
		}*/
		List<Box> boxes = boxDAO.findByExample(new Box());
		if(null != boxes)
			for(Box box : boxes){
				if(StaticUtil.BOX_BOOK_STATUS_HASPAY.equals(box.getStatus())){
					sum ++ ;
				}
			}
		return sum;
	}

	public int getAllLastBoxCount() throws Exception {
		int sum = 0;
		/*
		List<FloorCate> floors = floorCateDAO.findByExample(new FloorCate());
		if(null != floors){
			for(FloorCate floor : floors){
				sum += getLastCountByFloor(floor.getFloorCateId() + "");
			}
		}*/
		List<Box> boxes = boxDAO.findByExample(new Box());
		if(null != boxes)
			for(Box box : boxes){
				if(StaticUtil.BOX_BOOK_STATUS_NOBOOK.equals(box.getStatus())){
					sum ++ ;
				}
			}
		return sum;
	}	
		
	public BookInfo getBookInfoByStudNo(String studNo) throws Exception{
		if(null != studNo && !"".equals(studNo)){
			List<BookInfo> books = bookInfoDAO.findByProperty("studNo", studNo);
			if(null != books){
				return books.get(0);
			}
		}
		return null;
	}
	
	public boolean editBookStatus(BookInfo bookInfo,String desStatus) throws Exception{
		if(null != bookInfo && null != desStatus && !"".equals(desStatus)){
			if(!StaticUtil.BOOKINFO_STATUS_HASCON.equals(desStatus)){
				AdminOper ao = getAdmin();
				bookInfo.setStatus(desStatus);
				if(null != ao && null != ao.getBoxPrice() 
						&& !"".equals(ao.getBoxPrice()+"") && ao.getBoxPrice() >0){
					bookInfo.setCurrBoxprice(ao.getBoxPrice());
				}else{
					bookInfo.setCurrBoxprice(0f);
				}
				bookInfo.setPayTime(new Date());
				if(null != bookInfo.getOwnCount() ||!"".equals(bookInfo.getOwnCount())){
					bookInfo.setOwnCount(bookInfo.getOwnCount() + 1);
				}else {
					bookInfo.setOwnCount(1);
				}
				bookInfoDAO.attachDirty(bookInfo);
				Box box = boxDAO.findById(bookInfo.getBox().getBoxId());
				box.setStatus(desStatus);
				boxDAO.attachDirty(box);
				return true;
			}else{
				bookInfo.setStatus(desStatus);
				bookInfo.setBookTime(new Date());
				if(null != bookInfo.getOwnCount() ||!"".equals(bookInfo.getOwnCount())){
					
				}else {
					bookInfo.setOwnCount(0);
				}
				bookInfoDAO.attachDirty(bookInfo);
				return true;
			}
		}
		return false;
	}
		
	public List<BookInfo> getAllBookInfo(String studNo) throws Exception{
		List<BookInfo> bookInfoes = null;
		if(null == studNo || "".equals(studNo)){
			bookInfoes = bookInfoDAO.findByExample(new BookInfo());
		}else{
			bookInfoes = bookInfoDAO.findByProperty("studNo", studNo);
		}
		
		List<BookInfo> books = null;
		if(null != bookInfoes){
			books = new ArrayList<BookInfo>();
			for(BookInfo bi : bookInfoes){
				if(!StaticUtil.BOOKINFO_BOOK_STATUS_NOBOOK.equals(bi.getStatus())){
					books.add(bi);
				}
			}
		}
		return books ;
	}
		
	public String addFloorCate(FloorCate floorCate) throws Exception{
		List floorCates = floorCateDAO.findByProperty("cateName", floorCate.getCateName());
		if(floorCates.size()>0){
			return "本分类名称已存在！";
		}else{
			floorCateDAO.save(floorCate);
			return "true";
		}
	}
	
	public void deleteFloorCate(String floorCateId) throws Exception {
		if(null != floorCateId && !"".equals(floorCateId)){
			List<RowCate> rowCates = rowCateDAO.findByProperty("floorCate.floorCateId", Integer.valueOf(floorCateId));
			if(null != rowCates){
				for(RowCate rc : rowCates){
					deleteRowCate(rc.getRowCateId()+"");
				}
				floorCateDAO.delete(floorCateDAO.findById(Integer.valueOf(floorCateId)));
			}
		}
	}

	public String editFloorCate(String floorCateId, String cateName)
			throws Exception {
		if(null != floorCateId && !"".equals(floorCateId) 
					&& null != cateName && !"".equals(cateName)){
			List floorCates = floorCateDAO.findByProperty("cateName", cateName);
			if(floorCates.size()>0){
				return "本分类名称已存在！";
			}else{
				FloorCate fc = floorCateDAO.findById(Integer.valueOf(floorCateId));
				fc.setCateName(cateName);
				floorCateDAO.attachDirty(fc);
				return "true";
			}
			
		}else
			return "fail";
	}

	public FloorCate showFloorCateById(String floorCateId) throws Exception {
		if(null != floorCateId && !"".equals(floorCateId)){
			return floorCateDAO.findById(Integer.valueOf(floorCateId));
		}
		return null;
	}
		
	public List<RowCate> getAllRowCates(String floorCateId) throws Exception {
		if(null != floorCateId && !"".equals(floorCateId)) {
			return rowCateDAO.findByProperty("floorCate.floorCateId", Integer.valueOf(floorCateId));
		}
		return null;
	}
	
	public String addRowCate(String floorCateId, String cateName)
		throws Exception {
		if(null != floorCateId && !"".equals(floorCateId) && null != cateName && !"".equals(cateName) ) {
			List<RowCate> rowCates = getAllRowCates(floorCateId);
			if(null != rowCates){
				for(RowCate rc : rowCates){
					if(cateName.equals(rc.getCateName())){
						return "本分类名称已存在！";
					}
				}
				RowCate rowCate= new RowCate();
				rowCate.setCateName(cateName);
				rowCate.setFloorCate(floorCateDAO.findById(Integer.valueOf(floorCateId)));
				rowCateDAO.save(rowCate);
				return "true";
			}else
				return "fail";
		}else
			return "fail";
	}
	
	public void deleteRowCate(String rowCateId) throws Exception {
		if(null != rowCateId && !"".equals(rowCateId)){
			List<Box> boxes = boxDAO.findByProperty("rowCate.rowCateId", Integer.valueOf(rowCateId));
			if(null != boxes){
				for(Box box : boxes){
					deleteBox(box.getBoxId()+"");
				}
				rowCateDAO.delete(rowCateDAO.findById(Integer.valueOf(rowCateId)));
			}
			
		}	
	}
	
	public String editRowCate(String floorCateId,String rowCateId, String cateName) throws Exception {
		if(null != rowCateId && !"".equals(rowCateId) 
				&& null != cateName && !"".equals(cateName)){
			List<RowCate> rowCates = getAllRowCates(floorCateId);
			if(null != rowCates){
				for(RowCate rc : rowCates){
					if(cateName.equals(rc.getCateName())){
						return "本分类名称已存在！";
					}
				}
				RowCate rc = rowCateDAO.findById(Integer.valueOf(rowCateId));
				rc.setCateName(cateName);
				rowCateDAO.attachDirty(rc);
				return "true";
			}else
				return "fail";
		}else
			return "fail";
	
	}

	public RowCate showRowCateById(String rowCateId) throws Exception {
		if(null != rowCateId && !"".equals(rowCateId)){
			return rowCateDAO.findById(Integer.valueOf(rowCateId));
		}
		return null;
	}
	
	public List<Box> getAllBoxByRowId(String rowCateId) throws Exception{
		if(null != rowCateId && !"".equals(rowCateId)) {
			return boxDAO.findByProperty("rowCate.rowCateId", 
						Integer.valueOf(rowCateId));
		}
		return null;
	}
	
	public String addBox(String rowCateId,String boxName) throws Exception{
		if(null != rowCateId && !"".equals(rowCateId) && null != boxName ) {
			List<Box> boxes = getAllBoxByRowId(rowCateId);
			if(null != boxes){
				for(Box box : boxes){
					if(boxName.equals(box.getBoxName())){
						return "本箱子名称已存在！";
					}
				}
				Box box = new Box();
				box.setRowCate(rowCateDAO.findById(Integer.valueOf(rowCateId)));
				box.setBoxName(boxName);
				box.setStatus(StaticUtil.BOX_BOOK_STATUS_NOBOOK);
				boxDAO.save(box);
				return "true";
			}else
				return "fail";
		}else
			return "fail";
	}
	
	public String editBox(String rowCateId,String boxId,String boxName)throws Exception{
		if(null != boxId && !"".equals(boxId) 
				&& null != boxName && !"".equals(boxName)){
			List<Box> boxes = getAllBoxByRowId(rowCateId);
			if(null != boxes){
				for(Box box : boxes){
					if(boxName.equals(box.getBoxName())){
						return "本箱子名称已存在！";
					}
				}
				Box box = boxDAO.findById(Integer.valueOf(boxId));
				box.setBoxName(boxName);
				boxDAO.attachDirty(box);
				return "true";
			}else
				return "fail";
		}else
			return "fail";
	}
	
	public void deleteBox(String boxId)throws Exception{
		if(null != boxId && !"".equals(boxId)){
			List<BookInfo> bookInfos = bookInfoDAO.findByProperty("box.boxId", Integer.valueOf(boxId));
			if(null != bookInfos){
				for(BookInfo bi : bookInfos){
					bookInfoDAO.delete(bi);
				}
				boxDAO.delete(boxDAO.findById(Integer.valueOf(boxId)));
			}
			
		}	
	}
	
	public Box showBoxById(String boxId) throws Exception{
		if(null != boxId && !"".equals(boxId)){
			return boxDAO.findById(Integer.valueOf(boxId));
		}
		return null;
	}
	
	public boolean autoAddRow(String floorCateId, String rowSize)
			throws Exception {
		if(null != floorCateId && !"".equals(floorCateId) && null != rowSize && !"".equals(rowSize) 
					&& Integer.valueOf(rowSize) > 0){
			RowCate rc = null;
			for(int i=0;i<Integer.valueOf(rowSize);i++){
				addRowCate(floorCateId, floorCateDAO.findById(Integer.valueOf(floorCateId)).getCateName() + "-" + (i+1));
			}	
			return true;
		}
		return false;
	}

	public boolean autoAddBox(String rowCateId,String boxSize) throws Exception{
		if(null != rowCateId && !"".equals(rowCateId) && null != boxSize && !"".equals(boxSize) 
				&& Integer.valueOf(boxSize) > 0){
			for(int i=0;i<Integer.valueOf(boxSize);i++){
				addBox(rowCateId, (i+1)+"");
			}	
			return true;
		}
		return false;
	}
	
	public List<BookInfo> findBySignTime(String time) throws Exception{
		if(null != time && !"".equals(time)){
			List<BookInfo> bookInfos = bookInfoDAO.findBySignTime(time);
			List<BookInfo> result = null;
			if(null != bookInfos){
				result = new ArrayList<BookInfo>();
				for(BookInfo bi : bookInfos){
					if(StaticUtil.BOOKINFO_STATUS_HASPAY.equals(bi.getStatus())){
						result.add(bi);
					}
				}
			}
			return result;
		}
		return null;
	}
	
	public List<BookInfo> findByPartTime(String beginTime,String endTime) throws Exception{
		if(null != beginTime && !"".equals(beginTime) && null != endTime && !"".equals(endTime)){
			List<BookInfo> bookInfos = bookInfoDAO.findByPartTime(beginTime, endTime);
			List<BookInfo> result = null;
			if(null != bookInfos){
				result = new ArrayList<BookInfo>();
				for(BookInfo bi : bookInfos){
					if(StaticUtil.BOOKINFO_STATUS_HASPAY.equals(bi.getStatus())){
						result.add(bi);
					}
				}
			}
			return result;
		}
		return null;
	}
	
	public float getSumPrice(List<BookInfo> bookInfos) throws Exception{
		float sum = 0;
		if(null != bookInfos && bookInfos.size()>0){
			for(BookInfo bi : bookInfos){
				if(null != bi.getCurrBoxprice() && !"NULL".equals(bi.getCurrBoxprice()) ){
					sum += bi.getCurrBoxprice();
				}
			}
		}
		return sum;
	}
	
	public boolean setBookTime(String beginTime, String endTime)
		throws Exception {
		if(null != beginTime && !"".equals(beginTime) && null != endTime && !"".equals(endTime)){
			AdminOper ao = getAdmin();
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置格式
			ao.setBookBeginTime( f.parse(beginTime+" 00:00:01"));
			ao.setBookEndTime( f.parse(endTime+" 23:59:59"));
			adminOperDAO.attachDirty(ao);
			return true;
		}
		return false;
	}
	public boolean setConTime(String beginTime, String endTime)
		throws Exception {
		if(null != beginTime && !"".equals(beginTime) && null != endTime && !"".equals(endTime)){
			AdminOper ao = getAdmin();
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置格式
			ao.setConBeginTime( f.parse(beginTime+" 00:00:01"));
			ao.setConEndTime( f.parse(endTime+" 23:59:59"));
			adminOperDAO.attachDirty(ao);
			return true;
		}
		return false;
	}

	
	public boolean judgeBookTime() throws Exception {
		AdminOper adminOper = getAdmin();
		Date currDate = new Date();
		DateFormat df=DateFormat.getDateTimeInstance();
		return currDate.after(df.parse(adminOper.getBookBeginTime().toString())) 
			&& currDate.before(df.parse(adminOper.getBookEndTime().toString()));
	}
	
	public boolean judgeConTime() throws Exception {
		AdminOper adminOper = getAdmin();
		Date currDate = new Date();
		DateFormat df=DateFormat.getDateTimeInstance();
		return currDate.after(df.parse(adminOper.getConBeginTime().toString())) 
			&& currDate.before(df.parse(adminOper.getConEndTime().toString()));
	}
	
	public List<BookInfo> getBookInfosByPartTime(String beginTime,
			String endTime) throws Exception {
		List<BookInfo> infos = null;
		if(null != beginTime && !"".equals(beginTime) 
				&& null != endTime && !"".equals(endTime)){
			infos = new ArrayList<BookInfo>();
			List<BookInfo> bookInfos = bookInfoDAO.findByPartTime(beginTime, endTime);
			for(BookInfo bi : bookInfos){
				if(StaticUtil.BOOKINFO_STATUS_HASPAY.equals(bi.getStatus())){
					infos.add(bi);
				}
			}
		}
		return infos;
	}
	
	public synchronized boolean bookBoxOk(String boxId,String studNo)throws Exception{
		if(null != boxId && !"".equals(boxId) && null != findInfoByStudNo(studNo)){
			editBoxStatus(boxId,StaticUtil.BOX_BOOK_STATUS_HASBOOK);
			
			BookInfo bi = findInfoByStudNoAndBoxId(studNo, boxId);
			BookInfo newBi = new BookInfo();
			newBi.setBox(boxDAO.findById(Integer.valueOf(boxId)));
			newBi.setBookTime(new Date());
			Box box =  boxDAO.findById(Integer.valueOf(boxId));
			RowCate rc = rowCateDAO.findById(box.getRowCate().getRowCateId());
			FloorCate fc = floorCateDAO.findById(rc.getFloorCate().getFloorCateId());
			String boxName = box.getBoxName();
			String rowCateName = rc.getCateName();
			String floorCateName = fc.getCateName();
			newBi.setBoxPlace(floorCateName + " " + rowCateName + " 第" + boxName + "号箱子");
			newBi.setStudClass(bi.getStudClass());
			newBi.setCollege(bi.getCollege());
			newBi.setPhone(bi.getPhone());
			newBi.setStatus(StaticUtil.BOOKINFO_STATUS_HASBOOK);
			newBi.setCurrBoxprice(0f);
			newBi.setOwnCount(0);
			newBi.setStudName(bi.getStudName());
			newBi.setStudNo(bi.getStudNo());
			bookInfoDAO.save(newBi);
			bookInfoDAO.delete(bi);
			return true;
		}else
			return false;
	}
	
	public Box findBoxById(String boxId) throws Exception{
		if(null != boxId && !"".equals(boxId)){
			return boxDAO.findById(Integer.valueOf(boxId));
		}
		return null;
	}
	
	public boolean cancelBookBox(String boxId,String bookInfoId) throws Exception{
		if(null != boxId && !"".equals(boxId) && null != bookInfoId && !"".equals(bookInfoId)){
			editBoxStatus(boxId,StaticUtil.BOX_BOOK_STATUS_NOBOOK);
			BookInfo bi = getBookInfoById(bookInfoId);
			bi.setBox(null);
			bi.setBookTime(null);
			bi.setBoxPlace("");
			bi.setStatus(StaticUtil.BOOKINFO_BOOK_STATUS_NOBOOK);
			bookInfoDAO.attachDirty(bi);
			return true;
		}
		return false;
	}
	public boolean deleteBookNoPayRec() throws Exception{
		List<BookInfo> bookInfos = bookInfoDAO.findByExample(new BookInfo());
        boxDAO.cleanCache();
		if(null != bookInfos){
			for(BookInfo bi : bookInfos){
				if(!StaticUtil.BOOKINFO_STATUS_HASPAY.equals(bi.getStatus())){
					if("".equals(bi.getBookTime()) || "null".equals(bi.getBookTime()) || "NULL".equals(bi.getBookTime()) 
							||StaticUtil.BOOKINFO_BOOK_STATUS_NOBOOK.equals( bi.getStatus()) || "".equals( bi.getStatus())){
						bookInfoDAO.delete(bi);
					}else if(!StaticUtil.BOX_BOOK_STATUS_HASPAY
							.equals(getBoxById(bi.getBox().getBoxId()+"").getStatus()))	{
						//把箱子状态置0
						editBoxStatus(bi.getBox().getBoxId()+"", StaticUtil.BOX_BOOK_STATUS_NOBOOK);
						//如果是新用户把bookInfo表的记录删除，如果是老用户就把状态置0
						if(null == bi.getPayTime() || "null".equals(bi.getPayTime()) 
									||0 == bi.getOwnCount()){
							bookInfoDAO.delete(bi);
						}else{
							editBookStatus(bi, StaticUtil.BOOKINFO_BOOK_STATUS_NOBOOK);
						}
					}
				}
			}
			return true;
		}
		return false;
	}
	public boolean clearBoxStatus() throws Exception{
		List<Box> boxes = boxDAO.findByExample(new Box());
        boxDAO.cleanCache();
		if(null != boxes){
			for(Box box : boxes){
				box.setStatus(StaticUtil.BOX_BOOK_STATUS_NOBOOK);
				boxDAO.attachDirty(box);
			}
			return true;
		}else{
			return false;
		}
	}
	
	public boolean addBookInfoBoxAttr(String studNo,String boxId,String studName) throws Exception{
		if(null != studNo && !"".equals(studNo) && null != boxId && !"".equals(boxId)){
			Box box =  boxDAO.findById(Integer.valueOf(boxId));
			RowCate rc = rowCateDAO.findById(box.getRowCate().getRowCateId());
			FloorCate fc = floorCateDAO.findById(rc.getFloorCate().getFloorCateId());
			String boxName = box.getBoxName();
			String rowCateName = rc.getCateName();
			String floorCateName = fc.getCateName();
			BookInfo bi = findInfoByStudNoAndBoxId(studNo, boxId);
			if(null == bi){
				BookInfo bookInfo = new BookInfo();
				bookInfo.setStudName(studName);
				bookInfo.setStudNo(studNo);
				bookInfo.setBookTime(new Date());
				bookInfo.setBoxPlace(floorCateName + " " + rowCateName + " 第" + boxName + "号箱子");
				bookInfo.setCollege("");
				bookInfo.setCurrBoxprice(getAdmin().getBoxPrice());
				bookInfo.setOwnCount(0);
				bookInfo.setPhone("");
				bookInfo.setStatus(StaticUtil.BOOKINFO_STATUS_HASBOOK);
				bookInfo.setBox(boxDAO.findById(Integer.valueOf(boxId)));
				bookInfoDAO.save(bookInfo);
			}else{
				BookInfo bookInfo = new BookInfo();
				bookInfo.setStudName(bi.getStudName());
				bookInfo.setStudNo(bi.getStudNo());
				bookInfo.setBookTime(new Date());
				bookInfo.setBoxPlace(floorCateName + " " + rowCateName + " 第" + boxName + "号箱子");
				bookInfo.setCollege(bi.getCollege());
				bookInfo.setCurrBoxprice(getAdmin().getBoxPrice());
				bookInfo.setOwnCount(bi.getOwnCount());
				bookInfo.setPhone(bi.getPhone());
				bookInfo.setStatus(StaticUtil.BOOKINFO_STATUS_HASBOOK);
				bookInfo.setBox(boxDAO.findById(Integer.valueOf(boxId)));
				bookInfoDAO.delete(bi);
				bookInfoDAO.save(bookInfo);
			}
			
			return true;
		}else
			return false;
	}
	
	public boolean editBookInfo(String studNo,String college,String studClass,String phone)throws Exception{
		if(null != studNo && !"".equals(studNo) && null != college && !"".equals(college) 
				&& null != studClass && !"".equals(studClass) && null != phone && !"".equals(phone)){
			List<BookInfo> bookInfos = findInfoByStudNo(studNo);
			if(null != bookInfos){
				for(BookInfo bi : bookInfos){
					bi.setCollege(college);
					bi.setStudClass(studClass);
					bi.setPhone(phone);
					bookInfoDAO.attachDirty(bi);
				}
			}
			return true;
		}else
			return false;
	}
	
	public void deleteBookInfo(String bookInfoId)throws Exception{
		if(null != bookInfoId && !"".equals(bookInfoId)){
			bookInfoDAO.delete(bookInfoDAO.findById(Integer.valueOf(bookInfoId)));
		}
	}
	public synchronized boolean bookBox(String studName,String studNo,String boxId,String status,String college,String studClass,String phone)throws Exception{
		boolean result = true;
			
			//增加bookInfo记录
			addBookInfo(studName, studNo, boxId, status, college, studClass, phone);
			//修改箱子状态
			editBoxStatus(boxId,status);
			
			//删除没有用的bookInfo记录
			List<BookInfo> bookInfos = findInfoByStudNo(studNo);
			if(null != bookInfos && bookInfos.size() >0){
				for(BookInfo bi : bookInfos){
					if(          
							     StaticUtil.BOOKINFO_STATUS_HASBOOK.equals(bi.getStatus()) 
							||//1.已付费状态   2.箱子也已付费
								(StaticUtil.BOOKINFO_STATUS_HASPAY.equals(bi.getStatus()) && StaticUtil.BOX_BOOK_STATUS_HASPAY.equals(findBoxById(bi.getBox().getBoxId()+"").getStatus()))
						    ||//续约时间  
						        (judgeConTime() && (StaticUtil.BOOKINFO_STATUS_HASPAY.equals(bi.getStatus())|| StaticUtil.BOOKINFO_STATUS_HASCON.equals(bi.getStatus())))
						){
						
					}else {
						deleteBookInfo(bi.getBookInfoId()+"");
					}
				}
			}
		return result;
	}
	
	public BookInfo getBookInfoById(String bookInfoId)throws Exception{
		if(null != bookInfoId && !"".equals(bookInfoId)){
			return bookInfoDAO.findById(Integer.valueOf(bookInfoId));
		}else {
			return null;
		}
	}
	
	public int getConBookedAndPayedCount(String studNo) throws Exception{
		int sum = 0;
		List<BookInfo> bookInfos = findInfoByStudNo(studNo);
		if(null != bookInfos){
			for(BookInfo bi : bookInfos){
				if(StaticUtil.BOOKINFO_STATUS_HASCON.equals(bi.getStatus())
						||(null != bi.getBox() && StaticUtil.BOX_BOOK_STATUS_HASPAY.equals(findBoxById(bi.getBox().getBoxId()+"").getStatus()))){
					sum ++ ;
				}
			}
		}
		return sum;
	}
	
	
	public  String setBookBox(String boxId,List<BookInfo>bookInfos,String studNo,String studName)
		throws Exception{
		Box box = findBoxById(boxId);
		//synchronized(box){
			String result = "success";
			if(!judgeBookTime()){
				result = "预约时间已过";
				return result;
			}
			
			if(StaticUtil.BOX_BOOK_STATUS_HASBOOK.equals(box.getStatus()) 
					|| StaticUtil.BOX_BOOK_STATUS_HASPAY.equals(box.getStatus())){
				result = "箱子已被别人选中！请重新选择";
				return result;
			}
			int boxSum = 0;
			BookInfo bookInfo = null;
			if(null != bookInfos && bookInfos.size() >0){
				bookInfo = (BookInfo)bookInfos.get(0);
				for(BookInfo bi : bookInfos){
					if(StaticUtil.BOOKINFO_STATUS_HASBOOK.equals(bi.getStatus()) 
							//续费完，过了缴费阶段，重新开始预约才可以
							|| (
									//1.已付费状态   2.箱子也已付费
									StaticUtil.BOOKINFO_STATUS_HASPAY.equals(bi.getStatus()) 
									&& StaticUtil.BOX_BOOK_STATUS_HASPAY.equals(findBoxById(bi.getBox().getBoxId()+"").getStatus()) )
								){
						boxSum ++;
					}
				}
			}
			String maxBoxCount = getAdmin().getMaxBoxCount();
			boolean flag = true;
			if(null != maxBoxCount && !"".equals(maxBoxCount) && isNumeric(maxBoxCount)){
				if(boxSum >=Integer.valueOf(maxBoxCount)){
					result = "最多允许选择"+maxBoxCount+"个箱子！";
					flag = false;
				}
			}else{
				if(boxSum >= 1){
					result = "最多允许选择1个箱子！";
					flag = false;
				}
			}
			if(flag){
				//增加预约箱子记录，采取先获取，再赋值，删除原来没有预约的记录
				if(null != bookInfo){
					 if(!bookBox(studName,studNo,boxId,StaticUtil.BOOKINFO_STATUS_HASBOOK,
								bookInfo.getCollege(),bookInfo.getStudClass(),bookInfo.getPhone())){
						 result = "fail";
					 }
				}else{
					if(!bookBoxOk(boxId,studNo)){
						result = "fail";
					}
				}
			}
			return result;
		//}
	
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
