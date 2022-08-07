package sunghs.springexample.activeProfiles;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@ActiveProfiles("test")
@SpringBootTest(properties = "spring.profiles.active:test")
@Retention(RetentionPolicy.RUNTIME)
public @interface TestBasedSpringBootTest {
}
