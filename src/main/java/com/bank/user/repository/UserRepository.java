package com.bank.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
