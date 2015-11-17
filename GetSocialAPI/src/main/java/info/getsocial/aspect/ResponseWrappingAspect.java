package info.getsocial.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import info.getsocial.domain.RestResponse;

@Aspect
@Component
public class ResponseWrappingAspect {

	@Pointcut("execution(* info.getsocial.rest.*.*(..))") // expression
	public void anyApiRequest() {
	}

	@Around("anyApiRequest()")
	public Object unifyResponse(ProceedingJoinPoint pjp) throws Throwable {
		Object controllerResult = pjp.proceed();
		RestResponse result = new RestResponse(0, controllerResult);
		return result;
	}

	@AfterThrowing(value = "anyApiRequest()", throwing = "cause")
	public Object wrapException(Exception cause) {
		return cause;
	}

}
