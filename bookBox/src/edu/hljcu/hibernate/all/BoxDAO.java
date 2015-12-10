package edu.hljcu.hibernate.all;

import java.util.List;

public interface BoxDAO {
	public void save(Box transientInstance);
	public void delete(Box persistentInstance); 
	public Box findById(java.lang.Integer id) ;
	public List findByExample(Box instance);
	public List findByProperty(String propertyName, Object value); 
	public List findByBoxName(Object boxName) ;
	public List findByStatus(Object status) ;
	public Box merge(Box detachedInstance); 
	public void attachDirty(Box instance) ;
	public void attachClean(Box instance) ;

    public void cleanCache();
}
