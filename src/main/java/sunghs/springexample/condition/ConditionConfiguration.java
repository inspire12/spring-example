package sunghs.springexample.condition;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import sunghs.springexample.model.Student;

@Configuration
public class ConditionConfiguration implements InitializingBean {

    @Bean
    @Conditional(LocalPropertyCondition.class)
    public Student localStudent() {
        return new Student(1, "local");
    }

    @Bean
    @Conditional(CloudPropertyCondition.class)
    public Student cloudStudent() {
        return new Student(2, "cloud");
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(localStudent(), "local student is null");
        Assert.notNull(cloudStudent(), "cloud student is null");
    }
}
