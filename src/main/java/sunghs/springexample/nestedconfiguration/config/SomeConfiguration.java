package sunghs.springexample.nestedconfiguration.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import sunghs.springexample.nestedconfiguration.SomeProperty;

@Slf4j
@Configuration
public class SomeConfiguration implements InitializingBean {

    @Bean
    @ConfigurationProperties("some")
    public SomeProperty someProperty() {
        return new SomeProperty();
    }

    @Override
    public void afterPropertiesSet() {
        SomeProperty someProperty = someProperty();
        Assert.notNull(someProperty, "someProperty must not be null");
        log.info("someProperty : {}", someProperty);
    }
}
