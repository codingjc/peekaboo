package cn.codingjc.peekaboo.application.service.login;

import cn.codingjc.peekaboo.application.util.MessageUtils;
import cn.codingjc.peekaboo.domain.common.constant.CommonConstant;
import cn.codingjc.peekaboo.domain.domain.dto.LoginRequestDTO;
import cn.codingjc.peekaboo.domain.exception.BusinessException;
import cn.codingjc.peekaboo.domain.repository.VertifyCodeRepository;
import cn.codingjc.peekaboo.infrastructure.persistence.po.SysUserPO;
import cn.codingjc.peekaboo.infrastructure.util.RedisUtils;
import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AccountUserLogin extends UserLogin{

    private final VertifyCodeRepository vertifyCodeRepository;

    @Override
    public SysUserPO doLogin(LoginRequestDTO loginRequestDTO) {
        if (true) {
            // 验证码校验
            validateVertifyCode(loginRequestDTO);
        }
        Authentication authenticate = null;
        try {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
            authenticate = super.authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new BusinessException(MessageUtils.getMessage("user.not.exist"));
        }
        return (SysUserPO)authenticate.getPrincipal();
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
        vertifyCodeRepository.deleteVertifyCode(uuid);
    }
}
