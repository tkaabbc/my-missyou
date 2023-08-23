package com.example.mymissyou.core;

import com.example.mymissyou.model.User;

import java.util.HashMap;
import java.util.Map;

public class LocalUser {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void set(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        LocalUser.threadLocal.set(map);
    }

    public static void clear() {
        LocalUser.threadLocal.remove();
    }

    public static User getUser() {
        Map<String, Object> map = LocalUser.threadLocal.get();
        User user = (User)map.get("user");
        return user;
    }
}
