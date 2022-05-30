package sunghs.springexample.argumentresolver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sunghs.springexample.argumentresolver.model.UserInfo;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public UserInfo test(UserInfo userInfo) {
        return userInfo;
    }
}
