package cn.codingjc.peekaboo.application.service.base.impl;

import cn.codingjc.peekaboo.application.service.base.SysUserService;
import cn.codingjc.peekaboo.application.service.login.AccountUserLogin;
import cn.codingjc.peekaboo.application.service.login.SmsUserLogin;
import cn.codingjc.peekaboo.application.util.JwtUtils;
import cn.codingjc.peekaboo.application.util.MessageUtils;
import cn.codingjc.peekaboo.domain.domain.dto.LoginRequestDTO;
import cn.codingjc.peekaboo.domain.domain.dto.RegisterRequestDTO;
import cn.codingjc.peekaboo.domain.exception.BusinessException;
import cn.codingjc.peekaboo.domain.exception.ErrorCodeEnum;
import cn.codingjc.peekaboo.domain.repository.UserRepository;
import cn.codingjc.peekaboo.infrastructure.persistence.po.SysUserPO;
import cn.hutool.core.util.ObjectUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static cn.codingjc.peekaboo.domain.common.constant.CommonConstant.TOKEN_HEAD;
import static cn.codingjc.peekaboo.domain.common.constant.CommonConstant.USERNAME;


@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final AccountUserLogin accountUserLogin;

    private final SmsUserLogin smsUserLogin;

    private final UserRepository userRepository;

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        if ("code".equals(loginRequestDTO.getLoginType())) {
            return accountUserLogin.login(loginRequestDTO);
        }
        if ("sms".equals(loginRequestDTO.getLoginType())) {
            return smsUserLogin.login(loginRequestDTO);
        }
        return null;
    }

    @Override
    public void register(RegisterRequestDTO registerRequestDTO) {
        // 校验用户名唯一
        SysUserPO sysUserPO = userRepository.getUserByUsername(registerRequestDTO.getUserName());
        if (ObjectUtil.isNotNull(sysUserPO)) {
            throw new BusinessException(MessageUtils.getMessage("username.already.exist"));
        }
        // 校验手机号唯一
        SysUserPO phoneUser = userRepository.getUserByPhonenumber(registerRequestDTO.getPhonenumber());
        if (ObjectUtil.isNotNull(phoneUser)) {
            throw new BusinessException(MessageUtils.getMessage("phone.already.exist"));
        }
        boolean result = userRepository.saveRegisterUser(registerRequestDTO);
        if (!result) {
            throw new BusinessException(MessageUtils.getMessage("register.fail"));
        }
    }

    @Override
    public void loginOut(String token) {
        String authToken = token.substring(TOKEN_HEAD.length());
        Claims claims = JwtUtils.verifyJwt(authToken);
        if (claims == null) {
            throw new BusinessException(ErrorCodeEnum.TOKEN_INVALID);
        }
        String username = claims.get(USERNAME, String.class);
        userRepository.deleteToken(username);
    }
}
