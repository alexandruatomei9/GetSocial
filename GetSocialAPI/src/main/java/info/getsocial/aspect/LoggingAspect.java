package info.getsocial.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private final static Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

	@Around("execution(* *(..)) && @annotation(info.getsocial.util.Loggable)")
	public Object around(ProceedingJoinPoint point) {
		long start = System.currentTimeMillis();
		Object result = null;
		try {
			result = point.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		LOGGER.info(String.format("#%s(%s) duration %s[ms]",
				MethodSignature.class.cast(point.getSignature()).getMethod().getName(), point.getArgs(),
				System.currentTimeMillis() - start));

		return result;
	}
}
