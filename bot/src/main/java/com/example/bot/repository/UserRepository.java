package com.example.bot.repository;

import com.example.bot.entity.User;

public interface UserRepository {

	User findByUsername(String username);
}
