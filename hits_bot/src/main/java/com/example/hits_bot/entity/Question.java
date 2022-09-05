package com.example.hits_bot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@Data
@AllArgsConstructor
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String chat;

	private String text;
}
