package com.example.admin.repository;

import com.example.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByChat(String chat);
}
