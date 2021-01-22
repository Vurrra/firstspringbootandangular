package com.angularjs.angular1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angularjs.angular1.entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Integer> {

	UserEntity findByEmailid(String emailid);

	UserEntity findByPhno(String phno);

}
