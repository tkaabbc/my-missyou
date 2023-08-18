package com.example.mymissyou.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JwtToken {
    private static String jwtKey;

    private static Integer expiredTimeIn;

    @Value("${missyou.security.jwt-key}")
    public void setJwtKey(String jwtKey) {
        JwtToken.jwtKey = jwtKey;
    }

    @Value("${missyou.security.token-expired-in}")
    public void setExpiredTimeIn(Integer expiredTimeIn) {
        JwtToken.expiredTimeIn = expiredTimeIn;
    }

    public static String createToken(String username) {
        return getToken(username);
    }

    public static String getToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
        Map<String, Date> issueDate = calculateExpiredIssues();
        String token = JWT.create()
                .withClaim("username", username)
                .withIssuedAt(issueDate.get("now"))
                .withExpiresAt(issueDate.get("expiredTime"))
                .sign(algorithm);
        System.out.println(token);
        return token;
    }

    public static Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (JWTVerificationException exception){
            return false;
        }

        return true;
    }

    private static Map<String, Date> calculateExpiredIssues() {
        Map<String, Date> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.MILLISECOND, JwtToken.expiredTimeIn);

        // TODO can I use Enum for "now"?
        map.put("now", now);
        map.put("expiredTime", calendar.getTime());
        return map;
    }
}
