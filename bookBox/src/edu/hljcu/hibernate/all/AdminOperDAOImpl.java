package edu.hljcu.hibernate.all;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * AdminOper entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.hljcu.hibernate.all.AdminOper
 * @author MyEclipse Persistence Tools
 */

public class AdminOperDAOImpl extends HibernateDaoSupport implements AdminOperDAO {
	private static final Log log = LogFactory.getLog(AdminOperDAOImpl.class);
	// property constants
	public static final String ADMIN_NAME = "adminName";
	public static final String ADMIN_PSW = "adminPsw";
	public static final String BOX_PRICE = "boxPrice";

	public void save(AdminOper transientInstance) {
		log.debug("saving AdminOper instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AdminOper persistentInstance) {
		log.debug("deleting AdminOper instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AdminOper findById(java.lang.Integer id) {
		log.debug("getting AdminOper instance with id: " + id);
		try {
			AdminOper instance = (AdminOper) getHibernateTemplate().get(
					"edu.hljcu.hibernate.all.AdminOper", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AdminOper instance) {
		log.debug("finding AdminOper instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding AdminOper instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AdminOper as model where model."
					+ propertyName + "= ?";
			 return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAdminName(Object adminName) {
		return findByProperty(ADMIN_NAME, adminName);
	}

	public List findByAdminPsw(Object adminPsw) {
		return findByProperty(ADMIN_PSW, adminPsw);
	}

	public List findByBoxPrice(Object boxPrice) {
		return findByProperty(BOX_PRICE, boxPrice);
	}


	public AdminOper merge(AdminOper detachedInstance) {
		log.debug("merging AdminOper instance");
		try {
			AdminOper result = (AdminOper) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AdminOper instance) {
		log.debug("attaching dirty AdminOper instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AdminOper instance) {
		log.debug("attaching clean AdminOper instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}