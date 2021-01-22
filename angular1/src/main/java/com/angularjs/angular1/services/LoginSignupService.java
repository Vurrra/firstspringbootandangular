package com.angularjs.angular1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.angularjs.angular1.bean.UserBean;
import com.angularjs.angular1.dao.UserDao;
import com.angularjs.angular1.entity.UserEntity;

@Component
public class LoginSignupService {

	@Autowired
	UserDao userdao;

	@Bean
	@Lazy
	public UserEntity signupservice(UserBean user) {

		UserEntity userentity = new UserEntity();

		userentity.setUsername(user.username);
		userentity.setEmailid(user.emailid);
		userentity.setPhno(user.phno);
		userentity.setAccounttype(user.accounttype);
		userentity.setPassword(user.password);

		return userdao.saveAndFlush(userentity);
	}

}
