package edu.hljcu.hibernate.all;

import java.util.List;

public interface RowCateDAO {

	public void save(RowCate transientInstance);
	public void delete(RowCate persistentInstance);
	public RowCate findById(java.lang.Integer id) ;
	public List findByExample(RowCate instance) ;
	public List findByProperty(String propertyName, Object value); 
	public List findByCateName(Object cateName) ;
	public RowCate merge(RowCate detachedInstance); 
	public void attachDirty(RowCate instance) ;
	public void attachClean(RowCate instance) ;
}
