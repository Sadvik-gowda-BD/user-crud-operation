package com.bank.user.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRequestDto {

	@NotEmpty(message = "User name should not be empty")
	private String name;
	@NotEmpty(message = "Email should not be empty")
	private String email;
	@NotNull(message = "User age should not be empty")
	@Min(value = 15, message = "Minimum age should be 15 for register")
	private int age;
	@NotEmpty(message = "Password should not be empty")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
