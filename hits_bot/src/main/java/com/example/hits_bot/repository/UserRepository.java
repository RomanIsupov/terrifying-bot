package com.example.hits_bot.repository;

import com.example.hits_bot.entity.User;

public interface UserRepository {

	User findByChat(String chat);

	void create(String chat, String role);

	void setRole(String chat, String role);
}
