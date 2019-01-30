package com.cl.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.cl.domain.Customer;

public interface CustomerDao {

	Integer findCount(DetachedCriteria criteria);

	List<Customer> findByPage(DetachedCriteria criteria, Integer begin, Integer rows);

	void save(Customer customer);

}
