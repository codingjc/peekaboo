package cn.codingjc.peekaboo.application.service.login;

import cn.codingjc.peekaboo.application.util.JwtUtils;
import cn.codingjc.peekaboo.application.util.MessageUtils;
import cn.codingjc.peekaboo.domain.domain.dto.LoginRequestDTO;
import cn.codingjc.peekaboo.domain.exception.BusinessException;
import cn.codingjc.peekaboo.domain.exception.ErrorCodeEnum;
import cn.codingjc.peekaboo.infrastructure.persistence.po.SysUserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class UserLogin {

    @Autowired
    protected AuthenticationManager authenticationManager;

    public String login(LoginRequestDTO loginRequestDTO){
        SysUserPO sysUserPO = doLogin(loginRequestDTO);
        if (sysUserPO == null) {
            throw new BusinessException(MessageUtils.getMessage("user.not.exist"));
        }
        try {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(sysUserPO, null, sysUserPO.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new BusinessException(ErrorCodeEnum.NOT_AUTHTICATION);
        }
        return JwtUtils.generateToken(sysUserPO.getUsername());
    }


    public abstract SysUserPO doLogin(LoginRequestDTO loginRequestDTO);

}
