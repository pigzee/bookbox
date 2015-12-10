package edu.hljcu.hibernate.all;

import java.util.List;

public interface BookInfoDAO {

	public void save(BookInfo transientInstance);
	public void delete(BookInfo persistentInstance); 
	public BookInfo findById(java.lang.Integer id);
	public List findByExample(BookInfo instance);
	public List findByProperty(String propertyName, Object value);
	public List findByStudNo(Object studNo) ;
	public List findByStudName(Object studName); 
	public List findByStatus(Object status);
	public List findByCurrBoxprice(Object currBoxprice); 
	public List findByOwnCount(Object ownCount);
	public BookInfo merge(BookInfo detachedInstance);
	public void attachDirty(BookInfo instance);
	public void attachClean(BookInfo instance);
	public List findBySignTime(String signTime);
	public List findByPartTime(String beginTime,String endTime);
}
