package com.rizwan.controller;
import com.rizwan.service.CounsellorServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rizwan.dto.DashBoardResponse;
import com.rizwan.entity.Counsellor;

@Controller
public class CounsellorController {
	
    private final CounsellorServiceImpl counsellorServiceImpl;
	
    CounsellorController(CounsellorServiceImpl counsellorServiceImpl) {
        this.counsellorServiceImpl = counsellorServiceImpl;
    }
	
	@GetMapping("/")
	public String index(Model model)
	{ 
		Counsellor cobj = new  Counsellor();
	model.addAttribute("counsellor",cobj);
		return "index";
	}
@PostMapping("/login")
	public String handleLogin(Counsellor counsellor, HttpServletRequest request,  Model model) {
	Counsellor c = counsellorServiceImpl.login(counsellor.getEmailId(), counsellor.getPasswd());
		 if(c==null)
		 {
			  model.addAttribute("emsg","Invalid Credential");
			  return "index";
		 }
		 else {
			 HttpSession session = request.getSession(true);
			 session.setAttribute("counsellorId", c.getCouncilerId());
			/* DashBoardResponse dbObj = counsellorServiceImpl.dashboardResponse(c.getCouncilerId());
		     model.addAttribute("dashBoardInfo",dbObj);
		     // you can also use this line but this is duplicate code , so we
		     /// redirect the dashborad url  that is implemntng  same loginc 
		     */
		     return "redirect:/dashboard";
		 }
	}

@GetMapping("/register")
public String  registerPage(Model model)
{
	Counsellor cobj = new  Counsellor();
	model.addAttribute("counsellor",cobj);
	return "register";
}
@PostMapping("/register")
public String handleRegistration(Counsellor counsellor , Model model)
{
	Counsellor byEmail = counsellorServiceImpl.findByEmail(counsellor.getEmailId());
	if(byEmail!=null)
	{
		model.addAttribute("emsg","duplicate Email ");
		return "register";
	}
	
	
	boolean isregister = counsellorServiceImpl.register(counsellor);
	if(isregister)
	{// success
		model.addAttribute("smsg","Registration Sucsess...");
	} 
	else {
		// Failure
		model.addAttribute("emsg","Registration failed ");
	}
	return "register";
}
@GetMapping("dashboard")
public String displayDashBoard(HttpServletRequest request, Model model)
{
	 HttpSession session = request.getSession(false);
		Integer CousellorId = (Integer) session.getAttribute("counsellorId");
		 DashBoardResponse dbObj = counsellorServiceImpl.dashboardResponse(CousellorId);
	     model.addAttribute("dashBoardInfo",dbObj);
		return "dashboard";
}
@GetMapping("/logout")
public String logout(HttpServletRequest request)
{
	HttpSession session = request.getSession(false);
	session.invalidate();
	return "redirect:/"; 
}


}
