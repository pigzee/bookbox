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
 * BookInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.hljcu.hibernate.all.BookInfo
 * @author MyEclipse Persistence Tools
 */

public class BookInfoDAOImpl extends HibernateDaoSupport implements BookInfoDAO {
	private static final Log log = LogFactory.getLog(BookInfoDAOImpl.class);
	// property constants
	public static final String STUD_NO = "studNo";
	public static final String STUD_NAME = "studName";
	public static final String STATUS = "status";
	public static final String CURR_BOXPRICE = "currBoxprice";
	public static final String OWN_COUNT = "ownCount";

	public void save(BookInfo transientInstance) {
		log.debug("saving BookInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BookInfo persistentInstance) {
		log.debug("deleting BookInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BookInfo findById(java.lang.Integer id) {
		log.debug("getting BookInfo instance with id: " + id);
		try {
			BookInfo instance = (BookInfo) getHibernateTemplate().get(
					"edu.hljcu.hibernate.all.BookInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BookInfo instance) {
		log.debug("finding BookInfo instance by example");
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
		log.debug("finding BookInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BookInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStudNo(Object studNo) {
		return findByProperty(STUD_NO, studNo);
	}

	public List findByStudName(Object studName) {
		return findByProperty(STUD_NAME, studName);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByCurrBoxprice(Object currBoxprice) {
		return findByProperty(CURR_BOXPRICE, currBoxprice);
	}

	public List findByOwnCount(Object ownCount) {
		return findByProperty(OWN_COUNT, ownCount);
	}


	public BookInfo merge(BookInfo detachedInstance) {
		log.debug("merging BookInfo instance");
		try {
			BookInfo result = (BookInfo) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BookInfo instance) {
		log.debug("attaching dirty BookInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BookInfo instance) {
		log.debug("attaching clean BookInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findBySignTime(String signTime) {
		log.debug("finding BookInfo instance by signTime");
		try {
			String queryString = "from BookInfo bi where bi.payTime>'"+
			signTime+" 00:00:00' "+" and bi.payTime < '"+signTime+" 23:59:59'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by signTime failed", re);
			throw re;
		}
	}
	
	public List findByPartTime(String beginTime,String endTime) {
		log.debug("finding BookInfo instance by partTime");
		try {
			String queryString = "from BookInfo bi where bi.payTime>'"+
			beginTime+" 00:00:00' "+" and bi.payTime < '"+endTime+" 23:59:59'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by partTime failed", re);
			throw re;
		}
	}
}