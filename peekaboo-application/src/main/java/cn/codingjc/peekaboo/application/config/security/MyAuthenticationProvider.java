package cn.codingjc.peekaboo.application.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @date: 2023/4/23 21:29
 * @auther: codingjc
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider {

   @Override
   protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
      HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
      String vertifyCodeWeb = request.getParameter("vertify_code");
      String vertifyCodeSession = (String) request.getSession().getAttribute("vertify_code");

//      if (StringUtils.isEmpty(vertifyCodeWeb) ||
//              StringUtils.isEmpty(VerityCodeConfig.code) ||
//              !vertifyCodeWeb.equals(VerityCodeConfig.code) ) {
//         throw new AuthenticationServiceException("验证码错误！");
//      }
      super.additionalAuthenticationChecks(userDetails, authentication);
   }
}
