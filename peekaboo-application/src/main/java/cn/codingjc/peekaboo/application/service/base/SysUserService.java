package cn.codingjc.peekaboo.application.service.base;

import cn.codingjc.peekaboo.domain.domain.dto.LoginRequestDTO;
import cn.codingjc.peekaboo.domain.domain.dto.RegisterRequestDTO;

public interface SysUserService {

    String login(LoginRequestDTO loginRequestDTO);

    void register(RegisterRequestDTO registerRequestDTO);
}
