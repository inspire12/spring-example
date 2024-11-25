package sunghs.springexample.scancustomannotation.classes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sunghs.springexample.scancustomannotation.annotation.CustomAnnotation;

@CustomAnnotation
@Slf4j
@Service
@RequiredArgsConstructor
public class NotImplementationService {

}
