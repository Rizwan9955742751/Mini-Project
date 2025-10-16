package com.rizwan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rizwan.entity.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {
	@Query(value="select * from Enquiry  where counciler_Id=:counsellorId",nativeQuery = true)
	public List<Enquiry> getEnquiryByCounsellorId(Integer counsellorId);

}
