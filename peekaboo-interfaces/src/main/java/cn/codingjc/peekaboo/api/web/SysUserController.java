package cn.codingjc.peekaboo.api.web;

import cn.codingjc.peekaboo.application.service.base.SysUserService;
import cn.codingjc.peekaboo.domain.domain.dto.LoginRequestDTO;
import cn.codingjc.peekaboo.domain.domain.vo.ResultVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @PostMapping("/login")
    public ResultVO login(@RequestBody LoginRequestDTO loginRequestDTO){
        String token = sysUserService.login(loginRequestDTO);
        return null;
    }
}
