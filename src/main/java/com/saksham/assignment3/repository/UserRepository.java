package com.saksham.assignment3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.saksham.assignment3.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
