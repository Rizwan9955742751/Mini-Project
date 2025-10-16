package com.rizwan.service;

import com.rizwan.dto.DashBoardResponse;
import com.rizwan.entity.Counsellor;

public interface CouncilerService {
	public Counsellor findByEmail(String email);
	public boolean register (Counsellor counsellor);
	public Counsellor login (String email, String passwd);
	public DashBoardResponse dashboardResponse( Integer counsellorId);
}
