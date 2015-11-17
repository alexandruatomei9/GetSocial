package info.getsocial.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import info.getsocial.domain.RestResponse;

@Aspect
@Component
public class ResponseWrappingAspect {

	@Pointcut("execution(* info.getsocial.rest.*.*(..))") // expression
	public void anyApiRequest() {
	}

	@AfterReturning(value = "anyApiRequest()", returning = "response")
	public RestResponse wrapResponse(Object response) {
		RestResponse resp = (RestResponse)response;
		resp.setStatus(200);
		return resp;
	}

	@AfterThrowing(value = "anyApiRequest()", throwing = "cause")
	public Object wrapException(Exception cause) {
		return cause;
	}

	public String getJsonString(Object mainJson) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(mainJson);
		} catch (JsonProcessingException e) {
			return "Error ";
		}
	}

}
