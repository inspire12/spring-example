package sunghs.springexample.beaninjection;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConstructorInjectionService {

    private final ExampleService exampleService;

    /*
    public ConstructorInjectionService(final ExampleService exampleService) {
        this.exampleService = exampleService;
    }
    */

    public void getBusinessLogic() {
        exampleService.businessLogic();
    }
}
