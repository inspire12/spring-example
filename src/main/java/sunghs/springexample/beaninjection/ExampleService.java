package sunghs.springexample.beaninjection;

import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    public String businessLogic() {
        return "OK";
    }
}
