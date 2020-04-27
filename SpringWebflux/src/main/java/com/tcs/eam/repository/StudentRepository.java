package com.tcs.eam.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.tcs.eam.model.Student;

import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveMongoRepository<Student, Integer> {

	Mono<Student> findByName(String n);

}
