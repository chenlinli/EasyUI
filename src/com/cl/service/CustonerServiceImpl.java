package com.cl.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.cl.dao.CustomerDao;
import com.cl.domain.Customer;
import com.cl.domain.PageBean;

@Transactional
public class CustonerServiceImpl implements CustomerService{

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria criteria, Integer page, Integer rows) {
		PageBean<Customer> bean = new PageBean<Customer>();
		bean.setCurrPage(page);
		bean.setPageSize(rows);
		Integer totalCount = customerDao.findCount(criteria);
		bean.setTotalCount(totalCount);
		Double t = Math.ceil(totalCount*1.0/rows);
		bean.setTotalPage(t.intValue());
		Integer begin=(page-1)*rows;
		bean.setList(customerDao.findByPage(criteria,begin,rows));
		return bean;
	}

	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	
}
