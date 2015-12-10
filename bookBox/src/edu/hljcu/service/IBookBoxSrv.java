package edu.hljcu.service;

import java.util.List;

import edu.hljcu.hibernate.all.AdminOper;
import edu.hljcu.hibernate.all.BookInfo;
import edu.hljcu.hibernate.all.Box;
import edu.hljcu.hibernate.all.FloorCate;
import edu.hljcu.hibernate.all.RowCate;
import edu.hljcu.hibernate.all.Students;

public interface IBookBoxSrv {

	/**学生登录验证
	 * @param studNo
	 * @param studName
	 * @return
	 * @throws Exception
	 */
	public Students validate(String studNo,String studName) throws Exception;

	/**管理员登陆验证
	 * @param adminName
	 * @param adminPsw
	 * @return
	 * @throws Exception
	 */
	public AdminOper validateAdmin(String adminName,String adminPsw)  throws Exception;
	
	/**编辑管理员登陆名称
	 * @param name
	 * @throws Exception
	 */
	public void editAdminName(String name) throws Exception;
	
	/**设置管理员登陆密码
	 * @param oldPsw
	 * @param newPsw
	 * @return
	 * @throws Exception
	 */
	public boolean editAdminPsw(String oldPsw,String newPsw) throws Exception;
	
	/**设置箱子价钱
	 * @param boxPrice
	 * @throws Exception
	 */
	public void editBoxPrice(String boxPrice) throws Exception;
	
	/**设置允许预约的最大箱子数
	 * @param maxBoxCount
	 * @throws Exception
	 */
	public void editMaxBoxCount(String maxBoxCount) throws Exception;
	
	/**获得所有楼层分类
	 * @return
	 * @throws Exception
	 */
	public List getAllFloorCate() throws Exception;
	
	/**判断某学生是否已经处于预定或者已付费状态
	 * @param studNo
	 * @return
	 * @throws Exception
	 */
	public boolean hasBookOrPayBox(String studNo) throws Exception;
	
	/**通过楼层分类列出 下面所有对应的排号
	 * @return
	 * @throws Exception
	 */
	public List<RowCate> findRowByFloorId(String FloorId) throws Exception;
	
	/**通过排号分类列出下面所有对应的箱子
	 * @param rowId
	 * @return
	 * @throws Exception
	 */
	public List<Box> findBoxByRowId(String rowId) throws Exception;
	
	/**查看学生是否已经预定或者付费了箱子
	 *因为一个同学可以预约多个箱子 
	 * @param studNo
	 * @return
	 * @throws Exception
	 */
	public List<BookInfo> findInfoByStudNo(String studNo) throws Exception;
	
	/**通过Id获得箱子
	 * @param boxId
	 * @return
	 * @throws Exception
	 */
	public Box getBoxById(String boxId) throws Exception;
	
	/**通过学号获得预定信息
	 * @param studNo
	 * @return
	 * @throws Exception
	 */
	public BookInfo getBookedBoxByNo(String studNo) throws Exception;
	
	/**设置箱子状态
	 * @param boxId
	 * @param status
	 * @throws Exception
	 */
	public void editBoxStatus(String boxId,String status) throws Exception;
	
	/**增加预定信息记录
	 * @param studName
	 * @param studNo
	 * @param boxId
	 * @param status
	 * @param college
	 * @param studClass
	 * @param phone
	 * @throws Exception
	 */
	public void addBookInfo(String studName,String studNo,String boxId,String status,String college,String studClass,String phone) throws Exception;
	
	/**通过箱子Id设置预约信息状态
	 * @param studNo
	 * @param status
	 * @param boxId
	 * @throws Exception
	 */
	public void editBookInfoStatus(String studNo,String status,String boxId) throws Exception;
	
	/**获得AdminOper类
	 * @return
	 * @throws Exception
	 */
	public AdminOper getAdmin()throws Exception;
	
	public AdminOper getAdminById(String adminId) throws Exception;
	
	
	/**通过此楼层获得所有箱子数
	 * @param floorCateId
	 * @return
	 * @throws Exception
	 */
	public int getBoxCountByFloor(String floorCateId) throws Exception; 
	
	/**获得此楼层已被预约的箱子数
	 * @param floorCateId
	 * @return
	 * @throws Exception
	 */
	public int getHasBookedCountByFloor(String floorCateId) throws Exception; 
	
	/**获得此楼层已被付费的箱子数
	 * @param floorCateId
	 * @return
	 * @throws Exception
	 */
	public int getHasPayedCountByFloor(String floorCateId) throws Exception; 
	
	/**获得此楼层剩余的箱子数
	 * 	可以通过所有的减去前面的，但是程序运行复杂。所以采用此法
	 *  三个方法可以通过传值减少代码量，这里不做处理了。
	 * @param floorCateId
	 * @return
	 * @throws Exception
	 */
	public int getLastCountByFloor(String floorCateId) throws Exception; 
	
	/**获得所有箱子数
	 * @return
	 * @throws Exception
	 */
	public int getAllBoxCount() throws Exception; 
	
	/**获得所有被预约的箱子数
	 * @return
	 * @throws Exception
	 */
	public int getAllHasBookedCount() throws Exception; 

	/**获得所有已付款的箱子数
	 * @return
	 * @throws Exception
	 */
	public int getAllHasPayedCount() throws Exception; 

	/**获得剩余的箱子数
	 * @return
	 * @throws Exception
	 */
	public int getAllLastBoxCount() throws Exception;
	
	/**通过学号获得预约信息
	 * @param studNo
	 * @return
	 * @throws Exception
	 */
	public BookInfo getBookInfoByStudNo(String studNo) throws Exception;
	
	/**修改箱子状态
	 * 修改BookInfo表
	 * box表不修改
	 * 特殊情况，学生续约的时候BookInfo表的状态为3，而Book表没有此状态，只有是否被预约。
	 * @param book
	 * @param desStatus
	 * @throws Exception
	 */
	public boolean editBookStatus(BookInfo bookInfo,String desStatus) throws Exception;
	
	/**获得箱子付费条目，studNo为空则查找全部
	 * @param studNo
	 * @return
	 * @throws Exception
	 */
	public List<BookInfo> getAllBookInfo(String studNo) throws Exception;
	
	/**增加楼层分类
	 * @param floorCate
	 * @return
	 * @throws Exception
	 */
	public String addFloorCate(FloorCate floorCate) throws Exception;
	
	/**删除楼层分类
	 * @param floorCateId
	 * @throws Exception
	 */
	public void deleteFloorCate(String floorCateId) throws Exception;
	
	/**编辑楼层分类
	 * @param cateId
	 * @param cateName
	 * @throws Exception
	 */
	public String editFloorCate(String floorCateId,String cateName) throws Exception;
	
	/**通过Id获取楼层分类
	 * @param floorCateId
	 * @return
	 * @throws Exception
	 */
	public FloorCate showFloorCateById(String floorCateId) throws Exception;
	
	public int[] getBoxCountArr() throws Exception;
	
	public int[] getBoxCountArrByFloor(String floorCateId) throws Exception;
	
	public List<RowCate> getAllRowCates(String floorCateId) throws Exception;
	
	public String addRowCate(String floorCateId,String cateName) throws Exception;
	
	public void deleteRowCate(String rowCateId) throws Exception;
	
	public String editRowCate(String floorCateId,String rowCateId,String cateName) throws Exception;
	
	public RowCate showRowCateById(String rowCateId) throws Exception;
	
	public List<Box> getAllBoxByRowId(String rowCateId) throws Exception;
	
	public String addBox(String rowCateId,String boxName) throws Exception;
	
	public String editBox(String rowCateId,String boxId,String boxName)throws Exception;
	
	public void deleteBox(String boxId)throws Exception;
	
	public Box showBoxById(String boxId) throws Exception;
	
	/**自动生成排号
	 * @param floorCateId
	 * @param rowSize 排号个数
	 * @return
	 * @throws Exception
	 */
	public boolean autoAddRow(String floorCateId,String rowSize) throws Exception;
	
	/**自动生成箱子号
	 * @param rowCateId
	 * @param boxSize 箱子个数
	 * @return
	 * @throws Exception
	 */
	public boolean autoAddBox(String rowCateId,String boxSize) throws Exception;
	
	/**通过某一天的时间选择缴费的记录
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public List<BookInfo> findBySignTime(String time) throws Exception;
	
	/**通过某一段的时间选择缴费的记录
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public List<BookInfo> findByPartTime(String beginTime,String endTime) throws Exception;
	
	/**获得List的箱子价钱的总和
	 * @param bookInfos
	 * @return
	 * @throws Exception
	 */
	public float getSumPrice(List<BookInfo> bookInfos) throws Exception;
	
	/**设置预约箱子时间
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public boolean setBookTime(String beginTime,String endTime) throws Exception;
	
	/**设置续约箱子时间
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public boolean setConTime(String beginTime,String endTime) throws Exception;
	
	
	/**判断预约箱子时间
	 * @return
	 * @throws Exception
	 */
	public boolean judgeBookTime() throws Exception;
	
	/**判断续约箱子时间
	 * @return
	 * @throws Exception
	 */
	public boolean judgeConTime() throws Exception;
	
	public List<BookInfo> getBookInfosByPartTime(String beginTime,String endTime) throws Exception;
	
	/**把editBoxStatus和添加或者修改bookInfo放在service中
	 * @return
	 * @throws Exception
	 */
	public boolean bookBoxOk(String boxId,String studNo)throws Exception;
	
	/**通过箱子Id查找箱子
	 * @param boxId
	 * @return
	 * @throws Exception
	 */
	public Box findBoxById(String boxId) throws Exception; 
	
	/**取消预定的箱子
	 * @param boxId
	 * @param bookInfoId
	 * @return
	 * @throws Exception
	 */
	public boolean cancelBookBox(String boxId,String bookInfoId) throws Exception;
	
	/**删除预约但是没有续费的记录
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBookNoPayRec() throws Exception;
	
	/**箱子状态全部清零
	 * @return
	 * @throws Exception
	 */
	public boolean clearBoxStatus() throws Exception;
	
	/**设置bookInfo的有关预定箱子的状态
	 * @param studNo
	 * @param boxId
	 * @param studName
	 * @return
	 * @throws Exception
	 */
	public boolean addBookInfoBoxAttr(String studNo,String boxId,String studName) throws Exception;
	
	/**编辑bookInfo的学生的学院，班级，电话号码
	 * @param studNo
	 * @param college
	 * @param studClass
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public boolean editBookInfo(String studNo,String college,String studClass,String phone)
		throws Exception;
	
	public BookInfo findInfoByStudNoAndBoxId(String studNo,String boxId) throws Exception;
	
	public void deleteBookInfo(String bookInfoId)throws Exception;
	
	public boolean bookBox(String studName,String studNo,String boxId,String status,String college,String studClass,String phone) throws Exception;

	public BookInfo getBookInfoById(String bookInfoId)throws Exception;

	/**计算续约阶段，已经续约和缴费的总箱子数
	 * @param studNo
	 * @return
	 * @throws Exception
	 */
	public int getConBookedAndPayedCount(String studNo) throws Exception;
	
	
	public String setBookBox(String boxId,List<BookInfo>bookInfos,String studNo,String studName) throws Exception;


}