package cn.codingjc.peekaboo.application.config.security;


import cn.codingjc.peekaboo.application.util.JwtUtils;
import cn.codingjc.peekaboo.domain.exception.BusinessException;
import cn.codingjc.peekaboo.domain.exception.ErrorCodeEnum;
import cn.codingjc.peekaboo.domain.repository.UserRepository;
import cn.codingjc.peekaboo.infrastructure.persistence.po.SysUserPO;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static cn.codingjc.peekaboo.domain.common.constant.CommonConstant.*;

@Slf4j
@Component
public class JwtAuthticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String token = req.getHeader(TOKEN_HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_HEAD)) {
            String authToken = token.substring(TOKEN_HEAD.length());
            Claims claims = JwtUtils.verifyJwt(authToken);
            if (claims == null) {
                throw new BusinessException(ErrorCodeEnum.TOKEN_INVALID);
            }
            String username = claims.get(USERNAME, String.class);
            String redisToken = userRepository.getTokenByUserName(username);
            if (StringUtils.isEmpty(redisToken)) {
                throw new BusinessException(ErrorCodeEnum.TOKEN_INVALID);
            }
            // 刷新token
            userRepository.reflush(username, redisToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // token有效，存入securityContext
                SysUserPO sysUserPO = userRepository.getUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(sysUserPO, null, sysUserPO.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(req, resp);
        SecurityContextHolder.clearContext();
    }
}
