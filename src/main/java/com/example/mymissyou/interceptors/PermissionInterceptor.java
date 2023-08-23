package com.example.mymissyou.interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.example.mymissyou.core.LocalUser;
import com.example.mymissyou.exception.http.UnAuthenticatedException;
import com.example.mymissyou.model.User;
import com.example.mymissyou.service.UserService;
import com.example.mymissyou.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class PermissionInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserService userService;

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

        String tokens[] = bearerToken.split(" ");
        if (!(tokens.length == 2)) {
            throw new UnAuthenticatedException(10004);
        }
        String token = tokens[1];
        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaims(token);
        Map<String, Claim> map = optionalMap
                .orElseThrow(() -> new UnAuthenticatedException(10004));

        this.setToThreadLocal(map);

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

    private void setToThreadLocal(Map<String, Claim> map) {
        Long uid = map.get("uid").asLong();
        User user = this.userService.getUserById(uid);
        LocalUser.set(user);
    }
}
