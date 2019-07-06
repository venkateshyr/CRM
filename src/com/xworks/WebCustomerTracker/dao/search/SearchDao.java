package com.xworks.WebCustomerTracker.dao.search;

import java.util.List;

import com.xworks.WebCustomerTracker.dto.CRMDto;

//this is to check to comment on github
//Gani comment
public interface SearchDao {
	public List search();
	public String update();
	public String delete(int custId);
	public int insert(CRMDto crmDto);
	public CRMDto search(int pk);
	public void saveCustomer(CRMDto theCustomer);
	
	

}
