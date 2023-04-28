package cn.codingjc.peekaboo.api.web;

import cn.codingjc.peekaboo.application.service.base.SysUserService;
import cn.codingjc.peekaboo.application.util.MessageUtils;
import cn.codingjc.peekaboo.domain.domain.dto.LoginRequestDTO;
import cn.codingjc.peekaboo.domain.domain.dto.RegisterRequestDTO;
import cn.codingjc.peekaboo.domain.domain.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static cn.codingjc.peekaboo.domain.common.constant.CommonConstant.TOKEN_HEADER;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "user", description = "用户相关")
public class SysUserController {

    private final SysUserService sysUserService;

    /**
     * 用户注册
     * @param registerRequestDTO
     * @return
     */
    @PostMapping("/register")
    @Operation(summary = "Register user", description = "This can only be done by the logged in user.", tags = { "user" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResultVO.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = ResultVO.class)) }) })
    public ResultVO register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        sysUserService.register(registerRequestDTO);
        return ResultVO.ok(MessageUtils.getMessage("register.success"));
    }

    /**
     * 用户登录
     * @param loginRequestDTO
     * @return
     */
    @PostMapping("/login")
    public ResultVO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        String token = sysUserService.login(loginRequestDTO);
        return ResultVO.ok(MessageUtils.getMessage("login.success"), token);
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @GetMapping("/loginOut")
    public ResultVO loginOut(HttpServletRequest request){
        String token = request.getHeader(TOKEN_HEADER);
        sysUserService.loginOut(token);
        return ResultVO.ok(MessageUtils.getMessage("login.success"), token);
    }

}
