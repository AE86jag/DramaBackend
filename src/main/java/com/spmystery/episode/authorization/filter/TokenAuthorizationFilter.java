package com.spmystery.episode.authorization.filter;

import com.spmystery.episode.user.entity.Token;
import com.spmystery.episode.user.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization) || !authorization.startsWith("Token ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String[] split = authorization.split(" ");
        String tokenId = split[1];

        Token token = tokenMapper.findById(tokenId);

        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }

}
