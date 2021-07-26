package sunghs.springexample.injection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldInjectionService {

    @Autowired
    private ExampleService exampleService;

    public void getBusinessLogic() {
        exampleService.businessLogic();
    }
}
