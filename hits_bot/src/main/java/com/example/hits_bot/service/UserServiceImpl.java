package com.example.hits_bot.service;

import com.example.hits_bot.entity.User;
import com.example.hits_bot.repository.UserRepository;

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void changeRole(String chat, String role) {
		User user = userRepository.findByChat(chat);
		if (user == null) {
			userRepository.create(chat, role);
		}
		else {
			userRepository.setRole(chat, role);
		}
	}

	@Override
	public String getRole(String chat) {
		return userRepository.findByChat(chat).getRole();
	}
}
