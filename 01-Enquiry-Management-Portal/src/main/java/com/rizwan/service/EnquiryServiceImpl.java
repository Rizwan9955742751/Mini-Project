package com.rizwan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.rizwan.dto.ViewFilterRequest;
import com.rizwan.entity.Counsellor;
import com.rizwan.entity.Enquiry;
import com.rizwan.repo.CounsellorRepositoey;
import com.rizwan.repo.EnquiryRepository;

import io.micrometer.common.util.StringUtils;
@Service
public class EnquiryServiceImpl implements EnquiryService {
	@Autowired
	private EnquiryRepository enquiryRepo;
	@Autowired
	private CounsellorRepositoey counsellorRepo;
	@Override
	public boolean addEnuiry(Enquiry enquiry, Integer counsellorId) throws Exception {
		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElse(null);
		if(counsellor==null)
		{
			throw new Exception("Counsellor not found ");
		}
		enquiry.setCounciler(counsellor);
		
		Enquiry save = enquiryRepo.save(enquiry);
		if(save.getEnquiryId()!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<Enquiry> getAllEnquiry(Integer counsellorId) {
		
		return enquiryRepo.getEnquiryByCounsellorId(counsellorId);
	}
	@Override
	public Enquiry getEnquiryById(Integer enqId) {
	return	enquiryRepo.findById(enqId).orElse(null);
		
	}

	@Override
	public List<Enquiry> getEnquiryWithFilter(ViewFilterRequest request, Integer counsellorId) {
		Enquiry enq = new Enquiry();
		if(StringUtils.isNotEmpty(request.getClassMode()))
		{
			enq.setClass_Mode(request.getClassMode());
		}
		if(StringUtils.isNotEmpty(request.getCousrseName()))
		{
			enq.setCourseName(request.getCousrseName());
		}
		if(StringUtils.isNotEmpty(request.getEnqStatus()))
		{
			enq.setEnq_status(request.getEnqStatus());
		}
		Counsellor c = counsellorRepo.findById(counsellorId).orElse(null);
		enq.setCounciler(c);
		Example<Enquiry> example = Example.of(enq);
		List<Enquiry> enqlist = enquiryRepo.findAll(example);
		return enqlist;
	}

	

}
