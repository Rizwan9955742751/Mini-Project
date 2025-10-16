package com.rizwan.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rizwan.dto.ViewFilterRequest;
import com.rizwan.entity.Enquiry;
import com.rizwan.service.EnquiryServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	private EnquiryServiceImpl enquiryService;

	public EnquiryController(EnquiryServiceImpl enquiryService) {

		this.enquiryService = enquiryService;
	}
	@PostMapping("/filter-enq")
	public String filterEnquries(HttpServletRequest request, ViewFilterRequest viewFilterRequest,Model model)
	{
		HttpSession session = request.getSession(false);
		Integer CousellorId = (Integer) session.getAttribute("counsellorId");
		List<Enquiry> enqList = enquiryService.getEnquiryWithFilter(viewFilterRequest, CousellorId);
		model.addAttribute("enquiries",enqList);
		return "viewEnqsPage";
	}
	
	@GetMapping("/view-eqnuiries")
	public String getEnquires( HttpServletRequest request , Model model)
	{
		HttpSession session = request.getSession(false);
		Integer CousellorId = (Integer) session.getAttribute("counsellorId");
		List<Enquiry> enqList = enquiryService.getAllEnquiry(CousellorId);
		model.addAttribute("enquiries",enqList);
		ViewFilterRequest filterReq= new ViewFilterRequest();
		model.addAttribute( "viewFilterRequest",filterReq);
		return "viewEnqsPage";
	}
	
 @GetMapping("enquiry")
	public String addEnquiryPage(Model model) {
		
	 Enquiry enquiry = new Enquiry();
	 model.addAttribute("enquiry", enquiry);
		return "enquiryForm";
	}
  @GetMapping("/editEnq") 
 public String editEnquiry(@RequestParam("enqId") Integer enqId, Model model)
 {
	  Enquiry enquiry = enquiryService.getEnquiryById(enqId);
	  model.addAttribute("enquiry", enquiry);
	  return "enquiryForm";
 }
 
	@PostMapping("/addEnquiry")
	public String handleAddEnquiry( Enquiry enquiry, HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession(false);
		Integer CousellorId = (Integer) session.getAttribute("counsellorId");
		boolean isSave = enquiryService.addEnuiry(enquiry, CousellorId);
	//	model.addAttribute("enq", enquiry);  // if you are not using @ModelAttribute("enq") then model.addAttribute("enq", enquiry); we have to use
		if(isSave)
		{
			model.addAttribute("smsg","Enquiry Added");
		}
		else {
			model.addAttribute("emsg","Enquiry Failed to add ");
		}
		enquiry = new Enquiry();
		 model.addAttribute("enquiry", enquiry);
		return "enquiryForm";
	}
}
