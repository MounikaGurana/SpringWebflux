package com.tcs.eam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.eam.model.Student;
import com.tcs.eam.repository.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentRepository repo;
	
	@PostMapping
	public Mono<Student> save(@RequestBody Student s)
	{
		return repo.save(s);
	}
	
	@GetMapping
	public Flux<Student> getStudent()
	{
		return repo.findAll();
	}
}
