package com.tcs.eam.function;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tcs.eam.model.Student;
import com.tcs.eam.repository.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StudentGet implements Function<ServerRequest, Mono<ServerResponse>>  {

	
	@Autowired
	StudentRepository repo;
	
	@Override
	public Mono<ServerResponse> apply(ServerRequest req) {
		String n = req.pathVariable("name");
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<Student> c = repo.findByName(n);
		return c.flatMap(c1 -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(c1))).switchIfEmpty(notFound);
	}	
	
}
