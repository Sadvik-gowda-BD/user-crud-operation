package com.bank.user.serviceimpl;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.user.dto.UserRequestDto;
import com.bank.user.entity.User;
import com.bank.user.repository.UserRepository;
import com.bank.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public boolean registerUser(@Valid UserRequestDto userRequestDto) {
		User user = new User();
		BeanUtils.copyProperties(userRequestDto, user);
		user.setPassword(encoder.encode(userRequestDto.getPassword()));
		User savedUser = userRepo.save(user);

		if (savedUser == null)
			return false;
		return true;

	}

	@Override
	public void deleteUser(Integer id) {
		userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		userRepo.deleteById(id);

	}

	@Override
	public User getUser(Integer id) {
		return userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Override
	public void updateUser(Integer id, @Valid UserRequestDto userRequestDto) {
		userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		User user = new User();
		BeanUtils.copyProperties(userRequestDto, user);
		user.setPassword(encoder.encode(userRequestDto.getPassword()));
		user.setUserId(id);
		userRepo.save(user);

	}

}
