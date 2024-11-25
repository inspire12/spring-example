package sunghs.springexample.scancustomannotation.scanner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.event.EventListener;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import sunghs.springexample.scancustomannotation.annotation.CustomAnnotation;
import sunghs.springexample.scancustomannotation.classes.SomeInterface;

import java.util.List;

@Slf4j
@Component
public class CustomAnnotationScanner {

    /**
     * application ready event 발생 시 컴포넌트들을 스캔한다.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void scan() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(CustomAnnotation.class));

        List<String> list = provider.findCandidateComponents("sunghs.springexample.scancustomannotation")
                .stream()
                .filter(this::isSomeInterfaceImplement)
                .map(BeanDefinition::getBeanClassName)
                .toList();

        // 결과
        list.forEach(className -> log.info("result class name : {}", className));
    }

    /**
     * SomeInterface 를 구현했는지 확인 후 반환한다.
     */
    private boolean isSomeInterfaceImplement(BeanDefinition beanDefinition) {
        try {
            Class<?> type = Class.forName(beanDefinition.getBeanClassName());
            return SomeInterface.class.isAssignableFrom(type);
        } catch (ClassNotFoundException e) {
            log.error("isSomeInterfaceImplement error cause : ", e);
            return false;
        }
    }
}
