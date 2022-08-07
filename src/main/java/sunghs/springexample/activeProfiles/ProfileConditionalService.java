package sunghs.springexample.activeProfiles;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileConditionalService {

    @Value("${spring.profiles.active:unknown}")
    private String profile;

    public void doSomething() {
        log.info("current profile : {}", profile);
    }
}
