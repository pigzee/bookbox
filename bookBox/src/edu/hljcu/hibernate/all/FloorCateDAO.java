package edu.hljcu.hibernate.all;

import java.util.List;

public interface FloorCateDAO {
	public void save(FloorCate transientInstance) ;
	public void delete(FloorCate persistentInstance);
	public FloorCate findById(java.lang.Integer id);
	public List findByExample(FloorCate instance) ;
	public List findByProperty(String propertyName, Object value); 
	public List findByCateName(Object cateName) ;
	public FloorCate merge(FloorCate detachedInstance); 
	public void attachDirty(FloorCate instance);
	public void attachClean(FloorCate instance) ;
}
