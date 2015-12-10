package edu.hljcu.hibernate.all;

import java.util.List;

public interface StudentsDAO {

	public void save(Students transientInstance);
	public void delete(Students persistentInstance);
	public Students findById(java.lang.Integer id);
	public List findByExample(Students instance) ;
	public List findByProperty(String propertyName, Object value); 
	public Students merge(Students detachedInstance);
	public void attachDirty(Students instance) ;
	public void attachClean(Students instance) ;
}
