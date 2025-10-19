package com.rizwan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rizwan.dto.LoginDto;
import com.rizwan.dto.RegistrationDto;
import com.rizwan.dto.ResetPwdDto;
import com.rizwan.dto.UserDto;
import com.rizwan.entities.CitiesEntity;
import com.rizwan.entities.CountryEntity;
import com.rizwan.entities.StateEntity;
import com.rizwan.entities.UserEntity;
import com.rizwan.repo.CityRepository;
import com.rizwan.repo.CountryRepository;
import com.rizwan.repo.StateRepository;
import com.rizwan.repo.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
  private EmailService emailService;

    Random random = new Random();
	@Override
	public Map<Integer, String> getCountries() {
		Map<Integer, String> countryMap = new HashMap<>();
		List<CountryEntity> countryList = countryRepo.findAll();
		
		countryList.forEach(c->
		
		countryMap.put(c.getCountryId(), c.getCountryName())	
		);
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		Map<Integer, String> stateMap = new HashMap<>();
		List<StateEntity> countryList = stateRepo.findByCountryId(countryId);
		countryList.forEach(s->
			stateMap.put(s.getStateId(), s.getStateName())
		);
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		Map<Integer, String> cityMap = new HashMap<>();
		List<CitiesEntity> stateList = cityRepo.findByStateId(stateId);
		stateList.forEach(c->
			cityMap.put(c.getCityId(), c.getCityName())
		);
		return cityMap;
	}

	@Override
	public boolean duplicateEmailCheck(String email) {
		UserEntity byEmail = userRepo.findByEmail(email);
		return byEmail!=null;
	}

	@Override
	public boolean saveUser(RegistrationDto regFormDTO) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(regFormDTO, userEntity);
		CountryEntity country = countryRepo.findById(regFormDTO.getCountryId()).orElse(null);
		userEntity.setCountry(country);
		
		StateEntity state = stateRepo.findById(regFormDTO.getStateId()).orElse(null);
		userEntity.setState(state);
		
		   CitiesEntity city = cityRepo.findById(regFormDTO.getCityId()).orElse(null);
		     userEntity.setCity(city);
		     String randomPwd = generateRandomPwd();
		 	userEntity.setPwd(randomPwd);
		       
			userEntity.setUpdatePwd("No");
		 UserEntity savedUser = userRepo.save(userEntity);
		
	if(null!=savedUser.getUserId())
	{
		String subject ="Your Account has created";
		String body="your Password to login"+randomPwd;
		String to=regFormDTO.getEmail();
		emailService.sendEmail(subject, body, to);
		return true;
	}
		
		return false;
	}

	@Override
	public UserDto login(LoginDto loginFormDTO) {

		UserEntity  userEntity = userRepo.findByEmailAndPwd(loginFormDTO.getEmail(), loginFormDTO.getPwd());
	if(userEntity!=null)
	{
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}
		
		return null;
	}

	@Override
	public boolean resetPwd(ResetPwdDto resetPwdDTO) {
    String email = resetPwdDTO.getEmail();
     UserEntity userEntity = userRepo.findByEmail(email);
     userEntity.setPwd(resetPwdDTO.getNewPwd());
     userEntity.setUpdatePwd("Yes");
     userRepo.save(userEntity);
		return true;
	}
	private String generateRandomPwd() {
	    String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";

	    String alphabets = upperCaseLetters + lowerCaseLetters;


	    StringBuilder generatedPwd = new StringBuilder();

	    for (int i = 0; i < 5; i++) {
	        int randomIndex = random.nextInt(alphabets.length());
	        generatedPwd.append(alphabets.charAt(randomIndex));
	    }

	    return generatedPwd.toString();
	}
}
