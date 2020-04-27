package com.tcs.eam.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tcs.eam.function.StudentGet;
import com.tcs.eam.function.StudentSave;
import com.tcs.eam.handler.StudentHandler;
import com.tcs.eam.repository.StudentRepository;

@Configuration
public class StudentRouter {
	
	@Autowired
	StudentRepository repo;
	
	@Autowired
	StudentHandler h;
	
	@Bean
	public RouterFunction<ServerResponse> route()
	{
		
		return RouterFunctions.route(RequestPredicates.POST("/saveStudent").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), h::createStudent)
				.andRoute(RequestPredicates.GET("/getStudent/{name}").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), h::getStudentByName)
				.andRoute(RequestPredicates.GET("/getStudents").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), h::getStudents)
				;
	}
	
	@Bean
	public RouterFunction<ServerResponse> route(StudentSave s)
	{
		
		return RouterFunctions.route(RequestPredicates.POST("/studentSave").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), s::apply);
	}
	
	@Bean
	public RouterFunction<ServerResponse> route(StudentGet s)
	{
		
		return RouterFunctions.route(RequestPredicates.GET("/studentGET/{name}").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), s::apply);
	}
	
}
