package com.cl.service;

import org.hibernate.criterion.DetachedCriteria;

import com.cl.domain.Customer;
import com.cl.domain.PageBean;

public interface CustomerService {

	PageBean<Customer> findByPage(DetachedCriteria criteria, Integer page, Integer rows);

	void save(Customer customer);

}
