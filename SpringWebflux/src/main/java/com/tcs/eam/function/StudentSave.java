package com.tcs.eam.function;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.tcs.eam.model.Student;
import com.tcs.eam.repository.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StudentSave implements Function<ServerRequest, Mono<ServerResponse>> {
	
	@Autowired
	StudentRepository repo;
	
	@Override
	public Mono<ServerResponse> apply(ServerRequest req) {
		Mono<Student> s = req.bodyToMono(Student.class).flatMap(c1 -> repo.save(c1));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(s,Student.class);
	}	
	
}
