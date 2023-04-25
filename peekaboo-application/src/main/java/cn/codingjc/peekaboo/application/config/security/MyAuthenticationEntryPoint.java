package cn.codingjc.peekaboo.application.config.security;

import cn.codingjc.peekaboo.domain.common.enums.ResultEnum;
import cn.codingjc.peekaboo.domain.domain.vo.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().println(new ObjectMapper().writeValueAsString(ResultVO.fail(ResultEnum.AUTHTICATION_FAIL.getStatus())));
        response.getWriter().flush();
    }
}
