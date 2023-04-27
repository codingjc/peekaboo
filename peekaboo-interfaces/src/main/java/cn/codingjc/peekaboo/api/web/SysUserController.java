package cn.codingjc.peekaboo.api.web;

import cn.codingjc.peekaboo.application.service.base.SysUserService;
import cn.codingjc.peekaboo.application.util.MessageUtils;
import cn.codingjc.peekaboo.domain.domain.dto.LoginRequestDTO;
import cn.codingjc.peekaboo.domain.domain.dto.RegisterRequestDTO;
import cn.codingjc.peekaboo.domain.domain.vo.ResultVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @PostMapping("/login")
    public ResultVO login(@RequestBody LoginRequestDTO loginRequestDTO){
        String token = sysUserService.login(loginRequestDTO);
        return ResultVO.ok(MessageUtils.getMessage("login.success"), token);
    }

    @PostMapping("/register")
    public ResultVO register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        sysUserService.register(registerRequestDTO);
        return ResultVO.ok(MessageUtils.getMessage("register.success"));
    }

}
