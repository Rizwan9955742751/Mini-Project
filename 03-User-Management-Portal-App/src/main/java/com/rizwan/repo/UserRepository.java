package com.rizwan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rizwan.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>   {
	public UserEntity findByEmail(String email);
	
	public UserEntity findByEmailAndPwd(String email, String pwd);

}
