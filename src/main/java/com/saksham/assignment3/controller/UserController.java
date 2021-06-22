package com.saksham.assignment3.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saksham.assignment3.model.User;
import com.saksham.assignment3.model.UserPage;
import com.saksham.assignment3.model.UserSearchCriteria;
import com.saksham.assignment3.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<Page<User>> getUsers(UserPage userPage, UserSearchCriteria userSearchCriteria) {
		return new ResponseEntity<>(userService.getUsers(userPage, userSearchCriteria), HttpStatus.OK);
	}
}
