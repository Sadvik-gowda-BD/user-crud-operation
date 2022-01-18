package com.bank.user.service;

import javax.validation.Valid;

import com.bank.user.dto.UserRequestDto;
import com.bank.user.entity.User;

public interface UserService {

	boolean registerUser(@Valid UserRequestDto userRequestDto);

	void deleteUser(Integer id);

	User getUser(Integer id);

	void updateUser(Integer id, @Valid UserRequestDto userRequestDto);

}
