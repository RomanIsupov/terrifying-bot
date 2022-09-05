package com.example.hits_bot.service;

public interface UserService {

	void changeRole(String chat, String role);

	String getRole(String chat);
}
