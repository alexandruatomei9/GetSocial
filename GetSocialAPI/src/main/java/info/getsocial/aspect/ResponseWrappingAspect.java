package info.getsocial.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ResponseWrappingAspect {

	@Pointcut("execution(* info.getsocial.rest.*.*(..))") // expression 
	public void anyApiRequest() {
	}
    
	@AfterReturning(value = "anyApiRequest()", returning = "response")
	public Object wrapResponse(Object response) {
		return response;
	}

	@AfterThrowing(value = "anyApiRequest()", throwing = "cause")
	public Object wrapException(Exception cause) {
		return cause;
	}
	
}
