package com.spmystery.episode.util;

import com.spmystery.episode.user.entity.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtil {

    public static String currentUserId() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context == null) {
            throw new RuntimeException("系统异常, 未登录");
        }

        Authentication authentication = context.getAuthentication();
        if (authentication instanceof Token) {
            Token token = (Token) authentication;
            return token.getUser().getId();
        }

        throw new RuntimeException("用户未登录");
    }

    public static String currentUsername() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context == null) {
            throw new RuntimeException("系统异常, 未登录");
        }

        Authentication authentication = context.getAuthentication();
        if (authentication instanceof Token) {
            Token token = (Token) authentication;
            return token.getUser().getNickname();
        }

        throw new RuntimeException("用户未登录");
    }

    public static boolean isLogin() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context != null && context.getAuthentication() instanceof Token;
    }
}
