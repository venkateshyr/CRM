package com.xworks.WebCustomerTracker.controller.search;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xworks.WebCustomerTracker.dao.search.SearchDao;
import com.xworks.WebCustomerTracker.dto.CRMDto;

@Controller
@RequestMapping("/")
public class SearchControllerImpl {
	@Autowired
	private SearchDao searchDaoimpl;
	
	
	
	public  SearchControllerImpl() {
		System.out.println("Created" +this.getClass().getSimpleName());
	}
	
	@RequestMapping("/getUserdetail.do")
	public  ModelAndView customerData()
	{
		System.out.println("inside Controller Customer data");
		ModelAndView mdl = new ModelAndView("searchresult");
		List customer= searchDaoimpl.search();
		mdl.addObject("cus" ,customer);
		return mdl;
		
	}
	
	@RequestMapping("/formfForAdd.do")
	public ModelAndView insertcontroller()
	{
		ModelAndView addcus = new ModelAndView("customerAdd");
		
		
		return addcus;
	}
	
	@RequestMapping("/addtoDB.do")
	public ModelAndView insertcontroller(@ModelAttribute CRMDto crmDto)
	{
		ModelAndView cus = new ModelAndView("searchresult");
		Integer pk = searchDaoimpl.insert(crmDto);
		List customer= searchDaoimpl.search();
		cus.addObject("cus" ,customer);
		return cus;
	}
	
	@GetMapping("/delete.do")
	public ModelAndView deleteController(@RequestParam("customerId") int cusId)
	{
		System.out.println("customer id is " +cusId);
		ModelAndView cus = new ModelAndView("searchresult");
		searchDaoimpl.delete(cusId);
		List customer = searchDaoimpl.search();
		cus.addObject("cus" , customer);
		
		return cus;
	}
	
	@GetMapping("/update.do")
	public String updateController(@RequestParam("customerId") int cusId  , Model themodel)
	{
		//searchDaoimpl.search()
		//themodel.addAttribute(arg0)
		CRMDto customer = searchDaoimpl.search(cusId);
		themodel.addAttribute("customer" , customer);
		
		return "update";
	}
	
	@PostMapping("/save.do")
	public ModelAndView saveCustomer(@ModelAttribute("customer") CRMDto theCustomer) {
		
		// save the customer using our service
		searchDaoimpl.saveCustomer(theCustomer);
		ModelAndView mdl = new ModelAndView("searchresult");
		List customer= searchDaoimpl.search();
		mdl.addObject("cus" ,customer);
		return mdl;
		
	}
	

}
