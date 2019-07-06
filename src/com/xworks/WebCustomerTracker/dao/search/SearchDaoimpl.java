package com.xworks.WebCustomerTracker.dao.search;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.xworks.WebCustomerTracker.dto.CRMDto;

@Repository
public class SearchDaoimpl implements SearchDao{
	
	@Autowired
	private SessionFactory factory;
	
	public SearchDaoimpl()
	{
		System.out.println("created " +this.getClass().getSimpleName());
	}
	

	@Override
	public List search() {
		Session session = factory.openSession();
		List<CRMDto> crm = session.createQuery("from CRMDto").list();
		for (CRMDto crmDto : crm) {
			System.out.println(crmDto);
			
		}
		return crm;
	}

	@Override
	public String update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(int id) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		/*Query myQuerry = (Query) session.createQuery("delete from customer where id=:customerId");
		myQuerry.setParameter("customerId", custId);
		myQuerry.executeUpdate();*/
		System.out.println("in dao the value of custid is " +id);
		CRMDto crmdto = session.get(CRMDto.class, id);
		session.delete(crmdto);
		System.out.println("after delete");
		tx.commit();
		return null;
	}

	@Override
	public int insert(CRMDto crmDto) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Integer pk = (Integer) session.save(crmDto);
		tx.commit();
		return pk;
	}


	@Override
	public CRMDto search(int pk) {
		Session session = factory.openSession();
		CRMDto crmdto = session.get(CRMDto.class, pk);
		return crmdto;
	}


	@Override
	public void saveCustomer(CRMDto theCustomer) {
		// TODO Auto-generated method stub
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(theCustomer);
		tx.commit();
		//System.out.println("pk update is " +pk);
		
		
	}
	
	
	

}
