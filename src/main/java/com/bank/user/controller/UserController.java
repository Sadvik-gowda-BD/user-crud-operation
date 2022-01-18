package com.bank.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.user.dto.UserRequestDto;
import com.bank.user.entity.User;
import com.bank.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
		if (userService.registerUser(userRequestDto)) {
			return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("User registered failed", HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id) {

		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<String> getUser(@PathVariable Integer id, @Valid @RequestBody UserRequestDto userRequestDto) {
		userService.updateUser(id,userRequestDto);
		return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
	}

}
