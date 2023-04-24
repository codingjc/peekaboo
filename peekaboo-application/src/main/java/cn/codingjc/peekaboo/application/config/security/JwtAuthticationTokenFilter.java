package cn.codingjc.peekaboo.application.config.security;


import cn.codingjc.peekaboo.application.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthticationTokenFilter extends OncePerRequestFilter {

    private final String tokenHeader = "Authtication";
    private final String tokenHead = "Bear ";

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String token = req.getHeader(tokenHeader);
        if (StringUtils.isNotEmpty(token) && token.startsWith(tokenHead)) {
            String authToken = token.substring(this.tokenHead.length());
            Claims claims = JwtUtils.verifyJwt(authToken);
            if (claims == null) {
                throw new RuntimeException("token异常");
            }
            String username = claims.get("username", String.class);
        }
    }
}
