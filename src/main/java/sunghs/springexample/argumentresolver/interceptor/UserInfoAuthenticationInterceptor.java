package sunghs.springexample.argumentresolver.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import sunghs.springexample.argumentresolver.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class UserInfoAuthenticationInterceptor implements HandlerInterceptor {

    public static final String USER_INFO_KEY = "userInfo";

    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("authorization");
        UserInfo userInfo;

        try {
            userInfo = StringUtils.isNotEmpty(authorization)
                ? objectMapper.readValue(authorization, UserInfo.class)
                : new UserInfo();
        } catch (Exception e) {
            log.error("authorization read value error", e);
            userInfo = new UserInfo();
        }

        request.setAttribute(USER_INFO_KEY, userInfo);
        return true;
    }
}
