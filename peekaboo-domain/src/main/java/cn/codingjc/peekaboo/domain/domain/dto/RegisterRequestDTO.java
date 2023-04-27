package cn.codingjc.peekaboo.domain.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class RegisterRequestDTO {

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不为空")
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户类型（00系统用户）
     */
    private Integer userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 联系方式
     */
    @NotEmpty(message = "用户名不为空")
    @Pattern(regexp="^1[3|4|5|6|7|8|9][0-9]{9}$",message="手机号格式不正确！")
    private String phonenumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不为空")
    private String password;
}
