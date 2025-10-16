package com.rizwan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rizwan.entity.Counsellor;

public interface CounsellorRepositoey extends JpaRepository<Counsellor, Integer> {
	
	Counsellor findByEmailId(String emailId);

	Counsellor findByEmailIdAndPasswd(String emailId, String passwd);


}
