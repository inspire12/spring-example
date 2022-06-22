package sunghs.springexample.beanpostprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

@Slf4j
public class ExampleComponent implements InitializingBean {

    public void initMethod() {
        log.info("ExampleComponent InitMethod");
    }

    @PostConstruct
    public void postConstruct() {
        log.info("ExampleComponent PostConstruct");
    }

    @Override
    public void afterPropertiesSet() {
        log.info("ExampleComponent AfterPropertiesSet");
    }
}
