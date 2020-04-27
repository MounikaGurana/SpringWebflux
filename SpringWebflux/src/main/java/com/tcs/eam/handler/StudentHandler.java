package com.tcs.eam.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.tcs.eam.model.Student;
import com.tcs.eam.repository.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StudentHandler {

	@Autowired
	StudentRepository repo;
	
	
	public Mono<ServerResponse> createStudent(ServerRequest req)
	{
		Mono<Student> c = req.bodyToMono(Student.class).flatMap(c1 -> repo.save(c1));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(c,Student.class);
	}

	public Mono<ServerResponse> getStudentByName(ServerRequest req)
	{
		String n = req.pathVariable("name");
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<Student> c = repo.findByName(n);
		return c.flatMap(c1 -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(c1))).switchIfEmpty(notFound);
		
	}
	
	public Mono<ServerResponse> getStudents(ServerRequest req)
	{
		Flux<Student> s = repo.findAll();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(s, Student.class);
		
	}
	
}
