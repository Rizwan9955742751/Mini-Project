package com.rizwan.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rizwan.dto.DashBoardResponse;
import com.rizwan.entity.Counsellor;
import com.rizwan.entity.Enquiry;
import com.rizwan.repo.CounsellorRepositoey;
import com.rizwan.repo.EnquiryRepository;
@Service
public class CounsellorServiceImpl implements CouncilerService {
	@Autowired
	private CounsellorRepositoey counsellorRepo;
     @Autowired
     private EnquiryRepository enquiryRepo;
     
     @Override
 	public Counsellor findByEmail(String email) {
 		return 	 counsellorRepo.findByEmailId(email);
 		
 	}
	@Override
	public boolean register(Counsellor counsellor) {
	Counsellor saveCounsellor = counsellorRepo.save(counsellor);
	if(null!=saveCounsellor.getCouncilerId())
	{
		return true;
	}
		return false;
	}

	@Override
	public Counsellor login(String email, String passwd) {
		return counsellorRepo.findByEmailIdAndPasswd(email, passwd);
	
	}

	@Override
	public DashBoardResponse dashboardResponse(Integer counsellorId) {
		DashBoardResponse dasResponse = new DashBoardResponse();
		List<Enquiry> enqList = enquiryRepo.getEnquiryByCounsellorId(counsellorId);
		int totalenq = enqList.size();
		int enrolledEnq = enqList.stream().filter(e->e.getEnq_status().equals("Enrolled"))
		.collect(Collectors.toList())
		.size();
		int lostEnq = enqList.stream().filter(e->e.getEnq_status().equals("Lost"))
				.collect(Collectors.toList())
				.size();
		
		int openEnq = enqList.stream().filter(e->e.getEnq_status().equals("Open"))
				.collect(Collectors.toList())
				.size();
		dasResponse.setTotal_enq(totalenq);
		dasResponse.setEnrolled_enq(enrolledEnq);
		dasResponse.setOpen_enq(openEnq);
		dasResponse.setLost_enq(lostEnq);
		return dasResponse;
	}

	

}
