package edu.hljcu.hibernate.all;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Box
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see edu.hljcu.hibernate.all.Box
 * @author MyEclipse Persistence Tools
 */

public class BoxDAOImpl extends HibernateDaoSupport implements BoxDAO {
	private static final Log log = LogFactory.getLog(BoxDAOImpl.class);
	// property constants
	public static final String BOX_NAME = "boxName";
	public static final String STATUS = "status";

	public void save(Box transientInstance) {
		log.debug("saving Box instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Box persistentInstance) {
		log.debug("deleting Box instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

    //TODO liujian
    @Cacheable(value = "boxCache")
	public Box findById(java.lang.Integer id) {
		log.debug("getting Box instance with id: " + id);
		try {
			Box instance = (Box) getHibernateTemplate().get(
					"edu.hljcu.hibernate.all.Box", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Box instance) {
		log.debug("finding Box instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Box instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Box as model where model."
					+ propertyName + "= ?";
			
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBoxName(Object boxName) {
		return findByProperty(BOX_NAME, boxName);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}


	public Box merge(Box detachedInstance) {
		log.debug("merging Box instance");
		try {
			Box result = (Box) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Box instance) {
		log.debug("attaching dirty Box instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Box instance) {
		log.debug("attaching clean Box instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

    @Override
    @CacheEvict(value = "boxCache", allEntries=true)
    public void cleanCache() {
       logger.info("boxCache is clean");
    }
}