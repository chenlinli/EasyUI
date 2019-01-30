package com.cl.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.cl.domain.Customer;
import com.cl.domain.PageBean;
import com.cl.service.CustomerService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer=new Customer();
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	//���յ�ǰҳ����ÿҳ��ʾ�ļ�¼������easyui���й涨��page��rows
	private Integer page=1;
	private Integer rows=3;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String findAll() throws IOException{
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		PageBean<Customer> pagebean = customerService.findByPage(criteria,page,rows);
		//תjson��ʹ��jsonlib/fastjson
		System.out.println(pagebean);
		Map map = new HashMap<String,Object>();
		map.put("total", pagebean.getTotalCount());
		map.put("rows", pagebean.getList());
		//mapתjson
		JSONObject jsonObject = JSONObject.fromObject(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		return NONE;
	}

	public String save() throws IOException{
		Map map = new HashMap<String,Object>();
		try{
			customerService.save(customer);
			map.put("msg", "����ɹ�");
		}catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "����ʧ��");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		return NONE;
	}
}
