package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.Users;

public interface UserRepository extends JpaRepository<Users,Integer>{
	Optional<Users> findByUserMailIgnoreCase(String userMail);
}