package com.example.mymissyou.interceptors;

import com.example.mymissyou.exception.http.UnAuthenticatedException;
import com.example.mymissyou.util.JwtToken;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class PermissionInterceptor extends HandlerInterceptorAdapter {
    public PermissionInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (getWhitelist(handler)) {
            return true;
        }

        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.isEmpty(bearerToken)){
            throw new UnAuthenticatedException(10004);
        }

        if(!bearerToken.startsWith("Bearer")){
            throw new UnAuthenticatedException(10004);
        }

        if (bearerToken != null) {
            String token = bearerToken.split(" ")[1];
            return JwtToken.verifyToken(token);
        }

        return true;
    }

    private boolean getWhitelist(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Whitelist whitelist = handlerMethod.getMethod().getAnnotation(Whitelist.class);
            System.out.println("whitelist");
            System.out.println(whitelist);
            if (whitelist == null) {
                return false;
            }
            return whitelist.value();
        } else {
            throw new RuntimeException("handler not instanceof HandlerMethod");
        }
    }
}
