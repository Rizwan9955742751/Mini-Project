package com.rizwan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rizwan.constant.AppConstant;
import com.rizwan.dto.LoginDto;
import com.rizwan.dto.QuoteApiResponseDto;
import com.rizwan.dto.RegistrationDto;
import com.rizwan.dto.ResetPwdDto;
import com.rizwan.dto.UserDto;
import com.rizwan.service.DashBoardService;
import com.rizwan.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DashBoardService dashboardService;

	@GetMapping("/register")
	public String loadRegisterForm(Model model) {

		Map<Integer, String> countriesMap = userService.getCountries();

		model.addAttribute("countries", countriesMap);
		RegistrationDto registerDto = new RegistrationDto();
		model.addAttribute("registerForm", registerDto);
		return "register";
	}

	@GetMapping("/states/{countryId}")
	@ResponseBody
	public Map<Integer, String> getState(@PathVariable Integer countryId) {

		return	 userService.getStates(countryId);
	}

	@GetMapping("/cities/{stateId}")
	@ResponseBody
	public Map<Integer, String> getcity(@PathVariable Integer stateId) {

		return  userService.getCities(stateId);

		
	}

	@PostMapping("/register")
	public String handleRegisterForm(RegistrationDto registerDto, Model model) {
		boolean status = userService.duplicateEmailCheck(registerDto.getEmail());
		if (status) {
			model.addAttribute("emsg", "Duplicate email found");
		} else {
			boolean saveUser = userService.saveUser(registerDto);
			if (saveUser) {
				model.addAttribute("smsg", "Registration successFul chcek your email");

			} else {
				model.addAttribute("emsg", "Registration Failed");
			}
		}
		model.addAttribute("registerForm", new RegistrationDto());
		model.addAttribute("countries", userService.getCountries());
		return "register";
	}

	@GetMapping("/")
	public String loginPage(Model model) {
		LoginDto loginDto = new LoginDto();
		model.addAttribute("loginForm", loginDto);
		return "login";
	}

	@PostMapping("/login")
	public String handleLoginPage(LoginDto logindto, Model model) {

		UserDto userDto = userService.login(logindto);
		if (userDto == null) {
			model.addAttribute("emsg", "Invalid Credential");
			model.addAttribute("loginForm", new LoginDto());
		} else {
			String updatePwd = userDto.getUpdatePwd();
			if ("Yes".equals(updatePwd)) {
				return "redirect:dashboard";
			} else {
				return "redirect:reset-pwd-page?email=" + userDto.getEmail();
			}
		}
		return "login";
	}

	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		QuoteApiResponseDto quouteApiResponseDto = dashboardService.getQuoute();
		model.addAttribute("quote", quouteApiResponseDto);
		return "dashboard";
	}

	@GetMapping("/reset-pwd-page")
	public String resetPwd(@RequestParam("email") String email, Model model) {
		ResetPwdDto resetPwd = new ResetPwdDto();
		resetPwd.setEmail(email);
model.addAttribute(AppConstant.RESET_PWD,resetPwd);
		return AppConstant.RESET_PWD;
	}

	@PostMapping("/resetPwd")
	public String handleResetPwd(ResetPwdDto resetPwdDto, Model model) {
		boolean resetPwd = userService.resetPwd(resetPwdDto);
		if (resetPwd) {
			return "redirect:dashboard";
		}
		return AppConstant.RESET_PWD;
	}
}
