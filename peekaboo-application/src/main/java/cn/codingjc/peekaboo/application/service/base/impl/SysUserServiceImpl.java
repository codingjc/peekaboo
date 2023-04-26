package cn.codingjc.peekaboo.application.service.base.impl;

import cn.codingjc.peekaboo.application.service.base.SysUserService;
import cn.codingjc.peekaboo.application.service.login.AccountUserLogin;
import cn.codingjc.peekaboo.application.service.login.SmsUserLogin;
import cn.codingjc.peekaboo.domain.domain.dto.LoginRequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final AccountUserLogin accountUserLogin;

    private final SmsUserLogin smsUserLogin;

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

}
