package com.angularjs.angular1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angularjs.angular1.bean.UserBean;
import com.angularjs.angular1.dao.UserDao;
import com.angularjs.angular1.entity.UserEntity;
import com.angularjs.angular1.services.LoginSignupService;

@RestController
public class LoginSignupController {

	@Autowired
	LoginSignupService loginsignupservice;

	@Autowired
	UserDao userdao;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/signup")
	ResponseEntity<Object> signup(@RequestBody UserBean user) {

		if (userdao.findByEmailid(user.emailid) == null && userdao.findByPhno(user.phno) == null) {

			return ResponseEntity.status(HttpStatus.CREATED).body(loginsignupservice.signupservice(user));
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account Already exists with that email id/mobile number");

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/login")
	ResponseEntity<Object> login(@RequestBody UserBean user) {
		if (userdao.findByEmailid(user.emailid) != null) {
			if (userdao.findByEmailid(user.emailid).getPassword().equals(user.password)
					&& userdao.findByEmailid(user.emailid).getAccounttype().equals(user.accounttype)) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(userdao.findByEmailid(user.emailid));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Wrong Password or Account Type");
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account Does not exist");
	}

	@RequestMapping("/search/{emailid}")
	UserEntity search(@PathVariable("emailid") String emailid) {
		if (userdao.findByEmailid(emailid) != null)
			return userdao.findByEmailid(emailid);
		else {
			System.out.println(userdao.findByEmailid(emailid).getUserid());
			return userdao.findByEmailid(emailid);
		}

	}

}
