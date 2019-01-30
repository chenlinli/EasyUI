package com.cl.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.cl.dao.CustomerDao;
import com.cl.domain.Customer;

public class CustomerDaoInpl extends HibernateDaoSupport implements CustomerDao{

	@Override
	public Integer findCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	public List<Customer> findByPage(DetachedCriteria criteria, Integer begin, Integer rows) {
		criteria.setProjection(null);
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria,begin,rows);
	}

	@Override
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(customer);
	}

}
