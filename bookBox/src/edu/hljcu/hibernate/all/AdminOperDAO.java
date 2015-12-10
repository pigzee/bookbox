package edu.hljcu.hibernate.all;

import java.util.List;

public interface AdminOperDAO {

	public void save(AdminOper transientInstance);
	public void delete(AdminOper persistentInstance);
	public AdminOper findById(java.lang.Integer id);
	public List findByExample(AdminOper instance);
	public List findByProperty(String propertyName, Object value);
	public List findByAdminName(Object adminName);
	public List findByAdminPsw(Object adminPsw);
	public List findByBoxPrice(Object boxPrice);
	public AdminOper merge(AdminOper detachedInstance);
	public void attachDirty(AdminOper instance);
	public void attachClean(AdminOper instance);
}
