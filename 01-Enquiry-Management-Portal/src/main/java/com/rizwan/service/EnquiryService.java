package com.rizwan.service;

import java.util.List;

import com.rizwan.dto.ViewFilterRequest;
import com.rizwan.entity.Enquiry;

public interface EnquiryService {
	
	public boolean addEnuiry( Enquiry enquiry, Integer counsellorId)throws Exception;
	public List<Enquiry> getAllEnquiry(Integer counsellorId);
	
	public List<Enquiry>getEnquiryWithFilter(ViewFilterRequest request, Integer counsellorId);
	public Enquiry getEnquiryById(Integer enqId);

}
