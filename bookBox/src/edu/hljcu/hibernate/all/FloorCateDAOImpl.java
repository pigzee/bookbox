package edu.hljcu.hibernate.all;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * FloorCate entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.hljcu.hibernate.all.FloorCate
 * @author MyEclipse Persistence Tools
 */

public class FloorCateDAOImpl extends HibernateDaoSupport implements FloorCateDAO {
	private static final Log log = LogFactory.getLog(FloorCateDAOImpl.class);
	// property constants
	public static final String CATE_NAME = "cateName";

	public void save(FloorCate transientInstance) {
		log.debug("saving FloorCate instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FloorCate persistentInstance) {
		log.debug("deleting FloorCate instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FloorCate findById(java.lang.Integer id) {
		log.debug("getting FloorCate instance with id: " + id);
		try {
			FloorCate instance = (FloorCate) getHibernateTemplate().get(
					"edu.hljcu.hibernate.all.FloorCate", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FloorCate instance) {
		log.debug("finding FloorCate instance by example");
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
		log.debug("finding FloorCate instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FloorCate as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCateName(Object cateName) {
		return findByProperty(CATE_NAME, cateName);
	}

	public FloorCate merge(FloorCate detachedInstance) {
		log.debug("merging FloorCate instance");
		try {
			FloorCate result = (FloorCate) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FloorCate instance) {
		log.debug("attaching dirty FloorCate instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FloorCate instance) {
		log.debug("attaching clean FloorCate instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}