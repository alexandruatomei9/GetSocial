package info.getsocial.annotations;

import java.lang.annotation.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import info.getsocial.Application;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ContextConfiguration(classes = { Application.class })
@TestPropertySource(locations = "classpath:test.properties")
public @interface DefaultTestAnnotations {
	
}
