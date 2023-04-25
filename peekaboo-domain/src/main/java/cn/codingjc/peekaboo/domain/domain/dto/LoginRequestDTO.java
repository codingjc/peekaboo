package cn.codingjc.peekaboo.domain.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequestDTO {

    @NotEmpty(message = "用户名不为空")
    private String username;

    @NotEmpty(message = "密码不为空")
    private String password;

    @NotEmpty(message = "uuid不为空")
    private String uuid;

    @NotEmpty(message = "验证码不为空")
    private String veritfyCode;
}
