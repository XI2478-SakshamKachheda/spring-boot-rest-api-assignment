package com.saksham.assignment3.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.saksham.assignment3.model.User;
import com.saksham.assignment3.model.UserPage;
import com.saksham.assignment3.model.UserSearchCriteria;
import com.saksham.assignment3.repository.UserCriteriaRepository;
import com.saksham.assignment3.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final UserCriteriaRepository userCriteriaRepository;

	public UserService(UserRepository userRepository, UserCriteriaRepository userCriteriaRepository) {
		super();
		this.userRepository = userRepository;
		this.userCriteriaRepository = userCriteriaRepository;
	}

	public Page<User> getUsers(UserPage userPage, UserSearchCriteria userSearchCriteria) {
		return userCriteriaRepository.findAllWithFilters(userPage, userSearchCriteria);
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}
}
