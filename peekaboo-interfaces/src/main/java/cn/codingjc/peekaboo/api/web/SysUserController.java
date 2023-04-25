package cn.codingjc.peekaboo.api.web;

import cn.codingjc.peekaboo.domain.domain.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @PostMapping("/login")
    public ResultVO login(){
        return null;
    }
}
