package com.angularjs.angular1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity
@SequenceGenerator(name = "userid", initialValue = 110, allocationSize = 100)
public class UserEntity {

	private String username;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userid")
	private long userid;
	@Column(name = "emailid", unique = true)
	private String emailid;
	@Column(name = "phno", unique = true)
	private String phno;
	private String password;
	private String accounttype;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emialid) {
		this.emailid = emialid;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	@Override
	public String toString() {
		return "UserEntity [username=" + username + ", userid=" + userid + ", emailid=" + emailid + ", phno=" + phno
				+ ", password=" + password + ", accounttype=" + accounttype + "]";
	}

}
