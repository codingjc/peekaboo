package cn.codingjc.peekaboo.application.service.base;

import cn.codingjc.peekaboo.domain.domain.dto.LoginRequestDTO;

public interface SysUserService {

    String login(LoginRequestDTO loginRequestDTO);
}
