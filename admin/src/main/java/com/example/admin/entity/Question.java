package com.example.admin.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "questions")
@Data
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String chat;

	private String text;
}
