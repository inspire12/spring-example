package sunghs.springexample.injection.service;

import org.springframework.stereotype.Service;

@Service
public class BService {

    private final AService aService;

    public BService(AService aService) {
        this.aService = aService;
    }
}
