package com.techradar.polls.techradar_polls.auth;

import com.techradar.polls.techradar_polls.dto.AuthenticationInfo;
import com.techradar.polls.techradar_polls.exeptions.UnauthorisedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;


public class AuthenticationInterceptor implements HandlerInterceptor {
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = resolveToken(request);
        AuthenticationInfo user = resolveUser(token);
        if (user.getRole() != "Specialist") {
            throw new UnauthorisedException();
        }
        request.setAttribute("X-Techradar-UserID", user.getUserId());
        return true;
    }

    private AuthenticationInfo resolveUser(String token) {
        // instead of parsing token, return userID straight away
        return new AuthenticationInfo(1, "Specialist");
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}