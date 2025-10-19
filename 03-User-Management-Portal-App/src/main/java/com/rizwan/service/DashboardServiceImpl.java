 package com.rizwan.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rizwan.dto.QuoteApiResponseDto;
@Service
public class DashboardServiceImpl implements DashBoardService {
	private String quoteApiURL = "https://dummyjson.com/quotes/random";
	@Override
	public QuoteApiResponseDto  getQuoute() {
	RestTemplate rt = new RestTemplate();
	ResponseEntity<QuoteApiResponseDto> entity = rt.getForEntity(quoteApiURL, QuoteApiResponseDto.class);
	return	entity.getBody();
	
	
	}

}
