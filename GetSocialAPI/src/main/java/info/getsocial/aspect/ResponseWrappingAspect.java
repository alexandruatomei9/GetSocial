package info.getsocial.aspect;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import info.getsocial.domain.RestResponse;

@Aspect
@Component
public class ResponseWrappingAspect {

//	@Pointcut("execution(* info.getsocial.rest.*.*(..))") // expression
//	public void anyApiRequest() {
//	}
//
//	@AfterReturning(value = "anyApiRequest()", returning = "response")
//	public Object wrapResponse(Object response) {
//		Response origResponse = (Response)response;
//		
//		return  response;
//	}
//
//	@AfterThrowing(value = "anyApiRequest()", throwing = "cause")
//	public Object wrapException(Exception cause) {
//		return cause;
//	}

}
