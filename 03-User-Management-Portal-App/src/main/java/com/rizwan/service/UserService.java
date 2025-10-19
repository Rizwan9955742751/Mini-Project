package com.rizwan.service;

import java.util.Map;

import com.rizwan.dto.LoginDto;
import com.rizwan.dto.RegistrationDto;
import com.rizwan.dto.ResetPwdDto;
import com.rizwan.dto.UserDto;

public interface UserService {
	public Map<Integer, String> getCountries();
	public Map<Integer, String> getStates(Integer countryId);
	public Map<Integer, String> getCities(Integer stateId);
	public boolean duplicateEmailCheck(String email);
	public boolean saveUser(RegistrationDto regFormDTO);
	public UserDto login(LoginDto loginFormDTO);
	public boolean resetPwd(ResetPwdDto resetPwdDTO);

}
