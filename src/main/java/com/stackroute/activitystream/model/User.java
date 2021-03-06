package com.stackroute.activitystream.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table
public class User
{
	@Id
	@NotNull
	@Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$", message = "Email ID is not Proper Format")
	private String email_id;
	
	@NotNull(message="Password cannot be null")
	@Size(min=8, max=15)
	private String password;
	
	@NotNull(message="User Name cannot be null")
	@Size(min=8, max=30)
	private String username;
	
	@NotNull(message="Mobile Number Can't be null")
	@Pattern(regexp = "[789]\\d{9}", message = "Mobile Number is not Proper.")
	private String mobile;
	
	public User()
	{
		
	}
	
	public User(String email_id, String password, String username, String mobile) {
		super();
		this.email_id = email_id;
		this.password = password;
		this.username = username;
		this.mobile = mobile;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
	
}
