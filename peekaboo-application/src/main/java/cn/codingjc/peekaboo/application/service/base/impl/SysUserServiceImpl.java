package cn.codingjc.peekaboo.application.service.base.impl;

import cn.codingjc.peekaboo.application.service.base.SysUserService;
import cn.codingjc.peekaboo.application.service.base.UserService;
import cn.codingjc.peekaboo.application.util.JwtUtils;
import cn.codingjc.peekaboo.application.util.MessageUtils;
import cn.codingjc.peekaboo.application.util.RedisUtils;
import cn.codingjc.peekaboo.domain.common.constant.CommonConstant;
import cn.codingjc.peekaboo.domain.domain.dto.LoginRequestDTO;
import cn.codingjc.peekaboo.domain.exception.BusinessException;
import cn.codingjc.peekaboo.domain.exception.ErrorCodeEnum;
import cn.codingjc.peekaboo.infrastructure.persistence.po.SysUserPO;
import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final UserService service;

    private final AuthenticationManager authenticationManager;

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        if (true) {
            // 验证码校验
            validateVertifyCode(loginRequestDTO);
        }
        Authentication authenticate = null;
        try {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
            authenticate = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new BusinessException(ErrorCodeEnum.NOT_AUTHTICATION);
        }
        SysUserPO sysUserPO = (SysUserPO) authenticate.getPrincipal();
        return JwtUtils.generateToken(sysUserPO.getUsername());
    }

    public void validateVertifyCode(LoginRequestDTO loginRequestDTO){
        String uuid = loginRequestDTO.getUuid();
        String veritfyCode = loginRequestDTO.getVeritfyCode();
        Object redisCode = RedisUtils.get(CommonConstant.REDIS_VERTIFY_CODE_KEY + uuid);
        if (ObjectUtil.isEmpty(redisCode)) {
            throw new BusinessException(MessageUtils.getMessage("vertify.code.expired"));
        }
        if (!veritfyCode.equalsIgnoreCase(redisCode.toString())) {
            throw new BusinessException(MessageUtils.getMessage("vertify.code.error"));
        }
        // 删除验证码
        RedisUtils.del(CommonConstant.REDIS_VERTIFY_CODE_KEY + uuid);
    }
}
