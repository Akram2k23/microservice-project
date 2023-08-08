package com.tap.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tap.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
