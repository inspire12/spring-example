package sunghs.springexample.injection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetterInjectionService {

    private ExampleService exampleService;

    @Autowired
    public void setExampleService(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    public void getBusinessLogic() {
        exampleService.businessLogic();
    }
}
