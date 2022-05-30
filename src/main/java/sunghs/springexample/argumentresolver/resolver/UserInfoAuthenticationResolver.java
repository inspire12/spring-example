package sunghs.springexample.argumentresolver.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import sunghs.springexample.argumentresolver.interceptor.UserInfoAuthenticationInterceptor;
import sunghs.springexample.argumentresolver.model.UserInfo;

import javax.servlet.http.HttpServletRequest;

public class UserInfoAuthenticationResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return ((HttpServletRequest) webRequest.getNativeRequest()).getAttribute(UserInfoAuthenticationInterceptor.USER_INFO_KEY);
    }
}
