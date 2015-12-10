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
 * Students entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.hljcu.hibernate.all.Students
 * @author MyEclipse Persistence Tools
 */

public class StudentsDAOImpl extends HibernateDaoSupport implements StudentsDAO {
	private static final Log log = LogFactory.getLog(StudentsDAOImpl.class);

	// property constants

	public void save(Students transientInstance) {
		log.debug("saving Students instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Students persistentInstance) {
		log.debug("deleting Students instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Students findById(java.lang.Integer id) {
		log.debug("getting Students instance with id: " + id);
		try {
			Students instance = (Students) getHibernateTemplate().get(
					"edu.hljcu.hibernate.all.Students", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Students instance) {
		log.debug("finding Students instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Students instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Students as model where model."
					+ propertyName + "= ?";
			
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public Students merge(Students detachedInstance) {
		log.debug("merging Students instance");
		try {
			Students result = (Students) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Students instance) {
		log.debug("attaching dirty Students instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Students instance) {
		log.debug("attaching clean Students instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}