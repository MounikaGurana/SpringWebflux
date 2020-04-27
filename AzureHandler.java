package com.tcs.eam.router;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.tcs.eam.model.Customer;
import com.tcs.eam.model.ResponseDTO;

import reactor.core.publisher.Flux;

public class AzureHandler extends AzureSpringBootRequestHandler<Object, ResponseDTO> {
	
		@FunctionName("customerGet")
		public ResponseDTO get(@HttpTrigger(name = "getRequest", methods = {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> reqMsg,
				 @BindingName("name") String name, ExecutionContext e  )
		{
			return handleRequest(name, e);
		}
		
		@FunctionName("customerSave")
		public ResponseDTO save(@HttpTrigger(name = "saveRequest", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Customer>> req,
				ExecutionContext e  )
		{
			return handleRequest(req.getBody().get(), e);
		}
		
}
